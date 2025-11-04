/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.booking;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.BookingService;
import java.sql.*;
import model.Booking;
import model.Car;
import model.Promotion;
import service.PaymentService;

/**
 *
 * @author Chinh
 */
@WebServlet(name = "CreateBooking", urlPatterns = {"/CreateBooking"})
public class CreateBookingServlet extends HttpServlet {

    private final BookingService bookingService = new BookingService();
    private final PaymentService paymentService = new PaymentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");
            User customer = (User) request.getSession().getAttribute("userLogin");

            if (customer == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Nhận data từ confirm-booking.jsp
            long carId = Long.parseLong(request.getParameter("carId"));
            long provinceId = Long.parseLong(request.getParameter("provinceId"));
            long districtId = Long.parseLong(request.getParameter("districtId"));
            long wardId = Long.parseLong(request.getParameter("wardId"));
            String startDate = request.getParameter("startDate").substring(0, 10);
            String endDate = request.getParameter("endDate").substring(0, 10);
            String houseNumber = request.getParameter("houseNumber");
            String addressDetail = request.getParameter("addressDetail");
            double finalTotal = Double.parseDouble(request.getParameter("finalTotal"));

            // Chuẩn bị object tối thiểu
            Car car = new Car();
            car.setCarId(carId);

            long promotionId = 0;
            try {
                promotionId = Long.parseLong(request.getParameter("promotionId"));
            } catch (Exception ignored) {
            }

            Booking created = bookingService.createBooking(
                    customer, car, startDate, endDate,
                    provinceId, districtId, wardId, houseNumber, addressDetail
            );

            // nếu chọn promotion → gán thêm
            if (promotionId > 0 && created != null) {
                created.setPromotion(new Promotion(promotionId));
                bookingService.attachPromotion(created); // nếu Senpai có hàm cập nhật riêng
            }

            // 3️⃣ Sau khi tạo booking thành công → chuyển sang thanh toán VNPay
            if (created != null && created.getBookingId() > 0) {

                // Ghi log nhẹ
                System.out.println("✅ Booking #" + created.getBookingId() + " created successfully, redirecting to VNPay...");

                // Tạo link thanh toán
                PaymentService paymentService = new PaymentService();
                String paymentLink = paymentService.createPaymentLink(created, finalTotal, request);

                // Redirect sang VNPay
                response.sendRedirect(paymentLink);
                return;
            } else {
                request.setAttribute("error", "❌ Failed to create booking!");
                request.getRequestDispatcher("booking-failed.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static Long parseLongOrNull(String s) {
        try {
            return (isBlank(s) ? null : Long.parseLong(s.trim()));
        } catch (Exception e) {
            return null;
        }
    }

}
