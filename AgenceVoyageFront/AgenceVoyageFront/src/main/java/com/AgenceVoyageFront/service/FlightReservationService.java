package com.AgenceVoyageFront.service;

import com.AgenceVoyageFront.model.FlightReservation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class FlightReservationService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8083/api/flight-reservations"; // Replace with the flight reservation service URL

    public FlightReservationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Get all flight reservations
    public List<FlightReservation> getAllReservations() {
        FlightReservation[] reservations = restTemplate.getForObject(BASE_URL, FlightReservation[].class);
        return Arrays.asList(reservations);
    }

    // Get flight reservations by userId
    public List<FlightReservation> getReservationsByUserId(Long userId) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("userId", userId) // Add userId as a query parameter
                .toUriString();
        FlightReservation[] reservations = restTemplate.getForObject(url, FlightReservation[].class);
        return Arrays.asList(reservations);
    }

    // Get a single flight reservation by ID
    public FlightReservation getReservationById(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        return restTemplate.getForObject(url, FlightReservation.class);
    }

    // Create a new flight reservation
    public FlightReservation createReservation(FlightReservation reservation) {
        return restTemplate.postForObject(BASE_URL, reservation, FlightReservation.class);
    }

    // Update an existing flight reservation
    public FlightReservation updateReservation(Long id, FlightReservation updatedReservation) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.put(url, updatedReservation);
        return updatedReservation; // Return the updated reservation object
    }

    // Delete a flight reservation by ID
    public void deleteReservation(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.delete(url);
    }
}
