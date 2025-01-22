package com.travel.carservice.repository;

import com.travel.carservice.model.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation, Long> {
    List<CarReservation> findByUserId(Long userId);
}

