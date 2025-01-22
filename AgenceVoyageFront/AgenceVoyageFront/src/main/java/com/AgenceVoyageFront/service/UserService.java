package com.AgenceVoyageFront.service;

import com.AgenceVoyageFront.model.LoginRequest;
import com.AgenceVoyageFront.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8082/api/users"; // Replace with actual backend User API URL

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Get all users
    public List<User> getAllUsers() {
        User[] users = restTemplate.getForObject(BASE_URL, User[].class);
        return Arrays.asList(users);
    }

    // Get a single user by ID
    public User getUserById(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        return restTemplate.getForObject(url, User.class);
    }

    // Create a new user
    public User createUser(User user) {
        return restTemplate.postForObject(BASE_URL, user, User.class);
    }

    // Update an existing user
    public void updateUser(Long id, User updatedUser) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.put(url, updatedUser);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(id))
                .toUriString();
        restTemplate.delete(url);
    }

    public User authenticateUser(String email, String password) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .pathSegment("authenticate")
                    .toUriString();

            LoginRequest loginRequest = new LoginRequest(email, password);
            return restTemplate.postForObject(url, loginRequest, User.class);
        } catch (Exception e) {
            return null;
        }
    }

    public User registerUser(User user) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .pathSegment("register")
                    .toUriString();

            return restTemplate.postForObject(url, user, User.class);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'inscription: " + e.getMessage());
        }
    }
}
