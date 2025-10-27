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
import java.util.List;
import model.temp.BookingRange;

public class Car {

    private long carId;
    private String model;
    private String licensePlate;
    private String seat;
    private Double pricePerDay;
    private String status;
    private Timestamp createdAt;
    private Double deposit;
    private Timestamp updateAt;
    private Double adminFeePercent;
    private String driverLicenseRequired;

    // Quan hệ n-1
    private Brand brand;
    private CarCategory category;
    private User owner;

    // Quan hệ 1-n
    private List<CarImage> images;
    private List<CarFeature> features;

    private List<BookingRange> bookedRanges;

    public Car() {
    }

    public Car(long carId, String model, String licensePlate, String seat, double pricePerDay, String status, Timestamp createdAt, double deposit, Timestamp updateAt, double adminFeePercent, String driverLicenseRequired, Brand brand, CarCategory category, User owner, List<CarImage> images, List<CarFeature> features) {
        this.carId = carId;
        this.model = model;
        this.licensePlate = licensePlate;
        this.seat = seat;
        this.pricePerDay = pricePerDay;
        this.status = status;
        this.createdAt = createdAt;
        this.deposit = deposit;
        this.updateAt = updateAt;
        this.adminFeePercent = adminFeePercent;
        this.driverLicenseRequired = driverLicenseRequired;
        this.brand = brand;
        this.category = category;
        this.owner = owner;
        this.images = images;
        this.features = features;
    }

    public List<BookingRange> getBookedRanges() {
        return bookedRanges;
    }

    public void setBookedRanges(List<BookingRange> bookedRanges) {
        this.bookedRanges = bookedRanges;
    }

    public String getDriverLicenseRequired() {
        return driverLicenseRequired;
    }

    public void setDriverLicenseRequired(String driverLicenseRequired) {
        this.driverLicenseRequired = driverLicenseRequired;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public double getAdminFeePercent() {
        return adminFeePercent;
    }

    public void setAdminFeePercent(double adminFeePercent) {
        this.adminFeePercent = adminFeePercent;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<CarImage> getImages() {
        return images;
    }

    public void setImages(List<CarImage> images) {
        this.images = images;
    }

    public List<CarFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<CarFeature> features) {
        this.features = features;
    }

}
