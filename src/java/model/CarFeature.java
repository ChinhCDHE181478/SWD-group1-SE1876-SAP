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
public class CarFeature {
    private long featureId;
    private String featureValue;

    private Car car;                     // n-1
    private FeatureDefinition definition; // n-1

    public CarFeature() {
    }

    public CarFeature(long featureId, String featureValue, Car car, FeatureDefinition definition) {
        this.featureId = featureId;
        this.featureValue = featureValue;
        this.car = car;
        this.definition = definition;
    }

    public long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(long featureId) {
        this.featureId = featureId;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public FeatureDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(FeatureDefinition definition) {
        this.definition = definition;
    }
    
    
    
}
