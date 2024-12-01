package com.agencevoyage.hotel_service.service;

import com.agencevoyage.hotel_service.model.HotelReview;
import com.agencevoyage.hotel_service.repository.HotelReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelReviewService {

    @Autowired
    private HotelReviewRepository hotelReviewRepository;

    // Create or update hotel review
    public HotelReview saveReview(HotelReview hotelReview) {
        return hotelReviewRepository.save(hotelReview);
    }

    // Get all reviews
    public List<HotelReview> getAllReviews() {
        return hotelReviewRepository.findAll();
    }

    // Get review by ID
    public Optional<HotelReview> getReviewById(Long reviewId) {
        return hotelReviewRepository.findById(reviewId);
    }

    // Get reviews for a specific hotel
    public List<HotelReview> getReviewsByHotel(Long hotelId) {
        return hotelReviewRepository.findAllByHotelId(hotelId);
    }

    // Delete review by ID
    public void deleteReview(Long reviewId) {
        hotelReviewRepository.deleteById(reviewId);
    }
}
