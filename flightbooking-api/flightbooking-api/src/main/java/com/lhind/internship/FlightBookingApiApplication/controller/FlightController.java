package com.lhind.internship.FlightBookingApiApplication.controller;

import com.lhind.internship.FlightBookingApiApplication.model.resource.FlightResource;


import com.lhind.internship.FlightBookingApiApplication.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightResource>> getAllFlights() {
        List<FlightResource> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResource> getFlightById(@PathVariable Long id) {
        FlightResource flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightResource>> getFlightsByDateAndOrigin(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("origin") String origin) {
        List<FlightResource> flights = flightService.getFlightsByDepartureDateAndOrigin(date, origin);
        return ResponseEntity.ok(flights);
    }

    @PostMapping
    public ResponseEntity<FlightResource> createOrUpdateFlight(@RequestBody @Valid FlightResource flightResource) {
        FlightResource savedFlight = flightService.saveFlight(flightResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
