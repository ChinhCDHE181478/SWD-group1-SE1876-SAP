/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.payment;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp; 
import model.Booking;
import model.Payment;
import service.*;

/**
 *
 * @author Chinh
 */
@WebServlet(name = "VNPayReturnServlet", urlPatterns = {"/VNPayReturnServlet"})
public class VNPayReturnServlet extends HttpServlet {

    private final PaymentService paymentService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Các tham số VNPay gửi về
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

            // Chuẩn bị đối tượng Payment
            Payment payment = new Payment();
            Booking booking = new Booking();
            booking.setBookingId(bookingId);
            payment.setBooking(booking);

            payment.setTransactionRef(txnRef);
            payment.setAmount(Double.parseDouble(vnp_Amount) / 100); // VNPay nhân 100 lần
            payment.setMethod("VNPAY");
            payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            payment.setType("FULL");

            if ("00".equals(vnp_ResponseCode)) {
                payment.setStatus("SUCCESS");
            } else {
                payment.setStatus("FAILED");
            }

            // Lưu Payment vào DB
            long paymentId = paymentDAO.insertPayment(payment);
//            if (paymentId > 0) {
//                bookingDAO.attachPayment(bookingId, paymentId);
//            }

            // Gửi kết quả về view
            request.setAttribute("bookingId", bookingId);
            request.setAttribute("payment", payment);
            if ("00".equals(vnp_ResponseCode)) {
                request.setAttribute("message", "✅ Thanh toán thành công!");
            } else {
                request.setAttribute("message", "❌ Thanh toán thất bại hoặc bị hủy.");
            }

            request.getRequestDispatcher("payment-result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
