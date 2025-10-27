/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Booking;

/**
 *
 * @author Chinh
 */
import java.sql.*;
import model.*;

public class BookingDAO extends DBContext<Booking>{
    
    public boolean insertBooking(Booking booking) {
        String sql = """
            INSERT INTO Booking
            (customer_id, car_id, start_date, end_date, status, created_at, promotion_id, update_at, update_by)
            VALUES (?, ?, ?, ?, ?, GETDATE(), ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, booking.getCustomer().getUserId());
            ps.setLong(2, booking.getCar().getCarId());
            ps.setTimestamp(3, booking.getStartDate());
            ps.setTimestamp(4, booking.getEndDate());
            ps.setString(5, booking.getStatus());

            // promotion (có thể null)
            if (booking.getPromotion() != null) {
                ps.setLong(6, booking.getPromotion().getPromotionId());
            } else {
                ps.setNull(6, Types.BIGINT);
            }

            ps.setTimestamp(7, booking.getUpdateAt());
            if (booking.getUpdateBy() != null) {
                ps.setLong(8, booking.getUpdateBy().getUserId());
            } else {
                ps.setNull(8, Types.BIGINT);
            }

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return false;
    }
    
    public boolean isCarAvailable(long carId, Timestamp startDate, Timestamp endDate) {
        String sql = """
            SELECT COUNT(*) AS total
            FROM Booking
            WHERE car_id = ?
              AND status IN ('PENDING', 'CONFIRMED', 'ONGOING')
              AND (
                    (start_date <= ? AND end_date >= ?)   -- trùng trong khoảng
                 OR (start_date BETWEEN ? AND ?)           -- bắt đầu trong khoảng
                 OR (end_date BETWEEN ? AND ?)             -- kết thúc trong khoảng
              )
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, carId);

            // Gán thời gian cần check
            ps.setTimestamp(2, endDate);
            ps.setTimestamp(3, startDate);
            ps.setTimestamp(4, startDate);
            ps.setTimestamp(5, endDate);
            ps.setTimestamp(6, startDate);
            ps.setTimestamp(7, endDate);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                // nếu có >= 1 booking trùng, tức là KHÔNG khả dụng
                return total == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return false; // mặc định là không khả dụng nếu có lỗi
    }
}
