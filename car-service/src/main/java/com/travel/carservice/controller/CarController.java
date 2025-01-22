package com.travel.carservice.controller;

import com.travel.carservice.model.Car;
import com.travel.carservice.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Get all cars.
     *
     * @return List of all cars.
     */
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    /**
     * Create a new car.
     *
     * @param car The car object to create.
     * @return The created car.
     */
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.createCar(car));
    }

    /**
     * Get a car by ID.
     *
     * @param id The ID of the car.
     * @return The car object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update a car by ID.
     *
     * @param id      The ID of the car to update.
     * @param updatedCar The updated car object.
     * @return The updated car.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return ResponseEntity.ok(carService.updateCar(id, updatedCar));
    }

    /**
     * Delete a car by ID.
     *
     * @param id The ID of the car to delete.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
