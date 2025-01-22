package com.travel.flightservice.controller;
import com.travel.flightservice.model.FlightReservation;
import com.travel.flightservice.service.FlightReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight-reservations")
public class FlightReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightReservationController.class);
    private final FlightReservationService reservationService;

    // Constructor
    public FlightReservationController(FlightReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Get all reservations.
     *
     * @return List of all reservations.
     */
    @GetMapping
    public ResponseEntity<List<FlightReservation>> getAllReservations() {
        LOGGER.info("Fetching all flight reservations");
        List<FlightReservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    /**
     * Create a new reservation.
     *
     * @param reservation The reservation object to create.
     * @return Created reservation.
     */
    @PostMapping
    public ResponseEntity<FlightReservation> createReservation(@RequestBody FlightReservation reservation) {
        LOGGER.info("Creating a new reservation for flight ID: {}", reservation.getFlightId());
        FlightReservation createdReservation = reservationService.createReservation(reservation);
        return ResponseEntity.status(201).body(createdReservation);
    }

    /**
     * Get a reservation by ID.
     *
     * @param id The ID of the reservation to fetch.
     * @return The reservation object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FlightReservation> getReservationById(@PathVariable Long id) {
        LOGGER.info("Fetching reservation with ID: {}", id);
        FlightReservation reservation = reservationService.getReservationById(id);
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
    public ResponseEntity<FlightReservation> updateReservation(@PathVariable Long id, @RequestBody FlightReservation reservation) {
        LOGGER.info("Updating reservation with ID: {}", id);
        try {
            FlightReservation updatedReservation = reservationService.updateReservation(id, reservation);
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

