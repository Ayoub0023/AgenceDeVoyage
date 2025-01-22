package com.travel.flightservice.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class FlightReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("reservationId")
    private Long reservationId;

    @JsonProperty("userId") // Link to the user making the reservation
    private Long userId;

    @JsonProperty("flightId") // Link to the flight being reserved
    private Long flightId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("bookingDateTime") // Date and time of booking
    private LocalDateTime bookingDateTime;
    @JsonProperty("totalPrice") // Total price for the reservation
    private Double totalPrice;

    // Default Constructor
    public FlightReservation() {
    }

    // All-Args Constructor
    public FlightReservation(Long reservationId, Long userId, Long flightId, String status, LocalDateTime bookingDateTime, Double totalPrice) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.flightId = flightId;
        this.status = status;
        this.bookingDateTime = bookingDateTime;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
