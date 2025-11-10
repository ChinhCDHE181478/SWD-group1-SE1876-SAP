/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import model.User;
import service.UserService;

/**
 *
 * @author Chinh
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy user từ session (đã login)
        User currentUser = (User) request.getSession().getAttribute("userLogin");

        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Gửi user sang JSP để hiển thị
        request.setAttribute("user", currentUser);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        User currentUser = (User) request.getSession().getAttribute("userLogin");
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // ==== Lấy thông tin người dùng ====
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        if (name != null && !name.trim().isEmpty()) currentUser.setName(name.trim());
        if (password != null && !password.trim().isEmpty()) currentUser.setPassword(password.trim());
        if (phone != null && !phone.trim().isEmpty()) currentUser.setPhone(phone.trim());

        boolean success = userService.updateProfile(currentUser);
        request.getSession().setAttribute("userLogin", currentUser);

        // ==== Kiểm tra dữ liệu booking (từ ConfirmBookingServlet gửi qua) ====
        Map<String, String[]> paramMap = request.getParameterMap();
        boolean hasBookingData = paramMap.keySet().stream().anyMatch(k ->
                k.equals("carId") || k.equals("startDate") || k.equals("endDate")
        );

        if (success) {
            if (hasBookingData) {
                // ✅ Có dữ liệu booking → forward lại ConfirmBookingServlet để xử lý tiếp
                RequestDispatcher rd = request.getRequestDispatcher("/ConfirmBookingServlet");
                rd.forward(request, response);
                return;
            } else {
                // ❎ Không có dữ liệu booking → quay lại profile.jsp bình thường
                request.setAttribute("message", "✅ Profile updated successfully!");
                request.setAttribute("user", currentUser);
                request.getRequestDispatcher("/profile.jsp").forward(request, response);
                return;
            }
        } else {
            // ❌ Cập nhật thất bại
            request.setAttribute("message", "❌ Update failed, please try again!");
            request.setAttribute("user", currentUser);
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        }
    }

}
