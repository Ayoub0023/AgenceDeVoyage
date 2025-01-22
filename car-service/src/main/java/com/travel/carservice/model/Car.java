package com.travel.carservice.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("model")
    private String model;
    @JsonProperty("agency")
    private String agency;
    @JsonProperty("pricePerDay")
    private double pricePerDay;
    @JsonProperty("imageUrl")
    private String imageUrl;
    public Car (){}
public Car(String model, String agency, double pricePerDay, String imageUrl) {
        this.model = model;
        this.agency = agency;
        this.pricePerDay = pricePerDay;
        this.imageUrl = imageUrl;

}
public Long getId() {
        return id;
}
public void setId(Long id) {
        this.id = id;
}
public String getModel() {
        return model;
}
public void setModel(String model) {
        this.model = model;
}
public String getAgency() {
        return agency;
}
public void setAgency(String agency) {
        this.agency = agency;
}
public double getPricePerDay() {
        return pricePerDay;
}
public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
}
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
