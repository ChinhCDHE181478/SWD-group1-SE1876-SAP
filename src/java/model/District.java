/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chinh
 */
public class District {
    private long districtId;
    private String districtName;
    private Province province;
    private List<Ward> wards;

    public District() {
        this.wards = new ArrayList<>();
    }

    public District(long districtId, String districtName, Province province) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.province = province;
        this.wards = new ArrayList<>();
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }
    
    
}
