package com.AgenceVoyageFront.config;

import com.AgenceVoyageFront.model.FlightReservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "flight-service", url = "http://localhost:8083")
public interface FlightReservationClient {
    @GetMapping("/reservations/user/{userId}")
    List<FlightReservation> getReservationsByUserId(@PathVariable Long userId);
}
