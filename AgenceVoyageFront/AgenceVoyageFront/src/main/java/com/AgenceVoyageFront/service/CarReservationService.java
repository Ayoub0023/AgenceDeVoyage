package com.AgenceVoyageFront.service;

import com.AgenceVoyageFront.model.CarReservation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class CarReservationService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8084/api/car-reservations"; // Replace with the actual backend reservation service URL

    public CarReservationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Get all car reservations
    public List<CarReservation> getAllCarReservations() {
        CarReservation[] reservations = restTemplate.getForObject(BASE_URL, CarReservation[].class);
        return Arrays.asList(reservations);
    }

    // Get car reservations by userId
    public List<CarReservation> getReservationsByUserId(Long userId) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("user") // Append "user" to the URL path
                .pathSegment(String.valueOf(userId)) // Append the userId to the URL path
                .toUriString();
        CarReservation[] reservations = restTemplate.getForObject(url, CarReservation[].class);
        return Arrays.asList(reservations);
    }

    // Get a single car reservation by ID
    public CarReservation getReservationById(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        return restTemplate.getForObject(url, CarReservation.class);
    }

    // Create a new car reservation
    public CarReservation createReservation(CarReservation reservation) {
        return restTemplate.postForObject(BASE_URL, reservation, CarReservation.class);
    }

    // Update an existing car reservation
    public CarReservation updateReservation(Long id, CarReservation updatedReservation) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.put(url, updatedReservation);
        return updatedReservation; // Return the updated reservation object
    }

    // Delete a car reservation by ID
    public void deleteReservation(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.delete(url);
    }
}
