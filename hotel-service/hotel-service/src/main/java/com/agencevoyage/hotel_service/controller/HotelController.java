package com.agencevoyage.hotel_service.controller;

import com.agencevoyage.hotel_service.model.Hotel;
import com.agencevoyage.hotel_service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Get all hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // Get hotel by ID
    @GetMapping("/{id}")
    public Optional<Hotel> getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    // Create or update hotel
    @PostMapping
    public Hotel createOrUpdateHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }

    // Delete hotel by ID
    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}
