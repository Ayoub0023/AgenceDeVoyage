package com.agencevoyage.hotel_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id  // Only the jakarta.persistence.Id is needed for JPA entities
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;  // Foreign key reference to the Hotel entity

    private String roomType;
    private Double price;
    private Integer capacity;
}
