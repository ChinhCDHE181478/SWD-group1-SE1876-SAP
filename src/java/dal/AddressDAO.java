/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.*;
import java.sql.*;

/**
 *
 * @author Chinh
 */
public class AddressDAO extends DBContext<Address>{
    
    public long insertAddress(Address address) throws SQLException {
        String sql = "INSERT INTO Address (user_id, ward_id, address_detail, house_number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, address.getUser().getUserId());
            ps.setLong(2, address.getWard().getWardId());
            ps.setString(3, address.getAddressDetail());
            ps.setString(4, address.getHouseNumber());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (Exception ignore) {}
        }
        return 0;
    }

    public void deleteAddressById(long addressId) throws SQLException {
        String sql = "DELETE FROM Address WHERE address_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, addressId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (Exception ignore) {}
        }
    }

}
