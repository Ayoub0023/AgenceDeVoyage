package com.travel.hotel_service.service;

import com.travel.hotel_service.model.Hotel;
import com.travel.hotel_service.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // Get all hotels
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Create a new hotel
    public Hotel createHotel(Hotel hotel) {
        if (hotel.getImagePath() == null || hotel.getImagePath().isEmpty()) {
            hotel.setImagePath("default-image-path.jpg"); // Default image path if not provided
        }
        return hotelRepository.save(hotel);
    }

    // Get a single hotel by ID
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    // Update an existing hotel
    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setName(updatedHotel.getName());
                    hotel.setLocation(updatedHotel.getLocation());
                    hotel.setPricePerNight(updatedHotel.getPricePerNight());
                    hotel.setImagePath(updatedHotel.getImagePath()); // Update image path
                    return hotelRepository.save(hotel);
                })
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found with ID: " + id));
    }

    // Delete a hotel by ID
    public void deleteHotel(Long id) {
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Hotel not found with ID: " + id);
        }
    }
}
