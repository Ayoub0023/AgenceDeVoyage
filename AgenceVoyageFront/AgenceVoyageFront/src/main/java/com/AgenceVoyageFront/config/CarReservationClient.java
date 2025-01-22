package com.AgenceVoyageFront.config;

import com.AgenceVoyageFront.model.CarReservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8084")
public interface CarReservationClient {
    @GetMapping("/reservations/user/{userId}")
    List<CarReservation> getReservationsByUserId(@PathVariable Long userId);
}
