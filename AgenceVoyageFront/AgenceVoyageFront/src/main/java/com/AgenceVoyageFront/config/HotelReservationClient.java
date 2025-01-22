package com.AgenceVoyageFront.config;

import com.AgenceVoyageFront.model.HotelReservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "hotel-service", url = "http://localhost:8081")
public interface HotelReservationClient {
    @GetMapping("/reservations/user/{userId}")
    List<HotelReservation> getReservationsByUserId(@PathVariable Long userId);
}
