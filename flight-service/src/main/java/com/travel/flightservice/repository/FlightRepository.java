package com.travel.flightservice.repository;

import com.travel.flightservice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
