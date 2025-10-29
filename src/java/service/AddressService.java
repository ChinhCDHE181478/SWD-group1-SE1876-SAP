/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.*;
import dal.*;
import model.*;
/**
 *
 * @author Chinh
 */
public class AddressService {
    
    public List<Province> getAllProvincesWithDistrictsAndWards(){
        ProvinceDAO provinceDAO = new ProvinceDAO();
        return provinceDAO.getAllProvincesWithDistrictsAndWards();
    }
    
    public List<District> getDistrictsByProvinceId(long provinceId) {
        DistrictDAO districtDAO = new DistrictDAO();
        List<District> list = districtDAO.getDistrictsByProvinceId(provinceId);
        return list;
    }
    
    public List<Ward> getWardsByDistrictId(long districtId) {
        WardDAO wardDAO = new WardDAO();
        List<Ward> list = wardDAO.getWardsByDistrictId(districtId);
        return list;
    }
    
    public List<Province> getAllProvince() {
        ProvinceDAO provinceDAO = new ProvinceDAO();
        return provinceDAO.getAllProvinces();
    }
    
    public static void main(String[] args) {
        AddressService a = new AddressService();
        System.out.println(a.getDistrictsByProvinceId(1).get(0));
    }
    
}
