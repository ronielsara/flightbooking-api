package com.lhind.internship.FlightBookingApiApplication.mapper;

import com.lhind.internship.FlightBookingApiApplication.model.entity.UserDetails;
import com.lhind.internship.FlightBookingApiApplication.model.resource.UserDetailsResource;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetailsResource toResource(UserDetails details) {
        if (details == null) return null;
        return new UserDetailsResource(
                details.getId(),
                details.getFirstName(),
                details.getLastName(),
                details.getEmail(),
                details.getPhoneNumber()
        );
    }

    public UserDetails toEntity(UserDetailsResource resource) {
        if (resource == null) return null;
        UserDetails details = new UserDetails();
        details.setId(resource.id());
        details.setFirstName(resource.firstName());
        details.setLastName(resource.lastName());
        details.setEmail(resource.email());
        details.setPhoneNumber(resource.phoneNumber());
        return details;
    }
}
