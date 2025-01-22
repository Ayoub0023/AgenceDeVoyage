package com.AgenceVoyageFront.controller;

import com.AgenceVoyageFront.model.*;
import com.AgenceVoyageFront.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final HotelService hotelService;
    private final HotelReservationService hotelReservationService;
    private final FlightService flightService;
    private final FlightReservationService flightReservationService;
    private final CarService carService;




    public AdminController(UserService userService, HotelService hotelService, HotelReservationService hotelReservationService, FlightService flightService, FlightReservationService flightReservationService, CarService carService) {
        this.userService = userService;
        this.hotelService = hotelService;
        this.hotelReservationService = hotelReservationService;
        this.flightService = flightService;
        this.flightReservationService = flightReservationService;
        this.carService = carService;


    }

    // ------------------- User Management Endpoints -------------------

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------- Hotel Management Endpoints -------------------

    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @PostMapping("/hotels")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.createHotel(hotel);
        return ResponseEntity.status(201).body(createdHotel);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return hotel != null ? ResponseEntity.ok(hotel) : ResponseEntity.notFound().build();
    }

    @PutMapping("/hotels/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------- Hotel Reservation Management -------------------

    @GetMapping("/reservations")
    public ResponseEntity<List<HotelReservation>> getAllReservations() {
        List<HotelReservation> reservations = hotelReservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/reservations")
    public ResponseEntity<HotelReservation> createReservation(@RequestBody HotelReservation reservation) {
        HotelReservation createdReservation = hotelReservationService.createReservation(reservation);
        return ResponseEntity.status(201).body(createdReservation);
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<HotelReservation> getReservationById(@PathVariable Long id) {
        HotelReservation reservation = hotelReservationService.getReservationById(id);
        return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.notFound().build();
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<HotelReservation> updateReservation(@PathVariable Long id, @RequestBody HotelReservation reservation) {
        HotelReservation updatedReservation = hotelReservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        hotelReservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------- Flight Management Endpoints -------------------

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @PostMapping("/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return ResponseEntity.status(201).body(createdFlight);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return flight != null ? ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    @PutMapping("/flights/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(id, flight);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------- Flight Reservation Management -------------------

    @GetMapping("/flight-reservations")
    public ResponseEntity<List<FlightReservation>> getAllFlightReservations() {
        List<FlightReservation> reservations = flightReservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/flight-reservations")
    public ResponseEntity<FlightReservation> createFlightReservation(@RequestBody FlightReservation reservation) {
        FlightReservation createdReservation = flightReservationService.createReservation(reservation);
        return ResponseEntity.status(201).body(createdReservation);
    }

    @GetMapping("/flight-reservations/{id}")
    public ResponseEntity<FlightReservation> getFlightReservationById(@PathVariable Long id) {
        FlightReservation reservation = flightReservationService.getReservationById(id);
        return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.notFound().build();
    }

    @PutMapping("/flight-reservations/{id}")
    public ResponseEntity<FlightReservation> updateFlightReservation(@PathVariable Long id, @RequestBody FlightReservation reservation) {
        FlightReservation updatedReservation = flightReservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/flight-reservations/{id}")
    public ResponseEntity<Void> deleteFlightReservation(@PathVariable Long id) {
        flightReservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------- Car Management Endpoints -------------------

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carService.createCar(car);
        return ResponseEntity.status(201).body(createdCar);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id, car);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
