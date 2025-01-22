package com.travel.userservice.service;

import com.travel.userservice.model.User;
import com.travel.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User authenticateUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User createUser(User user) {
        // Vous pourriez ajouter ici la logique pour hasher le mot de passe
        return userRepository.save(user);
    }
}

