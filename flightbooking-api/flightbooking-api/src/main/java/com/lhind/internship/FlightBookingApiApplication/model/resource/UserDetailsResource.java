package com.lhind.internship.FlightBookingApiApplication.model.resource;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDetailsResource(
        Long id,

        @NotBlank(message = "First name must not be empty")
        String firstName,

        @NotBlank(message = "Last name must not be empty")
        String lastName,

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email must not be empty")
        String email,

        @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
        @NotBlank(message = "Phone number must not be empty")
        String phoneNumber
) {}


