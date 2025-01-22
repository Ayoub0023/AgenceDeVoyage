package com.travel.carservice.controller;

import com.travel.carservice.model.CarReservation;
import com.travel.carservice.service.CarReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-reservations")
public class CarReservationController {

    private final CarReservationService reservationService;

    public CarReservationController(CarReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/user/{userId}")
    public List<CarReservation> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.getReservationsByUserId(userId);
    }


    /**
     * Get all car reservations.
     *
     * @return List of all reservations.
     */
    @GetMapping
    public ResponseEntity<List<CarReservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    /**
     * Create a new car reservation.
     *
     * @param reservation The reservation object to create.
     * @return The created reservation.
     */
    @PostMapping
    public ResponseEntity<CarReservation> createReservation(@RequestBody CarReservation reservation) {
        return ResponseEntity.ok(reservationService.createReservation(reservation));
    }

    /**
     * Get a car reservation by ID.
     *
     * @param id The ID of the reservation.
     * @return The reservation object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarReservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    /**
     * Update a car reservation by ID.
     *
     * @param id               The ID of the reservation to update.
     * @param updatedReservation The updated reservation object.
     * @return The updated reservation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarReservation> updateReservation(@PathVariable Long id, @RequestBody CarReservation updatedReservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, updatedReservation));
    }

    /**
     * Delete a car reservation by ID.
     *
     * @param id The ID of the reservation to delete.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
