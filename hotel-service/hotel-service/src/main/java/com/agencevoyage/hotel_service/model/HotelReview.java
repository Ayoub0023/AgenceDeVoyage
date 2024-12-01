package com.agencevoyage.hotel_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;  // The hotel being reviewed

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;  // The customer who gave the review

    private Integer rating;  // Rating from 1 to 5
    private String reviewText;
}
