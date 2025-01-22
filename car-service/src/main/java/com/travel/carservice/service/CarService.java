package com.travel.carservice.service;

import com.travel.carservice.model.Car;
import com.travel.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Get all cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Create a new car
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    // Get a single car by ID
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    // Update an existing car
    public Car updateCar(Long id, Car updatedCar) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setModel(updatedCar.getModel());
                    car.setAgency(updatedCar.getAgency());
                    car.setPricePerDay(updatedCar.getPricePerDay());
                    car.setImageUrl(updatedCar.getImageUrl());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));
    }

    // Delete a car by ID
    public void deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Car not found with ID: " + id);
        }
    }
}

