/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.booking;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.*;
import service.*;

/**
 *
 * @author Chinh
 */
@WebServlet(name = "ConfirmBookingServlet", urlPatterns = {"/ConfirmBookingServlet"})
public class ConfirmBookingServlet extends HttpServlet {

    private final DrivingLicenseService drivingLicenseService = new DrivingLicenseService();
    private final CarService carService = new CarService();
    private final PromotionService promotionService = new PromotionService();
    private final AddressService addressService = new AddressService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            User user = (User) request.getSession().getAttribute("userLogin");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String provinceName = request.getParameter("provinceName");
            String districtName = request.getParameter("districtName");
            String wardName = request.getParameter("wardName");
            long carId = Long.parseLong(request.getParameter("carId"));
            long provinceId = Long.parseLong(request.getParameter("provinceId"));
            long districtId = Long.parseLong(request.getParameter("districtId"));
            long wardId = Long.parseLong(request.getParameter("wardId"));
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String houseNumber = request.getParameter("houseNumber");
            String addressDetail = request.getParameter("addressDetail");

            if (user.getName() == null || user.getName().trim().isEmpty()
                    || user.getPhone() == null || user.getPhone().trim().isEmpty()) {

                // Gửi thông báo và chuyển hướng sang trang update profile
                request.setAttribute("warning",
                        "Please update your profile with your full name and phone number before booking.");
                request.setAttribute("user", user);
                request.setAttribute("bookingData", request.getParameterMap());
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }

            Car car = carService.getCarById(carId);

            // Tính tổng tiền thuê
            LocalDateTime start = LocalDateTime.parse(startDate);
            LocalDateTime end = LocalDateTime.parse(endDate);
            long hours = Duration.between(start, end).toHours();
            double totalPrice = car.getPricePerDay() * (hours / 24.0);

            boolean c = carService.isAvailableToRent(carId, Timestamp.valueOf(start), Timestamp.valueOf(end));
            if (c == false) {
                request.setAttribute("message", "This car is not available to rent this time.");
                List<Province> provinces = addressService.getAllProvincesWithDistrictsAndWards();
                request.setAttribute("provinces", provinces);
                request.setAttribute("car", car);
                request.getRequestDispatcher("/car.jsp").forward(request, response);
                return;
            }

            boolean b = drivingLicenseService.checkDrivingLicense(user, car.getDriverLicenseRequired());
            if (b == false) {
                request.setAttribute("message", "Your driving license is not valid for renting this car, please update first.");
                List<Province> provinces = addressService.getAllProvincesWithDistrictsAndWards();
                request.setAttribute("provinces", provinces);
                request.setAttribute("car", car);
                request.getRequestDispatcher("/car.jsp").forward(request, response);
                return;
            }

            // Tạo 1 booking tạm (chưa lưu DB)
            Booking tempBooking = new Booking();
            tempBooking.setCustomer(user);
            tempBooking.setCar(car);
            tempBooking.setStartDate(Timestamp.valueOf(start));
            tempBooking.setEndDate(Timestamp.valueOf(end));
            tempBooking.setStatus("PENDING");

            Address addr = new Address();
            addr.setProvince(new Province(provinceId, provinceName));
            addr.setDistrict(new District(districtId, districtName, null));
            addr.setWard(new Ward(wardId, wardName, null));
            addr.setHouseNumber(houseNumber);
            addr.setAddressDetail(addressDetail);
            tempBooking.setAddress(addr);

            // nạp danh sách promotion
            request.setAttribute("promotions", promotionService.getAllActivePromotions());
            // Gửi booking sang JSP (qua request, không dùng session)
            request.setAttribute("booking", tempBooking);
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("hours", hours);

            request.getRequestDispatcher("confirm-booking.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Invalid booking request: " + e.getMessage());
        }
    }

}
