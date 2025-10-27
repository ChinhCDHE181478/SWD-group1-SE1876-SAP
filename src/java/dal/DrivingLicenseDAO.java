/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.*;
import model.DrivingLicense;
import model.User;

/**
 *
 * @author Chinh
 */
public class DrivingLicenseDAO extends DBContext<DrivingLicense> {

    public boolean updateDrivingLicense(DrivingLicense license) {
        String sql = """
            UPDATE Driving_License
            SET 
                license_number = ?,
                class = ?,
                issue_date = ?,
                expired_date = ?,
                updated_at = GETDATE()
            WHERE user_id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, license.getLicenseNumber());
            ps.setString(2, license.getLicenseClass());
            ps.setTimestamp(3, license.getIssueDate());
            ps.setTimestamp(4, license.getExpiredDate());

            // lấy ID từ user object
            if (license.getUser() != null) {
                ps.setLong(5, license.getUser().getUserId());
            } else {
                throw new SQLException("User object is null!");
            }

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public List<DrivingLicense> getLicensesByUser(User user) {
        List<DrivingLicense> list = new ArrayList<>();
        String sql = """
            SELECT 
                license_id, license_number, class, issue_date, expired_date, updated_at
            FROM Driving_License
            WHERE user_id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, user.getUserId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DrivingLicense license = new DrivingLicense();
                license.setLicenseId(rs.getLong("license_id"));
                license.setLicenseNumber(rs.getString("license_number"));
                license.setLicenseClass(rs.getString("class"));
                license.setIssueDate(rs.getTimestamp("issue_date"));
                license.setExpiredDate(rs.getTimestamp("expired_date"));
                license.setUpdatedAt(rs.getTimestamp("updated_at"));
                license.setUser(user);

                list.add(license);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
}
