package com.lhind.internship.FlightBookingApiApplication.model.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserResource(
        Long id,

        @NotBlank(message = "Username must not be empty")
        String username,

        @NotBlank(message = "Role must not be empty")
        String role,

        @NotBlank(message = "Password must not be empty")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotNull(message = "User details must be provided")
        UserDetailsResource userDetails
) {}


