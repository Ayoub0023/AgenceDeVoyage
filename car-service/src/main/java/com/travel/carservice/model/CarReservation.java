package com.travel.carservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class CarReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("userId") // Link to the user making the reservation
    private Long userId;

    @JsonProperty("carId")
    private Long carId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("rentalEndDate")
    private LocalDate rentalEndDate;

    @JsonProperty("rentalStartDate")
    private LocalDate rentalStartDate;
    @JsonProperty("totalPrice") // Total price for the reservation
    private Double totalPrice;
    public CarReservation() {}
    public CarReservation(Long id, Long carId, String status, LocalDate rentalEndDate, LocalDate rentalStartDate,Double totalPrice) {
        this.id = id;
        this.carId = carId;
        this.status = status;
        this.rentalEndDate = rentalEndDate;
        this.rentalStartDate = rentalStartDate;
        this.totalPrice = totalPrice;
    }
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
    public Long getCarId() {
        return carId;
    }
    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }
    public void setRentalEndDate(LocalDate rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }
    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }
    public void setRentalStartDate(LocalDate rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


}
