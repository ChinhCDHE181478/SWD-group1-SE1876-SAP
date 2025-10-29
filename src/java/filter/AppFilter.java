/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import model.User;

/**
 *
 * @author Chinh
 */

@WebFilter("/*")
public class AppFilter  implements Filter {

    // ví dụ hardcode danh sách path chỉ cho customer
    private static final List<String> CUSTOMER_PATHS = Arrays.asList(
        "/CreateBooking",
        "/CarDetailServlet",
        "/CarsServlet",
        "/ProfileServlet"
    );

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getServletPath();
        HttpSession session = request.getSession(false);

        // lấy userLogin từ session
        User user = (session != null) ? (User) session.getAttribute("userLogin") : null;
        String role = (user != null && user.getRole() != null) ? user.getRole().getRoleName() : null;

        // nếu path nằm trong customer_paths
        if (CUSTOMER_PATHS.contains(path)) {
            if (role == null || !"CUSTOMER".equalsIgnoreCase(role)) {
                // không có quyền → redirect về login hoặc trang lỗi
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        }

        // cho phép đi tiếp
        chain.doFilter(request, response);
    }
}
