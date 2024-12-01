package com.agencevoyage.hotel_service.repository;

import com.agencevoyage.hotel_service.model.HotelReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelReviewRepository extends JpaRepository<HotelReview, Long> {
    List<HotelReview> findAllByHotelId(Long hotelId);
    // Custom queries can go here if needed
}
