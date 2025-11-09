/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.auth;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.*;

/**
 *
 * @author Chinh
 */
@WebServlet(name="AuthServlet", urlPatterns = {"/login", "/register", "/logout"})
public class AuthServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println("Path: " + path);
        switch (path) {
            case "/login" -> request.getRequestDispatcher("/login.jsp").forward(request, response);
            case "/register" -> request.getRequestDispatcher("/register.jsp").forward(request, response);
            case "/logout" -> {
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/HomeServlet");
            }
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/login".equals(path)) {
            handleLogin(request, response);
        } else if ("/register".equals(path)) {
            handleRegister(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = trim(request.getParameter("email"));
        String password = trim(request.getParameter("password"));

        String error = authService.validateLogin(email, password, request.getSession());
        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/HomeServlet");
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = trim(request.getParameter("email"));

        String error = authService.register(email);
        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        request.setAttribute("msg", "Đăng ký thành công, vui lòng đăng nhập.");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private String trim(String s) {
        return s == null ? "" : s.trim();
    }

}
