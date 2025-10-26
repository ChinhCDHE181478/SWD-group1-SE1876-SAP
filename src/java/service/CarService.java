/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.CarDAO;
import java.util.List;
import model.Car;

/**
 *
 * @author Chinh
 */
public class CarService {
    
    public List<Car> getFilterCarList (String model, String seat, Double minPrice, Double maxPrice,
                                String status, Long categoryId, int currentPage, int itemsPerPage) {
        CarDAO c = new CarDAO();
        return c.filterCars(model, seat, minPrice, maxPrice, status, categoryId, currentPage, itemsPerPage);
    }
    
    public Car getCarById (Long id) {
        CarDAO c = new CarDAO();
        return c.getCarById(id);
    }
}
