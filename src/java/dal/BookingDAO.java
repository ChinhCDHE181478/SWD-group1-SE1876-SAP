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
import java.util.*;
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
            if (booking.getCustomer() != null && booking.getCustomer().getUserId() > 0) {
                ps.setLong(1, booking.getCustomer().getUserId());
            } else {
                ps.setNull(1, Types.BIGINT);
            }

            if (booking.getCar() != null && booking.getCar().getCarId() > 0) {
                ps.setLong(2, booking.getCar().getCarId());
            } else {
                ps.setNull(2, Types.BIGINT);
            }

            if (booking.getStartDate() != null) {
                ps.setTimestamp(3, booking.getStartDate());
            } else {
                ps.setNull(3, Types.TIMESTAMP);
            }

            if (booking.getEndDate() != null) {
                ps.setTimestamp(4, booking.getEndDate());
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }

            ps.setString(5, booking.getStatus());
            ps.setTimestamp(6, booking.getCreatedAt());

            ps.setNull(7, Types.BIGINT);  // promotion_id
            ps.setNull(8, Types.TIMESTAMP); // update_at
            ps.setNull(9, Types.BIGINT); // update_by

            if (booking.getAddress() != null && booking.getAddress().getAddressId() > 0) {
                ps.setLong(10, booking.getAddress().getAddressId());
            } else {
                ps.setNull(10, Types.BIGINT);
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        return 0;
    }

    public boolean isCarAvailable(long carId, Timestamp startDate, Timestamp endDate) {
        String sql = """
            SELECT COUNT(*) AS total
            FROM Booking
            WHERE car_id = ?
              AND status NOT IN ('COMPLETED', 'CANCEL')
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

    public boolean updatePromotionForBooking(long bookingId, long promotionId) {
        String sql = "UPDATE Booking SET promotion_id = ? WHERE booking_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, promotionId);
            ps.setLong(2, bookingId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateStatusForBooking(long bookingId, String status) {
        String sql = "UPDATE Booking SET status = ? WHERE booking_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setLong(2, bookingId);

            int rows = ps.executeUpdate(); // ✅ số dòng bị ảnh hưởng
            return rows > 0;               // ✅ true nếu có dòng được update
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close(); // ❗ chỉ close nếu không dùng pool
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteByBookingId(long bookingId) {
        String sql = "DELETE Booking WHERE booking_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, bookingId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getOwnerEmailByBookingId(long bookingId) {
        String email = null;
        String sql = """
            SELECT u.email
            FROM Booking b
            JOIN Car c ON b.car_id = c.car_id
            JOIN [User] u ON c.owner_id = u.user_id
            WHERE b.booking_id = ?
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, bookingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        return email;
    }

    public Booking getBookingDetailById(long bookingId) {
        Booking booking = null;

        String sql = """
            SELECT 
                b.booking_id, b.start_date, b.end_date, b.status, b.created_at,
                c.car_id, c.model AS car_model, c.license_plate, c.price_per_day,
                cu.user_id AS customer_id, cu.name AS customer_name, cu.email AS customer_email,
                ow.user_id AS owner_id, ow.name AS owner_name, ow.email AS owner_email
            FROM Booking b
            JOIN Car c ON b.car_id = c.car_id
            JOIN [User] cu ON b.customer_id = cu.user_id
            JOIN [User] ow ON c.owner_id = ow.user_id
            WHERE b.booking_id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                booking = new Booking();
                booking.setBookingId(rs.getLong("booking_id"));
                booking.setStartDate(rs.getTimestamp("start_date"));
                booking.setEndDate(rs.getTimestamp("end_date"));
                booking.setStatus(rs.getString("status"));
                booking.setCreatedAt(rs.getTimestamp("created_at"));

                // Car info
                Car car = new Car();
                car.setCarId(rs.getLong("car_id"));
                car.setModel(rs.getString("car_model"));
                car.setLicensePlate(rs.getString("license_plate"));
                car.setPricePerDay(rs.getDouble("price_per_day"));
                booking.setCar(car);

                // Customer info
                User customer = new User();
                customer.setUserId(rs.getLong("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setEmail(rs.getString("customer_email"));
                booking.setCustomer(customer);

                // Owner info (add vào Car)
                User owner = new User();
                owner.setUserId(rs.getLong("owner_id"));
                owner.setName(rs.getString("owner_name"));
                owner.setEmail(rs.getString("owner_email"));
                car.setOwner(owner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignore) {
            }
        }

        return booking;
    }
    
    
    public List<Booking> getBookingsByUserId(long userId) {
        List<Booking> list = new ArrayList<>();
        String sql = """
            SELECT 
                b.booking_id, b.start_date, b.end_date, b.status, b.created_at,
                c.car_id, c.model, c.license_plate, c.price_per_day
            FROM Booking b
            JOIN Car c ON b.car_id = c.car_id
            WHERE b.customer_id = ? AND (b.status IS NULL OR b.status <> 'DRAFT')
            ORDER BY b.created_at DESC
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingId(rs.getLong("booking_id"));
                b.setStartDate(rs.getTimestamp("start_date"));
                b.setEndDate(rs.getTimestamp("end_date"));
                b.setStatus(rs.getString("status"));
                b.setCreatedAt(rs.getTimestamp("created_at"));

                Car car = new Car();
                car.setCarId(rs.getLong("car_id"));
                car.setModel(rs.getString("model"));
                car.setLicensePlate(rs.getString("license_plate"));
                car.setPricePerDay(rs.getDouble("price_per_day"));
                b.setCar(car);

                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (Exception ignore) {}
        }
        return list;
    }
}
