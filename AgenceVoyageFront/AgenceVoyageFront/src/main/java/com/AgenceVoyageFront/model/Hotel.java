package com.AgenceVoyageFront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hotel {

    @JsonProperty("id")  // Maps the JSON property "id" to this field
    private Long id;

    @JsonProperty("name")  // Maps the JSON property "name" to this field
    private String name;

    @JsonProperty("location")  // Maps the JSON property "location" to this field
    private String location;

    @JsonProperty("pricePerNight")  // Maps the JSON property "pricePerNight" to this field
    private double pricePerNight;

    @JsonProperty("imagePath")  // Maps the JSON property "imagePath" to this field
    private String imagePath;

    // Default Constructor
    public Hotel() {}

    // All-Args Constructor
    public Hotel(Long id, String name, String location, double pricePerNight, String imagePath) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
