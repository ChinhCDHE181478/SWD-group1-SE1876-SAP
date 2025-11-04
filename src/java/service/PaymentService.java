/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.servlet.http.*;
import java.io.UnsupportedEncodingException;
import java.util.*;
import model.Booking;
import utils.VNPayUtil;

/**
 *
 * @author Chinh
 */
public class PaymentService {
    
    private static final Map<String, Long> transactionMap = new HashMap<>();

    public String createPaymentLink(Booking booking, double finalTotal, HttpServletRequest req) throws UnsupportedEncodingException {
        if (booking == null || booking.getBookingId() <= 0) {
            throw new IllegalArgumentException("Invalid booking info");
        }

        String txnRef = "BOOKING" + booking.getBookingId() + "_" + VNPayUtil.getRandomNumber(6);

        // Lưu mapping để xử lý khi VNPay callback (vnp_TxnRef)
        transactionMap.put(txnRef, booking.getBookingId());

        // Trả về link thanh toán
        return VNPayUtil.getPaymentUrl(Math.round(finalTotal), txnRef, req);
    }

    public Long getBookingIdByTxnRef(String txnRef) {
        return transactionMap.get(txnRef);
    }
}
