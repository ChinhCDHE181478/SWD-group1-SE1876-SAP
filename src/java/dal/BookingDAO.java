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

public class BookingDAO extends DBContext<Booking> {

     public long insertBooking(Booking booking) throws SQLException {
        String sql = """
            INSERT INTO Booking (
                customer_id, car_id, start_date, end_date, status,
                created_at, promotion_id, update_at, update_by, address_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // set các trường, nếu null thì setNull
            if (booking.getCustomer() != null && booking.getCustomer().getUserId() > 0)
                ps.setLong(1, booking.getCustomer().getUserId());
            else ps.setNull(1, Types.BIGINT);

            if (booking.getCar() != null && booking.getCar().getCarId() > 0)
                ps.setLong(2, booking.getCar().getCarId());
            else ps.setNull(2, Types.BIGINT);

            if (booking.getStartDate() != null)
                ps.setTimestamp(3, booking.getStartDate());
            else ps.setNull(3, Types.TIMESTAMP);

            if (booking.getEndDate() != null)
                ps.setTimestamp(4, booking.getEndDate());
            else ps.setNull(4, Types.TIMESTAMP);

            ps.setString(5, booking.getStatus());
            ps.setTimestamp(6, booking.getCreatedAt());

            ps.setNull(7, Types.BIGINT);  // promotion_id
            ps.setNull(8, Types.TIMESTAMP); // update_at
            ps.setNull(9, Types.BIGINT); // update_by

            if (booking.getAddress() != null && booking.getAddress().getAddressId() > 0)
                ps.setLong(10, booking.getAddress().getAddressId());
            else ps.setNull(10, Types.BIGINT);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getLong(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (Exception ignore) {}
        }
        return 0;
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
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // mặc định là không khả dụng nếu có lỗi
    }
    
}
