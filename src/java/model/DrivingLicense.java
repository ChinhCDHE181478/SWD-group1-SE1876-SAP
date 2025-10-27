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

public class DrivingLicense {
    private long licenseId;
    private String licenseNumber;
    private String licenseClass;
    private Timestamp issueDate;
    private Timestamp expiredDate;
    private Timestamp updatedAt;

    // 1 user <-> 1 driving license
    private User user;

    // Getters & Setters
    public long getLicenseId() { return licenseId; }
    public void setLicenseId(long licenseId) { this.licenseId = licenseId; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getLicenseClass() { return licenseClass; }
    public void setLicenseClass(String licenseClass) { this.licenseClass = licenseClass; }

    public Timestamp getIssueDate() { return issueDate; }
    public void setIssueDate(Timestamp issueDate) { this.issueDate = issueDate; }

    public Timestamp getExpiredDate() { return expiredDate; }
    public void setExpiredDate(Timestamp expiredDate) { this.expiredDate = expiredDate; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
