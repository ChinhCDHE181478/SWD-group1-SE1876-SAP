/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;

/**
 *
 * @author Chinh
 */
public class Ward {
    private long wardId;
    private String wardName;
    private District district;
    private List<Address> addresses;

    public Ward() {
        this.addresses = new ArrayList<>();
    }

    public Ward(long wardId, String wardName, District district) {
        this.wardId = wardId;
        this.wardName = wardName;
        this.district = district;
        this.addresses = new ArrayList<>();
    }

    public long getWardId() {
        return wardId;
    }

    public void setWardId(long wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
    
    
    
}
