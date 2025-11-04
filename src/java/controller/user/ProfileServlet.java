/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.UserService;

/**
 *
 * @author Chinh
 */
@WebServlet(name="ProfileServlet", urlPatterns={"/ProfileServlet"})
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
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Lấy dữ liệu từ form
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        // Chỉ set những gì user nhập
        if (name != null && !name.trim().isEmpty()) currentUser.setName(name);
        if (password != null && !password.trim().isEmpty()) currentUser.setPassword(password);
        if (phone != null && !phone.trim().isEmpty()) currentUser.setPhone(phone);

        boolean success = userService.updateProfile(currentUser);

        if (success) {
            request.getSession().setAttribute("userLogin", currentUser);
            request.setAttribute("message", "✅ Profile updated successfully!");
        } else {
            request.setAttribute("message", "❌ Update failed, please try again!");
        }

        request.setAttribute("user", currentUser);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

}
