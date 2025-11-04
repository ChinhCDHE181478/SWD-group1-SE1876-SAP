/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.*;

import java.sql.*;
import java.time.Instant;
/**
 *
 * @author Chinh
 */
public class PaymentDAO extends DBContext<Payment>{
    
    public long insertPayment(Payment payment) {
        String sql = """
            INSERT INTO Payment (booking_id, amount, method, status, transaction_ref, created_at, type, deposit_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, payment.getBooking().getBookingId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getMethod());
            ps.setString(4, payment.getStatus());
            ps.setString(5, payment.getTransactionRef());
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setString(7, payment.getType());
            ps.setNull(8, Types.BIGINT);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return -1;
    }
}
