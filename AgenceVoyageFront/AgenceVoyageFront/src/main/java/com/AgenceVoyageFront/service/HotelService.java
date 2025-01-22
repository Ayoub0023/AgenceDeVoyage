package com.AgenceVoyageFront.service;

import com.AgenceVoyageFront.model.Hotel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class HotelService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8081/api/hotels"; // Replace with the actual backend service URL

    public HotelService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Get all hotels
    public List<Hotel> getAllHotels() {
        Hotel[] hotels = restTemplate.getForObject(BASE_URL, Hotel[].class);
        return Arrays.asList(hotels);
    }

    // Get a hotel by ID
    public Hotel getHotelById(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        return restTemplate.getForObject(url, Hotel.class);
    }

    // Create a new hotel
    public Hotel createHotel(Hotel hotel) {
        return restTemplate.postForObject(BASE_URL, hotel, Hotel.class);
    }

    // Update an existing hotel
    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.put(url, updatedHotel);
        return updatedHotel; // Return the updated hotel
    }

    // Delete a hotel by ID
    public void deleteHotel(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.delete(url);
    }
}
