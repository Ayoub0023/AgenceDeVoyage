package com.agencevoyage.hotel_service.repository;

import com.agencevoyage.hotel_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom queries can go here if needed
}
