package com.travel.flightservice.service;
import com.travel.flightservice.model.Flight;
import com.travel.flightservice.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FlightService{
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Get all flights
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Create a new flight
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Get a single flight by ID
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    // Update an existing flight
    public Flight updateFlight(Long id, Flight updatedFlight) {
        return flightRepository.findById(id)
                .map(flight -> {
                    flight.setAirline(updatedFlight.getAirline());
                    flight.setDeparture(updatedFlight.getDeparture());
                    flight.setArrival(updatedFlight.getArrival());
                    flight.setPrice(updatedFlight.getPrice());
                    return flightRepository.save(flight);
                })
                .orElseThrow(() -> new IllegalArgumentException("Flight not found with ID: " + id));
    }

    // Delete a flight by ID
    public void deleteFlight(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Flight not found with ID: " + id);
        }
    }
}


