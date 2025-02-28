package com.lhind.internship.FlightBookingApiApplication.controller;

import com.lhind.internship.FlightBookingApiApplication.model.resource.UserResource;
import com.lhind.internship.FlightBookingApiApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<UserResource>> getUsersByFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(userService.getUsersByFlight(flightId));
    }

    @PostMapping
    public ResponseEntity<UserResource> createOrUpdateUser(@RequestBody UserResource userResource) {
        UserResource savedUser = userService.saveUser(userResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
