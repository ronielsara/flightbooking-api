package com.lhind.internship.FlightBookingApiApplication.mapper;

import com.lhind.internship.FlightBookingApiApplication.model.entity.User;
import com.lhind.internship.FlightBookingApiApplication.model.entity.UserDetails;
import com.lhind.internship.FlightBookingApiApplication.model.resource.UserDetailsResource;
import com.lhind.internship.FlightBookingApiApplication.model.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    public UserResource toResource(User user) {
        if (user == null) return null;
        return new UserResource(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getPassword(),
                userDetailsMapper.toResource(user.getUserDetails())
        );
    }

    public User toEntity(UserResource resource) {
        if (resource == null) return null;
        User user = new User();
        user.setId(resource.id());
        user.setUsername(resource.username());
        user.setRole(resource.role());
        user.setPassword(resource.password());
        return user;
    }

    public void updateUser(final User user, final UserResource resource) {
        user.setUsername(resource.username());
        user.setRole(resource.role());
        user.setPassword(resource.password());

        if (resource.userDetails() != null) {
            if (user.getUserDetails() == null) {
                user.setUserDetails(new UserDetails());
            }
            updateUserDetails(user.getUserDetails(), resource.userDetails());
        }
    }

    public void updateUserDetails(final UserDetails userDetails, final UserDetailsResource resource) {
        if (resource == null) return;

        userDetails.setFirstName(resource.firstName());
        userDetails.setLastName(resource.lastName());
        userDetails.setEmail(resource.email());
        userDetails.setPhoneNumber(resource.phoneNumber());
    }
}

