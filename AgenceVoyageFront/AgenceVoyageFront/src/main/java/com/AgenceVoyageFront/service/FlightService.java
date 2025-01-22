package com.AgenceVoyageFront.service;

import com.AgenceVoyageFront.model.Flight;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class FlightService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8083/api/flights"; // Replace with the actual backend service URL

    public FlightService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Get all flights
    public List<Flight> getAllFlights() {
        Flight[] flights = restTemplate.getForObject(BASE_URL, Flight[].class);
        return Arrays.asList(flights);
    }

    // Get a flight by ID
    public Flight getFlightById(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        return restTemplate.getForObject(url, Flight.class);
    }

    // Create a new flight
    public Flight createFlight(Flight flight) {
        return restTemplate.postForObject(BASE_URL, flight, Flight.class);
    }

    // Update an existing flight
    public Flight updateFlight(Long id, Flight updatedFlight) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.put(url, updatedFlight);
        return updatedFlight; // Return the updated flight
    }

    // Delete a flight by ID
    public void deleteFlight(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.delete(url);
    }
}
