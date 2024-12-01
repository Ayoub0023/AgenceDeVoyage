package com.agencevoyage.hotel_service.repository;

import com.agencevoyage.hotel_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Custom queries can go here if needed
}
