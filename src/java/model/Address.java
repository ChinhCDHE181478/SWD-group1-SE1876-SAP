/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Chinh
 */
public class Address {
      
    private long addressId;
    private String addressDetail;
    private String houseNumber;
    private User user;      // nếu có class User
    private Ward ward;      // Ward chứa District chứa Province
    private Province province;
    private District district;

    public Address() {}

    public Address(long addressId, String addressDetail, String houseNumber, User user, Ward ward) {
        this.addressId = addressId;
        this.addressDetail = addressDetail;
        this.houseNumber = houseNumber;
        this.user = user;
        this.ward = ward;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }
    
    
}
