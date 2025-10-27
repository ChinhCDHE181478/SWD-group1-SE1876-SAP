/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author Chinh
 */
import java.sql.*;
import model.*;

public class PromotionDAO extends DBContext<Promotion> {

    public Promotion getPromotionByCode(String code) {
        Promotion promo = null;
        String sql = "SELECT * FROM Promotion WHERE code = ? AND GETDATE() BETWEEN start_date AND end_date";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                promo = new Promotion();
                promo.setPromotionId(rs.getLong("promotion_id"));
                promo.setCode(rs.getString("code"));
                promo.setDiscountPercent(rs.getDouble("discount_percent"));
                promo.setDiscountAmount(rs.getDouble("discount_amount"));
                promo.setStartDate(rs.getTimestamp("start_date"));
                promo.setEndDate(rs.getTimestamp("end_date"));
                promo.setCurrentCount(rs.getInt("current_count"));
                promo.setUsageLimitPerUser(rs.getInt("usage_limit_per_user"));
                promo.setDescription(rs.getString("description"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }

        return promo;
    }
}
