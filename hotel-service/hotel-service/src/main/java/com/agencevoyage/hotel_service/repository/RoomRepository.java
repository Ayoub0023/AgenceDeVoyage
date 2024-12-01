package com.agencevoyage.hotel_service.repository;

import com.agencevoyage.hotel_service.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    // Custom queries can go here if needed
}
