package com.travel.hotel_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HotelReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId") // Link to the user making the reservation
    private Long userId;

    @JsonProperty("hotelId")
    private Long hotelId;

    @JsonProperty("checkInDate")
    private LocalDate checkInDate;

    @JsonProperty("checkOutDate")
    private LocalDate checkOutDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("totalPrice") // Total price for the reservation
    private Double totalPrice;

    // Default Constructor
    public HotelReservation() {
    }

    // All-Args Constructor
    public HotelReservation(Long id, Long userId, Long hotelId, LocalDate checkInDate, LocalDate checkOutDate, String status, Double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
