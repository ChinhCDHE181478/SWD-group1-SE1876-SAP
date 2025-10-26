/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Chinh
 */
public class Brand {
    private long brandId;
    private String brandName;

    // 1 Brand có nhiều Car
    private List<Car> cars;  

    public Brand() {
    }

    public Brand(long brandId, String brandName, List<Car> cars) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.cars = cars;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    
}
