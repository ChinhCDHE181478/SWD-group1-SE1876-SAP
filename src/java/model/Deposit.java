package model;

import java.sql.Timestamp;

public class Deposit {
    private long depositId;
    private String status;
    private double deductionAmount;
    private double refundAmount;
    private double amount;
    private String paymentMethod;
    private Timestamp depositDate;
    private Timestamp refundDate;
    private User customer;
    private Booking booking;
    private User staff;

    // Getters & Setters
    public long getDepositId() { return depositId; }
    public void setDepositId(long depositId) { this.depositId = depositId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getDeductionAmount() { return deductionAmount; }
    public void setDeductionAmount(double deductionAmount) { this.deductionAmount = deductionAmount; }

    public double getRefundAmount() { return refundAmount; }
    public void setRefundAmount(double refundAmount) { this.refundAmount = refundAmount; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Timestamp getDepositDate() { return depositDate; }
    public void setDepositDate(Timestamp depositDate) { this.depositDate = depositDate; }

    public Timestamp getRefundDate() { return refundDate; }
    public void setRefundDate(Timestamp refundDate) { this.refundDate = refundDate; }

    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    public User getStaff() { return staff; }
    public void setStaff(User staff) { this.staff = staff; }
}
