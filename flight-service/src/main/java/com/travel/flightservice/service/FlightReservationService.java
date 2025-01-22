package com.travel.flightservice.service;
import com.travel.flightservice.model.FlightReservation;
import com.travel.flightservice.repository.FlightReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlightReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightReservationService.class);

    private final FlightReservationRepository flightReservationRepository;

    public FlightReservationService(FlightReservationRepository flightReservationRepository) {
        this.flightReservationRepository = flightReservationRepository;
    }

    public List<FlightReservation> getReservationsByUserId(Long userId) {
        return flightReservationRepository.findByUserId(userId);
    }

    /**
     * Get all flight reservations.
     *
     * @return List of all flight reservations.
     */
    public List<FlightReservation> getAllReservations() {
        LOGGER.info("Fetching all flight reservations");
        return flightReservationRepository.findAll();
    }

    /**
     * Create a new flight reservation.
     *
     * @param reservation The reservation object to create.
     * @return The created reservation.
     */
    public FlightReservation createReservation(FlightReservation reservation) {
        validateReservation(reservation);
        LOGGER.info("Creating new flight reservation for userId: {}", reservation.getUserId());
        return flightReservationRepository.save(reservation);
    }

    /**
     * Get a flight reservation by ID.
     *
     * @param reservationId The ID of the reservation to fetch.
     * @return The reservation object.
     * @throws IllegalArgumentException If the reservation is not found.
     */
    public FlightReservation getReservationById(Long reservationId) {
        LOGGER.info("Fetching flight reservation with ID: {}", reservationId);
        return flightReservationRepository.findById(reservationId)
                .orElseThrow(() -> {
                    LOGGER.error("Reservation not found with ID: {}", reservationId);
                    return new IllegalArgumentException("Reservation not found with ID: " + reservationId);
                });
    }

    /**
     * Update an existing flight reservation.
     *
     * @param id                    The ID of the reservation to update.
     * @param updatedReservation    The updated reservation object.
     * @return The updated reservation.
     * @throws IllegalArgumentException If the reservation is not found.
     */
    public FlightReservation updateReservation(Long id, FlightReservation updatedReservation) {
        validateReservation(updatedReservation);

        LOGGER.info("Updating flight reservation with ID: {}", id);
        return flightReservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setUserId(updatedReservation.getUserId());
                    reservation.setFlightId(updatedReservation.getFlightId());
                    reservation.setBookingDateTime(updatedReservation.getBookingDateTime());
                    reservation.setStatus(updatedReservation.getStatus());
                    reservation.setTotalPrice(updatedReservation.getTotalPrice());

                    return flightReservationRepository.save(reservation);
                })
                .orElseThrow(() -> {
                    LOGGER.error("Reservation not found with ID: {}", id);
                    return new IllegalArgumentException("Reservation not found with ID: " + id);
                });
    }

    /**
     * Delete a flight reservation by ID.
     *
     * @param id The ID of the reservation to delete.
     * @throws IllegalArgumentException If the reservation is not found.
     */
    public void deleteReservation(Long id) {
        LOGGER.info("Deleting flight reservation with ID: {}", id);
        if (flightReservationRepository.existsById(id)) {
            flightReservationRepository.deleteById(id);
        } else {
            LOGGER.error("Reservation not found with ID: {}", id);
            throw new IllegalArgumentException("Reservation not found with ID: " + id);
        }
    }

    /**
     * Validate the flight reservation object.
     *
     * @param reservation The reservation object to validate.
     */
    private void validateReservation(FlightReservation reservation) {
        if (reservation == null) {
            LOGGER.error("Reservation object cannot be null");
            throw new IllegalArgumentException("Reservation object cannot be null");
        }

        if (reservation.getUserId() == null || reservation.getFlightId() == null) {
            LOGGER.error("Reservation userId and flightId cannot be null");
            throw new IllegalArgumentException("Reservation userId and flightId cannot be null");
        }

        if (reservation.getBookingDateTime() == null) {
            LOGGER.error("Reservation bookingDateTime cannot be null");
            throw new IllegalArgumentException("Reservation bookingDateTime cannot be null");
        }
    }
}