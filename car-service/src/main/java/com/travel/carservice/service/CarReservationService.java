package com.travel.carservice.service;

import com.travel.carservice.model.CarReservation;
import com.travel.carservice.repository.CarReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarReservationService.class);

    private final CarReservationRepository reservationRepository;

    public CarReservationService(CarReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<CarReservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    /**
     * Get all car reservations.
     *
     * @return List of all reservations.
     */
    public List<CarReservation> getAllReservations() {
        LOGGER.info("Fetching all car reservations");
        return reservationRepository.findAll();
    }

    /**
     * Create a new car reservation.
     *
     * @param reservation The reservation object to create.
     * @return The created reservation.
     */
    public CarReservation createReservation(CarReservation reservation) {
        validateReservation(reservation);
        LOGGER.info("Creating new car reservation for userId: {}", reservation.getUserId());
        return reservationRepository.save(reservation);
    }

    /**
     * Get a car reservation by ID.
     *
     * @param reservationId The ID of the reservation to fetch.
     * @return The reservation object.
     * @throws IllegalArgumentException If the reservation is not found.
     */
    public CarReservation getReservationById(Long reservationId) {
        LOGGER.info("Fetching car reservation with ID: {}", reservationId);
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> {
                    LOGGER.error("Car reservation not found with ID: {}", reservationId);
                    return new IllegalArgumentException("Car reservation not found with ID: " + reservationId);
                });
    }

    /**
     * Update an existing car reservation.
     *
     * @param id                 The ID of the reservation to update.
     * @param updatedReservation The updated reservation object.
     * @return The updated reservation.
     * @throws IllegalArgumentException If the reservation is not found.
     */
    public CarReservation updateReservation(Long id, CarReservation updatedReservation) {
        validateReservation(updatedReservation);

        LOGGER.info("Updating car reservation with ID: {}", id);
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setUserId(updatedReservation.getUserId());
                    reservation.setCarId(updatedReservation.getCarId());
                    reservation.setRentalStartDate(updatedReservation.getRentalStartDate());
                    reservation.setRentalEndDate(updatedReservation.getRentalEndDate());
                    reservation.setStatus(updatedReservation.getStatus());
                    reservation.setTotalPrice(updatedReservation.getTotalPrice());
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> {
                    LOGGER.error("Car reservation not found with ID: {}", id);
                    return new IllegalArgumentException("Car reservation not found with ID: " + id);
                });
    }

    /**
     * Delete a car reservation by ID.
     *
     * @param id The ID of the reservation to delete.
     * @throws IllegalArgumentException If the reservation is not found.
     */
    public void deleteReservation(Long id) {
        LOGGER.info("Deleting car reservation with ID: {}", id);
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            LOGGER.error("Car reservation not found with ID: {}", id);
            throw new IllegalArgumentException("Car reservation not found with ID: " + id);
        }
    }

    /**
     * Validate the reservation object.
     *
     * @param reservation The reservation object to validate.
     */
    private void validateReservation(CarReservation reservation) {
        if (reservation == null) {
            LOGGER.error("Car reservation object cannot be null");
            throw new IllegalArgumentException("Car reservation object cannot be null");
        }

        if (reservation.getUserId() == null || reservation.getCarId() == null) {
            LOGGER.error("Car reservation userId and carId cannot be null");
            throw new IllegalArgumentException("Car reservation userId and carId cannot be null");
        }

        if (reservation.getRentalStartDate() == null || reservation.getRentalEndDate() == null) {
            LOGGER.error("Car reservation pick-up and drop-off dates cannot be null");
            throw new IllegalArgumentException("Car reservation pick-up and drop-off dates cannot be null");
        }

        if (reservation.getRentalStartDate().isAfter(reservation.getRentalEndDate())) {
            LOGGER.error("Pick-up date cannot be after drop-off date");
            throw new IllegalArgumentException("Pick-up date cannot be after drop-off date");
        }
    }
}
