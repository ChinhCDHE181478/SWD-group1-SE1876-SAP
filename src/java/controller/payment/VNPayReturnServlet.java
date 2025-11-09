/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.payment;

import dal.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import model.Booking;
import model.Car;
import model.Payment;
import model.User;
import service.*;
import utils.EmailUtil;

@WebServlet(name = "VNPayReturnServlet", urlPatterns = {"/VNPayReturnServlet"})
public class VNPayReturnServlet extends HttpServlet {

    private final PaymentService paymentService = new PaymentService();
    private final BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // ===== Lấy tham số từ VNPay =====
            String txnRef = request.getParameter("vnp_TxnRef");
            String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
            String vnp_Amount = request.getParameter("vnp_Amount");
            String vnp_TransactionNo = request.getParameter("vnp_TransactionNo"); // optional

            BookingDAO bookingDAO = new BookingDAO();
            PaymentDAO paymentDAO = new PaymentDAO();

            Long bookingId = paymentService.getBookingIdByTxnRef(txnRef);
            if (bookingId == null) {
                request.setAttribute("error", "Invalid or expired transaction reference.");
                request.getRequestDispatcher("payment-result.jsp").forward(request, response);
                return;
            }

            // ===== Chuẩn bị Payment =====
            Payment payment = new Payment();
            Booking booking = new Booking();
            booking.setBookingId(bookingId);
            payment.setBooking(booking);
            payment.setTransactionRef(txnRef);
            payment.setAmount(Double.parseDouble(vnp_Amount) / 100); // VNPay nhân 100 lần
            payment.setMethod("VNPAY");
            payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            payment.setType("FULL");

            // ===== Lấy user trong session =====
            User currentUser = (User) request.getSession().getAttribute("userLogin");

            // ===== Thanh toán THÀNH CÔNG =====
            if ("00".equals(vnp_ResponseCode)) {
                payment.setStatus("SUCCESS");
                long paymentId = paymentDAO.insertPayment(payment);
                
                boolean b = bookingService.updateStatus(bookingId, "PENDING");

                Booking fullBooking = bookingService.getBookingDetailById(bookingId);
                Car carInfo = fullBooking.getCar();

                // --- Gửi mail KHÁCH HÀNG ---
                String customerMail = fullBooking.getCustomer().getEmail();
                String subjectCus = "✅ Xác nhận đặt xe thành công - " + carInfo.getModel();
                String contentCus = String.format(
                        "Xin chào %s,\n\n"
                                + "Bạn đã đặt xe %s (biển số %s) thành công.\n"
                                + "Thời gian thuê: %s → %s\n"
                                + "Giá thuê: %,d VNĐ/ngày.\n\n"
                                + "Cảm ơn bạn đã tin tưởng dịch vụ của chúng tôi! 🚗💨",
                        fullBooking.getCustomer().getName(),
                        carInfo.getModel(),
                        carInfo.getLicensePlate(),
                        fullBooking.getStartDate(),
                        fullBooking.getEndDate(),
                        (int) carInfo.getPricePerDay()
                );
                EmailUtil.sendEmail(customerMail, subjectCus, contentCus);

                // --- Gửi mail CHỦ XE ---
                String ownerMail = carInfo.getOwner().getEmail();
                String subjectOwner = "🚗 Xe của bạn đã được thuê!";
                String contentOwner = String.format(
                        "Xin chào %s,\n\n"
                                + "Xe %s (biển số %s) của bạn đã được thuê bởi khách hàng %s.\n"
                                + "Thời gian thuê: %s → %s\n\n"
                                + "Hãy kiểm tra hệ thống để xem chi tiết đơn đặt xe. 💼",
                        carInfo.getOwner().getName(),
                        carInfo.getModel(),
                        carInfo.getLicensePlate(),
                        fullBooking.getCustomer().getName(),
                        fullBooking.getStartDate(),
                        fullBooking.getEndDate()
                );
                EmailUtil.sendEmail(ownerMail, subjectOwner, contentOwner);

                // --- Forward kết quả ---
                request.setAttribute("bookingId", bookingId);
                request.setAttribute("payment", payment);
                request.setAttribute("message", "✅ Thanh toán thành công!");
                request.getRequestDispatcher("payment-result.jsp").forward(request, response);

            } else {
                // ===== Thanh toán THẤT BẠI =====
                payment.setStatus("FAILED");

                // --- Gửi mail KHÁCH HÀNG ---
                User customer = (User) request.getSession().getAttribute("user");
                if (customer != null && customer.getEmail() != null) {
                    String subjectFail = "⚠️ Thanh toán thất bại";
                    String contentFail = String.format(
                            "Xin chào %s,\n\n"
                                    + "Rất tiếc, quá trình thanh toán hoặc đặt xe của bạn đã không thành công.\n"
                                    + "Vui lòng kiểm tra lại thông tin thanh toán hoặc thử lại sau.\n\n"
                                    + "Nếu cần hỗ trợ, hãy liên hệ đội ngũ chăm sóc khách hàng của chúng tôi. ❤️",
                            customer.getName()
                    );
                    EmailUtil.sendEmail(customer.getEmail(), subjectFail, contentFail);
                }

                // --- Xóa booking chưa hoàn tất ---
                bookingService.deleteById(bookingId);

                // --- Lưu Payment thất bại ---
                paymentDAO.insertPayment(payment);

                // --- Forward kết quả ---
                request.setAttribute("bookingId", bookingId);
                request.setAttribute("payment", payment);
                request.setAttribute("message", "❌ Thanh toán thất bại hoặc bị hủy.");
                request.getRequestDispatcher("payment-result.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}