package com.AgenceVoyageFront.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;


public class Flight {
    @JsonProperty("id")  // Maps the JSON property "id" to this field
    private Long id;
    @JsonProperty("airline")
    private String airline;

    @JsonProperty("departure")
    private String departure;

    @JsonProperty("arrival")
    private String arrival;

    @JsonProperty("departureDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    @JsonProperty("departureTime")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    @JsonProperty("arrivalDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @JsonProperty("arrivalTime")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;

    @JsonProperty("price")
    private double price;
    public Flight() {}
    public Flight(Long id, String airline, String departure, String arrival,LocalDate departureDate, LocalTime departureTime,LocalDate arrivalDate,LocalTime arrivalTime, double price) {
        this.id = id;
        this.airline = airline;
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
