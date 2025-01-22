package com.AgenceVoyageFront.service;

import com.AgenceVoyageFront.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8084/api/cars"; // Replace with the actual backend service URL

    public CarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Get all cars
    public List<Car> getAllCars() {
        Car[] cars = restTemplate.getForObject(BASE_URL, Car[].class);
        return Arrays.asList(cars);
    }

    // Get a car by ID
    public Car getCarById(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        return restTemplate.getForObject(url, Car.class);
    }

    // Create a new car
    public Car createCar(Car car) {
        return restTemplate.postForObject(BASE_URL, car, Car.class);
    }

    // Update an existing car
    public Car updateCar(Long id, Car updatedCar) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.put(url, updatedCar);
        return updatedCar; // Return the updated car
    }

    // Delete a car by ID
    public void deleteCar(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.delete(url);
    }
}
