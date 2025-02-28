package com.lhind.internship.FlightBookingApiApplication.model.resource;

import com.lhind.internship.FlightBookingApiApplication.model.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record BookingResource(
        Long id,

        @NotNull(message = "Booking date is required")
        LocalDateTime bookingDate,

        @NotNull(message = "Booking status is required")
        BookingStatus status,

        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Flight ID is required")
        Long flightId
) {}


