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
public class FeatureDefinition {
    private long featureDefId;
    private String featureName;
    private String description;

    private List<CarFeature> carFeatures;

    public FeatureDefinition() {
    }

    public FeatureDefinition(long featureDefId, String featureName, String description, List<CarFeature> carFeatures) {
        this.featureDefId = featureDefId;
        this.featureName = featureName;
        this.description = description;
        this.carFeatures = carFeatures;
    }

    public long getFeatureDefId() {
        return featureDefId;
    }

    public void setFeatureDefId(long featureDefId) {
        this.featureDefId = featureDefId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CarFeature> getCarFeatures() {
        return carFeatures;
    }

    public void setCarFeatures(List<CarFeature> carFeatures) {
        this.carFeatures = carFeatures;
    }
    
    
}
