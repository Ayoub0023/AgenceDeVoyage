package com.travel.userservice.controller;


import com.travel.userservice.model.LoginRequest;
import com.travel.userservice.model.User;
import com.travel.userservice.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.authenticateUser(email, password);

        if (user != null) {
            // Store user information in session
            session.setAttribute("username", user.getName());
            session.setAttribute("userRole", user.getRole());

            // Redirect based on role
            if ("admin".equals(user.getRole())) {
                return "redirect:/manage-hotels";
            } else {
                return "redirect:/";
            }
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticate(@RequestBody LoginRequest loginRequest) {
        User user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            // S'assurer que le rôle est en majuscules
            if (user.getRole() != null) {
                user.setRole(user.getRole().toUpperCase());
            }
            System.out.println("Authentification réussie pour: " + user.getName() + " avec le rôle: " + user.getRole());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Vérifier si l'email existe déjà
            if (userService.existsByEmail(user.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Un compte existe déjà avec cet email");
            }

            // Définir le rôle par défaut comme "USER"
            user.setRole("USER");

            // Créer le nouvel utilisateur
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'inscription");
        }
    }

}

