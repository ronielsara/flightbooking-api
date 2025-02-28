package com.lhind.internship.FlightBookingApiApplication.model.resource;

import com.lhind.internship.FlightBookingApiApplication.model.enums.FlightStatus;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record FlightResource(
        Long id,


        @NotNull(message = "Origin is required")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Origin must be exactly 3 uppercase letters (e.g., JFK, LAX)")
        String origin,

        @NotNull(message = "Destination is required")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Destination must be exactly 3 uppercase letters (e.g., JFK, LAX)")
        String destination,

        @NotNull(message = "Airline name is required")
        String airline,

        @NotNull(message = "Flight number is required")
        String flightNumber,

        @NotNull(message = "Departure date is required")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime departureDate,

        @NotNull(message = "Arrival date is required")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime arrivalDate,

        @NotNull(message = "Flight status is required")
        FlightStatus status
) {}


