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
import model.Ward;

public class WardDAO extends DBContext<Ward> {

    public List<Ward> getWardsByDistrictId(long districtId) {
        List<Ward> list = new ArrayList<>();
        String sql = "SELECT ward_id, ward_name FROM Ward WHERE district_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, districtId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ward w = new Ward();
                w.setWardId(rs.getLong("ward_id"));
                w.setWardName(rs.getString("ward_name"));
                list.add(w);
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