package com.lhind.internship.FlightBookingApiApplication.service;

import com.lhind.internship.FlightBookingApiApplication.mapper.UserDetailsMapper;
import com.lhind.internship.FlightBookingApiApplication.mapper.UserMapper;
import com.lhind.internship.FlightBookingApiApplication.model.entity.Booking;
import com.lhind.internship.FlightBookingApiApplication.model.entity.User;
import com.lhind.internship.FlightBookingApiApplication.model.entity.UserDetails;
import com.lhind.internship.FlightBookingApiApplication.model.resource.UserResource;
import com.lhind.internship.FlightBookingApiApplication.repository.BookingRepository;
import com.lhind.internship.FlightBookingApiApplication.repository.UserDetailsRepository;
import com.lhind.internship.FlightBookingApiApplication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final UserMapper userMapper;
    private final UserDetailsMapper userDetailsMapper;
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookingRepository bookingRepository,
                       UserMapper userMapper, UserDetailsMapper userDetailsMapper, UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.userMapper = userMapper;
        this.userDetailsMapper = userDetailsMapper;  // ✅ Injected via constructor
        this.userDetailsRepository = userDetailsRepository;
    }

    // ✅ Get all users and their user details
    public List<UserResource> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResource)
                .collect(Collectors.toList());
    }

    // ✅ Get a specific user and their user details
    public UserResource getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toResource(user);
    }

    // ✅ Get all users and their user details who have booked on a specific flight
    public List<UserResource> getUsersByFlight(Long flightId) {
        List<Booking> bookings = bookingRepository.findByFlightId(flightId);
        return bookings.stream()
                .map(Booking::getUser) // Extract users from bookings
                .distinct()
                .map(userMapper::toResource)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResource saveUser(UserResource userResource) {
        User user;

        if (userResource.id() != null) {
            user = userRepository.findById(userResource.id())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            if (userResource.userDetails() != null) {
                if (user.getUserDetails() != null) {
                    userMapper.updateUserDetails(user.getUserDetails(), userResource.userDetails()); // ✅ Update existing userDetails
                } else {
                    UserDetails userDetails = userDetailsMapper.toEntity(userResource.userDetails());
                    userDetails.setUser(user);
                    user.setUserDetails(userDetails);
                }
            }

            userMapper.updateUser(user, userResource);

        } else {
            user = userMapper.toEntity(userResource);


            if (userResource.userDetails() != null) {
                UserDetails userDetails = userDetailsMapper.toEntity(userResource.userDetails());
                user.setUserDetails(userDetails);
                userDetails.setUser(user);
            }
            user = userRepository.save(user);
        }
        return userMapper.toResource(user);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }
}
