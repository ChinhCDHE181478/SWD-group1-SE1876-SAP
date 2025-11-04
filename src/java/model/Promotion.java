/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Chinh
 */
import java.sql.Timestamp;

public class Promotion {
    private long promotionId;
    private String code;
    private double discountPercent;
    private Timestamp startDate;
    private Timestamp endDate;
    private double discountAmount;
    private int usageLimitPerUser;
    private int currentCount;
    private String description;

    public Promotion() {
    }
    
    public Promotion(long promotionId) {
        this.promotionId = promotionId;
    }

    public Promotion(long promotionId, String code, double discountPercent, Timestamp startDate, Timestamp endDate, double discountAmount, int usageLimitPerUser, int currentCount, String description) {
        this.promotionId = promotionId;
        this.code = code;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountAmount = discountAmount;
        this.usageLimitPerUser = usageLimitPerUser;
        this.currentCount = currentCount;
        this.description = description;
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getUsageLimitPerUser() {
        return usageLimitPerUser;
    }

    public void setUsageLimitPerUser(int usageLimitPerUser) {
        this.usageLimitPerUser = usageLimitPerUser;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
