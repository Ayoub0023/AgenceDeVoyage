package com.AgenceVoyageFront.service;

import com.AgenceVoyageFront.model.HotelReservation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class HotelReservationService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8081/api/reservations"; // Backend reservation service URL

    public HotelReservationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Get all reservations
    public List<HotelReservation> getAllReservations() {
        HotelReservation[] reservations = restTemplate.getForObject(BASE_URL, HotelReservation[].class);
        return Arrays.asList(reservations);
    }

    // Get reservations by userId
    public List<HotelReservation> getReservationsByUserId(Long userId) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("userId", userId) // Add userId as a query parameter
                .toUriString();
        HotelReservation[] reservations = restTemplate.getForObject(url, HotelReservation[].class);
        return Arrays.asList(reservations);
    }

    // Get a single reservation by ID
    public HotelReservation getReservationById(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        return restTemplate.getForObject(url, HotelReservation.class);
    }

    // Create a new reservation
    public HotelReservation createReservation(HotelReservation reservation) {
        return restTemplate.postForObject(BASE_URL, reservation, HotelReservation.class);
    }

    // Update an existing reservation
    public HotelReservation updateReservation(Long id, HotelReservation updatedReservation) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.put(url, updatedReservation);
        return updatedReservation; // Return the updated reservation object
    }

    // Delete a reservation by ID
    public void deleteReservation(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.delete(url);
    }
}
