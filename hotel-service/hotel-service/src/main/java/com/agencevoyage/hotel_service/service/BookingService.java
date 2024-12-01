package com.agencevoyage.hotel_service.service;

import com.agencevoyage.hotel_service.model.Booking;
import com.agencevoyage.hotel_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Create or update booking
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    // Delete booking by ID
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
