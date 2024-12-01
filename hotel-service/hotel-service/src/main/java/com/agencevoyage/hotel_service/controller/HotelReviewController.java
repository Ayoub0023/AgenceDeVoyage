package com.agencevoyage.hotel_service.controller;

import com.agencevoyage.hotel_service.model.HotelReview;
import com.agencevoyage.hotel_service.service.HotelReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class HotelReviewController {

    @Autowired
    private HotelReviewService hotelReviewService;

    // Create or update review
    @PostMapping
    public ResponseEntity<HotelReview> saveReview(@RequestBody HotelReview review) {
        HotelReview savedReview = hotelReviewService.saveReview(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    // Get all reviews
    @GetMapping
    public ResponseEntity<List<HotelReview>> getAllReviews() {
        List<HotelReview> reviews = hotelReviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Get review by ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelReview> getReviewById(@PathVariable Long id) {
        Optional<HotelReview> review = hotelReviewService.getReviewById(id);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete review by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        hotelReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
