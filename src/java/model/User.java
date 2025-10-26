/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Chinh
 */
public class User {
    private long userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Role role;
    private Timestamp createdAt;
    private boolean isDelete;
    private User createdBy;
    private String avatar;
    private String status;

    // 1 user có thể sở hữu nhiều xe
    private List<Car> carsOwned;

    public User() {
    }

    public User(long userId, String name, String email, String password, String phone, Role role, Timestamp createdAt, boolean isDelete, User createdBy, String avatar, String status, List<Car> carsOwned) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.createdAt = createdAt;
        this.isDelete = isDelete;
        this.createdBy = createdBy;
        this.avatar = avatar;
        this.status = status;
        this.carsOwned = carsOwned;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Car> getCarsOwned() {
        return carsOwned;
    }

    public void setCarsOwned(List<Car> carsOwned) {
        this.carsOwned = carsOwned;
    }
    
    
}
