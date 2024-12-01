package com.agencevoyage.hotel_service.repository;

import com.agencevoyage.hotel_service.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // Custom queries can go here if needed
}
