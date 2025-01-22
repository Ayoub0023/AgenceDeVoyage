package com.travel.hotel_service.controller;

import com.travel.hotel_service.model.HotelReservation;
import com.travel.hotel_service.service.HotelReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class HotelReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelReservationController.class);
    private final HotelReservationService reservationService;

    // Constructor
    public HotelReservationController(HotelReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Get all reservations.
     *
     * @return List of all reservations.
     */
    @GetMapping
    public ResponseEntity<List<HotelReservation>> getAllReservations() {
        LOGGER.info("Fetching all hotel reservations");
        List<HotelReservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    /**
     * Create a new reservation.
     *
     * @param reservation The reservation object to create.
     * @return Created reservation.
     */
    @PostMapping
    public ResponseEntity<HotelReservation> createReservation(@RequestBody HotelReservation reservation) {
        LOGGER.info("Creating a new reservation for hotel ID: {}", reservation.getHotelId());
        HotelReservation createdReservation = reservationService.createReservation(reservation);
        return ResponseEntity.status(201).body(createdReservation);
    }

    @GetMapping("/user/{userId}")
    public List<HotelReservation> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.getReservationsByUserId(userId);
    }

    /**
     * Get a reservation by ID.
     *
     * @param id The ID of the reservation to fetch.
     * @return The reservation object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HotelReservation> getReservationById(@PathVariable Long id) {
        LOGGER.info("Fetching reservation with ID: {}", id);
        HotelReservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        } else {
            LOGGER.error("Reservation not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update an existing reservation.
     *
     * @param id          The ID of the reservation to update.
     * @param reservation The updated reservation object.
     * @return Updated reservation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HotelReservation> updateReservation(@PathVariable Long id, @RequestBody HotelReservation reservation) {
        LOGGER.info("Updating reservation with ID: {}", id);
        try {
            HotelReservation updatedReservation = reservationService.updateReservation(id, reservation);
            return ResponseEntity.ok(updatedReservation);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Error updating reservation with ID: {}", id, ex);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a reservation by ID.
     *
     * @param id The ID of the reservation to delete.
     * @return ResponseEntity with status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        LOGGER.info("Deleting reservation with ID: {}", id);
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Error deleting reservation with ID: {}", id, ex);
            return ResponseEntity.notFound().build();
        }
    }
}
