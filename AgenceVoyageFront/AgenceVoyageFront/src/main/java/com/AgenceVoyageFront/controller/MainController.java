package com.AgenceVoyageFront.controller;

import com.AgenceVoyageFront.model.*;
import com.AgenceVoyageFront.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/api/front")
public class MainController {

    public static int logged = 0;

    private final HotelService hotelService;
    private final FlightService flightService;
    private final CarService carService;
    private final UserService userService;

    private final HotelReservationService hotelReservationService;
    private final FlightReservationService flightReservationService;
    private final CarReservationService carReservationService;



    public MainController(HotelService hotelService, FlightService flightService, CarService carService, UserService userService, HotelReservationService hotelReservationService,
                          FlightReservationService flightReservationService,
                          CarReservationService carReservationService) {
        this.hotelService = hotelService;
        this.flightService = flightService;
        this.carService = carService;
        this.userService = userService;
        this.hotelReservationService = hotelReservationService;
        this.flightReservationService = flightReservationService;
        this.carReservationService = carReservationService;
    }

    @GetMapping("/")
    public String home() {
        return "index";  // Pointera vers templates/index.html
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        try {
            System.out.println("Tentative de connexion pour: " + email); // Debug
            User authenticatedUser = userService.authenticateUser(email, password);

            if (authenticatedUser != null) {
                System.out.println("Utilisateur authentifié avec le rôle: " + authenticatedUser.getRole()); // Debug
                session.setAttribute("username", authenticatedUser.getUsername());
                session.setAttribute("userRole", authenticatedUser.getRole().toUpperCase());
                session.setAttribute("userId", authenticatedUser.getId()); // Store userId in the session
                System.out.println("Utilisateur connecté : "+ session.getAttribute("userId"));
                logged = 1;

                // Check if the user is an ADMIN
                if (authenticatedUser.getRole() != null &&
                        authenticatedUser.getRole().toUpperCase().equals("ADMIN")) {
                    return "redirect:/admin/manage-hotels";
                } else {
                    return "redirect:/";
                }
            } else {
                model.addAttribute("error", "Email ou mot de passe incorrect");
                return "login";
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion: " + e.getMessage()); // Debug
            model.addAttribute("error", "Erreur lors de la connexion");
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        logged=0;
        return "redirect:/";
    }
    @GetMapping("/admin/**")
    public String adminCheck(HttpSession session) {
        String userRole = (String) session.getAttribute("userRole");
        if (userRole == null || !userRole.equals("ADMIN")) {
            return "redirect:/login";
        }
        return null;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user,
                                      Model model) {
        try {
            User registeredUser = userService.registerUser(user);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'inscription: " + e.getMessage());
            return "register";
        }
    }

    // Serve the hotel management page
    @GetMapping("/admin/manage-hotels")
    public String manageHotelsPage(Model model) {
        if(logged==0) return("index");
        List<Hotel> hotels = hotelService.getAllHotels(); // Fetch the hotels list
        model.addAttribute("hotels", hotels); // Add the list to the model
        return "admin/manage-hotels"; // Maps to src/main/resources/templates/admin/manage-hotels.html
    }

    // Serve the reservations management page
    @GetMapping("/admin/manage-reservations")
    public String manageReservationsPage() {
        if(logged==0) return("index");
        return "admin/manage-reservations"; // Maps to src/main/resources/templates/admin/manage-reservations.html
    }

    // Serve the flight management page
    @GetMapping("/admin/manage-flights")
    public String manageFlightsPage(Model model) {
        if(logged==0) return("index");
        List<Flight> flights = flightService.getAllFlights(); // Fetch the flights list
        model.addAttribute("flights", flights); // Add the list to the model
        return "admin/manage-flights"; // Maps to src/main/resources/templates/admin/manage-flights.html
    }

    // Serve the flight reservations management page
    @GetMapping("/admin/manage-flight-reservations")
    public String manageFlightReservationsPage() {
        if(logged==0) return("index");
        return "admin/manage-flight-reservations"; // Maps to src/main/resources/templates/admin/manage-flight-reservations.html
    }

    @GetMapping("/client/client-booking")
    public String clientBookingPage(Model model) {
        if(logged==0) return("index");
        // Fetch the list of hotels, flights, and cars
        List<Hotel> hotels = hotelService.getAllHotels();
        List<Flight> flights = flightService.getAllFlights();
        List<Car> cars = carService.getAllCars(); // Assuming you have a CarService

        // Add the lists to the model
        model.addAttribute("hotels", hotels);
        model.addAttribute("flights", flights);
        model.addAttribute("cars", cars);

        return "client/client-booking"; // Maps to src/main/resources/templates/client/client-booking.html
    }


    // Serve the car management page
    @GetMapping("/admin/manage-cars")
    public String manageCarsPage(Model model) {
        if(logged==0) return("index");
        List<Car> cars = carService.getAllCars(); // Fetch the cars list
        model.addAttribute("cars", cars); // Add the list to the model
        return "admin/manage-cars"; // Maps to src/main/resources/templates/admin/manage-cars.html
    }

    @GetMapping("/client/my-reservations")
    public String getMyReservations(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        System.out.println("User ID from session: " + userId);

        if (userId == null) {
            return "redirect:/login"; // Redirect to login if the user is not authenticated
        }

        try {
            // Fetch reservations using userId from reservation services
            List<HotelReservation> hotelReservations = hotelReservationService.getReservationsByUserId(userId);
            List<FlightReservation> flightReservations = flightReservationService.getReservationsByUserId(userId);
            List<CarReservation> carReservations = carReservationService.getReservationsByUserId(userId);

            System.out.println("Fetched Hotel Reservations: " + hotelReservations.size());
            System.out.println("Fetched Flight Reservations: " + flightReservations.size());
            System.out.println("Fetched Car Reservations: " + carReservations.size());

            // Add reservations to the model
            model.addAttribute("hotelReservations", hotelReservations);
            model.addAttribute("flightReservations", flightReservations);
            model.addAttribute("carReservations", carReservations);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while fetching reservations.");
        }

        return "client/my-reservations"; // Maps to templates/client/my-reservations.html
    }

    @GetMapping("/admin/statistics")
    public String getStatisticsPage(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        try {
            // Fetch reservations for the user
            List<HotelReservation> hotelReservations = hotelReservationService.getReservationsByUserId(userId);
            List<FlightReservation> flightReservations = flightReservationService.getReservationsByUserId(userId);
            List<CarReservation> carReservations = carReservationService.getReservationsByUserId(userId);

            // Calculate total gains for today, month, and year
            double dailyGains = 0;
            double monthlyGains = 0;
            double yearlyGains = 0;

            LocalDate today = LocalDate.now();
            LocalDate firstDayOfMonth = today.withDayOfMonth(1);
            LocalDate firstDayOfYear = today.withDayOfYear(1);

            // Sum gains from hotel reservations
            for (HotelReservation reservation : hotelReservations) {
                LocalDate checkInDate = reservation.getCheckInDate();
                if (checkInDate.isEqual(today)) {
                    dailyGains += reservation.getTotalPrice();
                }
                if (!checkInDate.isBefore(firstDayOfMonth)) {
                    monthlyGains += reservation.getTotalPrice();
                }
                if (!checkInDate.isBefore(firstDayOfYear)) {
                    yearlyGains += reservation.getTotalPrice();
                }
            }

            // Sum gains from flight reservations
            for (FlightReservation reservation : flightReservations) {
                LocalDate bookingDate = reservation.getBookingDateTime().toLocalDate();
                if (bookingDate.isEqual(today)) {
                    dailyGains += reservation.getTotalPrice();
                }
                if (!bookingDate.isBefore(firstDayOfMonth)) {
                    monthlyGains += reservation.getTotalPrice();
                }
                if (!bookingDate.isBefore(firstDayOfYear)) {
                    yearlyGains += reservation.getTotalPrice();
                }
            }

            // Sum gains from car reservations
            for (CarReservation reservation : carReservations) {
                LocalDate rentalStartDate = reservation.getRentalStartDate();
                if (rentalStartDate.isEqual(today)) {
                    dailyGains += reservation.getTotalPrice();
                }
                if (!rentalStartDate.isBefore(firstDayOfMonth)) {
                    monthlyGains += reservation.getTotalPrice();
                }
                if (!rentalStartDate.isBefore(firstDayOfYear)) {
                    yearlyGains += reservation.getTotalPrice();
                }
            }

            // Add statistics to the model
            model.addAttribute("dailyGains", dailyGains);
            model.addAttribute("monthlyGains", monthlyGains);
            model.addAttribute("yearlyGains", yearlyGains);

            return "admin/statistics"; // Maps to templates/client/statistics.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while calculating statistics.");
            return "error";
        }
    }





}


