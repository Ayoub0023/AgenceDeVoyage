package com.travel.flightservice.repository;

import com.travel.flightservice.model.FlightReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {
    List<FlightReservation> findByUserId(Long userId);
}
