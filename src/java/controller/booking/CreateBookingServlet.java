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

/**
 *
 * @author Chinh
 */
@WebServlet(name="CreateBooking", urlPatterns={"/CreateBooking"})
public class CreateBookingServlet extends HttpServlet {
    private final BookingService bookingService = new BookingService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            User customer = (User) request.getSession().getAttribute("userLogin");
            if (customer == null || customer.getUserId() <= 0) {
                throw new IllegalStateException("You must login before booking.");
            }

            String carIdStr = request.getParameter("carId");
            String provinceIdStr = request.getParameter("provinceId");
            String districtIdStr = request.getParameter("districtId");
            String wardIdStr = request.getParameter("wardId");
            String startDate = request.getParameter("startDate");
            String endDate   = request.getParameter("endDate");
            String houseNumber = request.getParameter("houseNumber");
            String addressDetail = request.getParameter("addressDetail");

            // Validate rỗng
            if (isBlank(carIdStr) || isBlank(wardIdStr) || isBlank(startDate) || isBlank(endDate)) {
                throw new IllegalArgumentException("Missing required fields.");
            }

            long carId = Long.parseLong(carIdStr);
            Long provinceId = parseLongOrNull(provinceIdStr); // không dùng trực tiếp, chỉ để kiểm tra form
            Long districtId = parseLongOrNull(districtIdStr);
            long wardId = Long.parseLong(wardIdStr);

            // Tạo Car tối thiểu
            Car car = new Car();
            car.setCarId(carId);

            Booking booking = bookingService.createBooking(
                customer, car, startDate, endDate,
                provinceId, districtId, wardId, houseNumber, addressDetail
            );

            if (booking != null && booking.getBookingId() > 0) {
                response.sendRedirect("booking-success.jsp");
            } else {
                request.setAttribute("error", "Booking creation failed!");
                request.getRequestDispatcher("booking-failed.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
    private static Long parseLongOrNull(String s) {
        try { return (isBlank(s) ? null : Long.parseLong(s.trim())); } catch (Exception e) { return null; }
    }

}
