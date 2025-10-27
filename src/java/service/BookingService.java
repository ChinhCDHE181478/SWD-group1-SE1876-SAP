/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Chinh
 */
import dal.BookingDAO;
import dal.PromotionDAO;
import model.*;
import java.sql.*;

public class BookingService {
    
    public boolean createBooking(User customer, Car car, String promoCode,
                                 Timestamp startDate, Timestamp endDate) {
        BookingDAO bookingDAO = new BookingDAO();
        PromotionDAO promotionDAO = new PromotionDAO();
        Promotion promotion = null;
        if (promoCode != null && !promoCode.trim().isEmpty()) {
            promotion = promotionDAO.getPromotionByCode(promoCode);
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setCar(car);
        booking.setPromotion(promotion);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setStatus("PENDING"); // trạng thái mặc định
        booking.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        booking.setUpdateAt(null);
        booking.setUpdateBy(null);

        return bookingDAO.insertBooking(booking);
    }
}
