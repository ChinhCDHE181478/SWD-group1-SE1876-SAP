/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Chinh
 */
import dal.AddressDAO;
import dal.BookingDAO;
import dal.PromotionDAO;
import model.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class BookingService {
    
    public static void main(String[] args) {
        BookingService b = new BookingService();
        User u = new User();
        u.setUserId(1);
        Car c = new Car();
        c.setCarId(1);
        Timestamp t1 = new Timestamp(1000000);
        Timestamp t2 = new Timestamp(10000000);
        Booking bo = b.createBooking(u, c, t1.toString(), t2.toString(), 1l, 1l, 1l, "12", "wall");
        System.out.println(bo);
    }
    
    public Booking createBooking(User customer, Car car, String start, String end,
                                 Long provinceId, Long districtId, long wardId,
                                 String houseNumber, String addressDetail) {
        AddressDAO addressDAO = new AddressDAO();
        AddressDAO addressDAO2 = new AddressDAO();
        BookingDAO bookingDAO = new BookingDAO();

        try {
            // 1️⃣ Chuẩn bị địa chỉ
            Address address = new Address();
            address.setUser(customer);
            address.setHouseNumber(houseNumber);
            address.setAddressDetail(addressDetail);

            Ward ward = new Ward();
            ward.setWardId(wardId);
            address.setWard(ward);

            long addressId = addressDAO.insertAddress(address);
            if (addressId <= 0) {
                System.out.println("❌ Insert Address thất bại, không tạo Booking.");
                return null;
            }

            address.setAddressId(addressId);

            // 2️⃣ Chuẩn bị Booking
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Booking booking = new Booking();
            booking.setCustomer(customer);
            booking.setCar(car);
            booking.setAddress(address);
            booking.setStatus("PENDING");
            booking.setStartDate(new Timestamp(sdf.parse(start).getTime()));
            booking.setEndDate(new Timestamp(sdf.parse(end).getTime()));
            booking.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            long bookingId = bookingDAO.insertBooking(booking);
            if (bookingId <= 0) {
                System.out.println("❌ Insert Booking thất bại, rollback địa chỉ mới tạo.");
                // rollback mềm: xoá address vừa tạo (nếu cần)
                addressDAO2.deleteAddressById(addressId);
                return null;
            }

            booking.setBookingId(bookingId);
            System.out.println("✅ Booking được tạo thành công ID=" + bookingId);
            return booking;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
