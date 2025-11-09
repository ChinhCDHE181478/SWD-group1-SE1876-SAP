/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.UserDAO;
import jakarta.servlet.http.HttpSession;
import model.User;
import utils.*;

/**
 *
 * @author Chinh
 */
public class AuthService {

    public String validateLogin(String email, String password, HttpSession session) {
        UserDAO userDAO = new UserDAO();

        if (email.isEmpty() || password.isEmpty()) {
            return "Email và mật khẩu là bắt buộc.";
        }

        User user = userDAO.login(email, password);
        if (user == null) {
            return "Email hoặc mật khẩu không đúng.";
        }
        user.setPassword(null);

        // Đăng nhập thành công
        session.setAttribute("userLogin", user);
        return null; // null = không có lỗi
    }

    public String register(String email) {
        UserDAO userDAO = new UserDAO();

        UserDAO userDAO1 = new UserDAO();
        // ✅ Kiểm tra trống
        if (email == null || email.trim().isEmpty()) {
            return "Email là bắt buộc.";
        }

        // ✅ Kiểm tra tồn tại
        if (userDAO.emailExists(email.trim())) {
            return "Email đã tồn tại, vui lòng chọn email khác.";
        }

        // ✅ Tạo mật khẩu ngẫu nhiên
        String pass = randomString8();

        // ✅ Lưu vào DB
        boolean ok = userDAO1.insertUser(email.trim(), pass);
        if (!ok) {
            return "Tạo tài khoản thất bại, vui lòng thử lại.";
        }

        // ✅ Gửi mail xác nhận
        try {
            String content = "<h3>Chào mừng bạn đến với Car Rental System 🚗</h3>"
                    + "<p>Email đăng ký: <b>" + email + "</b></p>"
                    + "<p>Mật khẩu của bạn là: <b>" + pass + "</b></p>"
                    + "<p>👉 Vui lòng đăng nhập và đổi mật khẩu để bảo mật hơn nhé.</p>"
                    + "<br><i>Trân trọng,<br/>Đội ngũ Car Rental System</i>";

            EmailUtil.sendEmail(
                    email,
                    "Register User - Car Rental System",
                    content
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "Đăng ký thành công, nhưng gửi email thất bại: " + e.getMessage();
        }

        return null; // null = OK
    }

    public static String randomString8() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }
}
