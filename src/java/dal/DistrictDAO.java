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
import model.District;

public class DistrictDAO extends DBContext<District> {

    public List<District> getDistrictsByProvinceId(long provinceId) {
        List<District> list = new ArrayList<>();
        String sql = "SELECT district_id, district_name FROM District WHERE province_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, provinceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                District d = new District();
                d.setDistrictId(rs.getLong("district_id"));
                d.setDistrictName(rs.getString("district_name"));
                list.add(d);
            }
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
