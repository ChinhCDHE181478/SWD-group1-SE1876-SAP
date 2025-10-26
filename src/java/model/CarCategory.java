/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Chinh
 */
import java.util.List;

public class CarCategory {
    private long cateId;
    private String cateName;

    private List<Car> cars;

    public CarCategory() {
    }

    public CarCategory(long cateId, String cateName, List<Car> cars) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.cars = cars;
    }

    public long getCateId() {
        return cateId;
    }

    public void setCateId(long cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    
    
}
