/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.UserDAO;
import jakarta.servlet.http.HttpSession;
import model.User;
import utils.Common;

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

    public String register(String email, String password) {
        UserDAO userDAO = new UserDAO();
        UserDAO userDAO2 = new UserDAO();
         
        if (email.isEmpty() || password.isEmpty()) {
            return "Email và mật khẩu là bắt buộc.";
        }
        if (userDAO.emailExists(email)) {
            return "Email đã tồn tại, vui lòng chọn email khác.";
        }
        boolean ok = userDAO2.insertUser(email, password);
        if (!ok) {
            return "Tạo tài khoản thất bại, vui lòng thử lại.";
        }
        return null;
    }
}
