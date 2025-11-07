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
import java.util.ArrayList;
import java.util.List;
import model.*;

public class PromotionDAO extends DBContext<Promotion> {

    public List<Promotion> getAllActivePromotions() {
        List<Promotion> list = new ArrayList<>();

        String sql = """
            SELECT 
                promotion_id, code, discount_percent, start_date, end_date,
                discount_amount, usage_limit_per_user, current_count, description, max_number
            FROM Promotion
            WHERE 
                GETDATE() BETWEEN start_date AND end_date
                AND (current_count < max_number)
            ORDER BY discount_percent DESC, discount_amount DESC
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Promotion p = new Promotion();
                p.setPromotionId(rs.getLong("promotion_id"));
                p.setCode(rs.getString("code"));
                p.setDiscountPercent(rs.getDouble("discount_percent"));
                p.setStartDate(rs.getTimestamp("start_date"));
                p.setEndDate(rs.getTimestamp("end_date"));
                p.setDiscountAmount(rs.getDouble("discount_amount"));
                p.setUsageLimitPerUser(rs.getInt("usage_limit_per_user"));
                p.setCurrentCount(rs.getInt("current_count"));
                p.setDescription(rs.getString("description"));
                p.setMax_number(rs.getInt("max_number"));
                list.add(p);
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
        return list;
    }
}
