package com.travel.hotel_service.service;

import com.travel.hotel_service.model.HotelReservation;
import com.travel.hotel_service.repository.HotelReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelReservationService.class);

    private final HotelReservationRepository reservationRepository;

    public HotelReservationService(HotelReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<HotelReservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    /**
     * Get all hotel reservations.
     *
     * @return List of all reservations.
     */
    public List<HotelReservation> getAllReservations() {
        LOGGER.info("Fetching all reservations");
        return reservationRepository.findAll();
    }

    /**
     * Create a new hotel reservation.
     *
     * @param reservation The reservation object to create.
     * @return The created reservation.
     */
    public HotelReservation createReservation(HotelReservation reservation) {
        validateReservation(reservation);
        LOGGER.info("Creating new reservation for userId: {}", reservation.getUserId());
        return reservationRepository.save(reservation);
    }

    /**
     * Get a hotel reservation by ID.
     *
     * @param reservationId The ID of the reservation to fetch.
     * @return The reservation object.
     * @throws IllegalArgumentException If the reservation is not found.
     */
    public HotelReservation getReservationById(Long reservationId) {
        LOGGER.info("Fetching reservation with ID: {}", reservationId);
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> {
                    LOGGER.error("Reservation not found with ID: {}", reservationId);
                    return new IllegalArgumentException("Reservation not found with ID: " + reservationId);
                });
    }

    /**
     * Update an existing hotel reservation.
     *
     * @param id                 The ID of the reservation to update.
     * @param updatedReservation The updated reservation object.
     * @return The updated reservation.
     * @throws IllegalArgumentException If the reservation is not found.
     */
    public HotelReservation updateReservation(Long id, HotelReservation updatedReservation) {
        validateReservation(updatedReservation);

        LOGGER.info("Updating reservation with ID: {}", id);
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setUserId(updatedReservation.getUserId());
                    reservation.setHotelId(updatedReservation.getHotelId());
                    reservation.setCheckInDate(updatedReservation.getCheckInDate());
                    reservation.setCheckOutDate(updatedReservation.getCheckOutDate());
                    reservation.setStatus(updatedReservation.getStatus());
                    reservation.setTotalPrice(updatedReservation.getTotalPrice());
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> {
                    LOGGER.error("Reservation not found with ID: {}", id);
                    return new IllegalArgumentException("Reservation not found with ID: " + id);
                });
    }

    /**
     * Delete a hotel reservation by ID.
     *
     * @param id The ID of the reservation to delete.
     * @throws IllegalArgumentException If the reservation is not found.
     */
    public void deleteReservation(Long id) {
        LOGGER.info("Deleting reservation with ID: {}", id);
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            LOGGER.error("Reservation not found with ID: {}", id);
            throw new IllegalArgumentException("Reservation not found with ID: " + id);
        }
    }

    /**
     * Validate the reservation object.
     *
     * @param reservation The reservation object to validate.
     */
    private void validateReservation(HotelReservation reservation) {
        if (reservation == null) {
            LOGGER.error("Reservation object cannot be null");
            throw new IllegalArgumentException("Reservation object cannot be null");
        }

        if (reservation.getUserId() == null || reservation.getHotelId() == null) {
            LOGGER.error("Reservation userId and hotelId cannot be null");
            throw new IllegalArgumentException("Reservation userId and hotelId cannot be null");
        }

        if (reservation.getCheckInDate() == null || reservation.getCheckOutDate() == null) {
            LOGGER.error("Reservation check-in and check-out dates cannot be null");
            throw new IllegalArgumentException("Reservation check-in and check-out dates cannot be null");
        }

        if (reservation.getCheckInDate().isAfter(reservation.getCheckOutDate())) {
            LOGGER.error("Check-in date cannot be after check-out date");
            throw new IllegalArgumentException("Check-in date cannot be after check-out date");
        }
    }
}
