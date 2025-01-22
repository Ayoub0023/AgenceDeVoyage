package com.travel.flightservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8082")
public interface UserClient {

    @GetMapping("/users/{id}")
    Object getUserById(@PathVariable Long id);
}

