/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Tiện ích gửi email cho Java Servlet / Ant project
 * Không cần Spring Boot 😘
 */
public class EmailUtil {

    // 👉 Hàm gửi email cơ bản (HTML hoặc text đều được)
    public static void sendEmail(String to, String subject, String content) {
        // ===== Cấu hình tài khoản gửi =====
        final String username = "contact.edutest.vn@gmail.com"; // 👈 Gmail của Senpai
        final String password = "gapkmfnwqhmdgcbh"; // 👈 Mật khẩu ứng dụng Gmail

        // ===== Thiết lập cấu hình SMTP =====
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // bật TLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // ===== Tạo session với thông tin xác thực =====
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // ===== Tạo message =====
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "Car Rental System", "UTF-8"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject, "UTF-8");
            message.setContent(content, "text/html; charset=UTF-8"); // cho phép HTML

            // ===== Gửi =====
            Transport.send(message);

            System.out.println("✅ Email đã gửi tới: " + to);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Lỗi gửi email: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        sendEmail("quansuper000@gmail.com", "Hello", "Test");
    }
}
