/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Chinh
 */
public class UserDAO extends DBContext<User> {

    public User getByEmail(String email) {
        String sql = "SELECT user_id, name, email, password, phone, role_id, status FROM [User] WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getLong("user_id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setPhone(rs.getString("phone"));
                // nếu cần role/status thì map thêm
                return u;
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
        return null;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM [User] WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
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

    public boolean insertUser(String email, String rawPassword) {
        // TODO: nếu muốn mã hoá: String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        String sql = """
            INSERT INTO [User] (email, password, status, created_at, is_delete, role_id)
            VALUES (?, ?, 'ACTIVE', GETDATE(), 0, 3)
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, rawPassword); // hoặc hashed
            return ps.executeUpdate() > 0;
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

    public User login(String email, String rawPassword) {
        // Nếu dùng BCrypt: lấy user, rồi BCrypt.checkpw(rawPassword, user.getPassword())
        String sql = "SELECT u.user_id, u.name, u.email, u.password, u.phone, u.status, "
                + "r.role_id, r.role_name, r.description "
                + "FROM [User] u "
                + "JOIN Role r ON u.role_id = r.role_id "
                + "WHERE u.email = ? AND u.password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, rawPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getLong("user_id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setPhone(rs.getString("phone"));

                Role role = new Role();
                role.setRoleId(rs.getLong("role_id"));
                role.setRoleName(rs.getString("role_name"));
                role.setDescription(rs.getString("description"));

                u.setRole(role);
                
                return u;
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
        return null;
    }

    public boolean updateUser(User user) {
        StringBuilder sql = new StringBuilder("UPDATE [User] SET ");
        List<Object> params = new ArrayList<>();

        // build các field cần update
        if (user.getName() != null) {
            sql.append("name = ?, ");
            params.add(user.getName());
        }
        if (user.getPassword() != null) {
            sql.append("password = ?, ");
            params.add(user.getPassword());
        }
        if (user.getPhone() != null) {
            sql.append("phone = ?, ");
            params.add(user.getPhone());
        }
        if (user.getRole() != null && user.getRole().getRoleId() > 0) {
            sql.append("role_id = ?, ");
            params.add(user.getRole().getRoleId());
        }
        if (user.getAvatar() != null) {
            sql.append("avatar = ?, ");
            params.add(user.getAvatar());
        }
        if (user.getStatus() != null) {
            sql.append("status = ?, ");
            params.add(user.getStatus());
        }
        if (user.isIsDelete() != null) {
            sql.append("is_deleted = ?, ");
            params.add(user.isIsDelete());
        }

        // bỏ dấu phẩy cuối
        if (params.isEmpty()) {
            // không có gì để update
            return false;
        }
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE user_id = ?");
        params.add(user.getUserId());

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
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

}
