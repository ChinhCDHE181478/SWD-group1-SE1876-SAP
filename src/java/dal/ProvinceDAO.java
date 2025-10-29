/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.District;
import model.Province;
import model.Ward;

/**
 *
 * @author Chinh
 */
public class ProvinceDAO extends DBContext<Province>{
    
    public List<Province> getAllProvinces() {
        List<Province> list = new ArrayList<>();
        String sql = "SELECT province_id, province_name FROM Province";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Province p = new Province();
                p.setProvinceId(rs.getLong("province_id"));
                p.setProvinceName(rs.getString("province_name"));
                list.add(p);
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
    
    public List<Province> getAllProvincesWithDistrictsAndWards() {
        List<Province> provinces = new ArrayList<>();

        String sql = """
            SELECT p.province_id, p.province_name,
                   d.district_id, d.district_name,
                   w.ward_id, w.ward_name
            FROM Province p
            LEFT JOIN District d ON p.province_id = d.province_id
            LEFT JOIN Ward w ON d.district_id = w.district_id
            ORDER BY p.province_id, d.district_id, w.ward_id
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            Map<Long, Province> provinceMap = new LinkedHashMap<>();
            Map<Long, District> districtMap = new HashMap<>();

            while (rs.next()) {
                long provinceId = rs.getLong("province_id");
                String provinceName = rs.getString("province_name");

                // Province
                Province province = provinceMap.get(provinceId);
                if (province == null) {
                    province = new Province();
                    province.setProvinceId(provinceId);
                    province.setProvinceName(provinceName);
                    provinceMap.put(provinceId, province);
                }

                long districtId = rs.getLong("district_id");
                if (districtId > 0) {
                    String districtName = rs.getString("district_name");
                    String districtKey = provinceId + "-" + districtId;

                    District district = districtMap.get(districtId);
                    if (district == null) {
                        district = new District();
                        district.setDistrictId(districtId);
                        district.setDistrictName(districtName);
                        province.getDistricts().add(district);
                        districtMap.put(districtId, district);
                    }

                    long wardId = rs.getLong("ward_id");
                    if (wardId > 0) {
                        Ward ward = new Ward();
                        ward.setWardId(wardId);
                        ward.setWardName(rs.getString("ward_name"));
                        district.getWards().add(ward);
                    }
                }
            }

            provinces = new ArrayList<>(provinceMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return provinces;
    }
    
}
