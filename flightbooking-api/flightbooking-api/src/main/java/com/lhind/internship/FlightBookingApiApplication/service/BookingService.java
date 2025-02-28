package com.lhind.internship.FlightBookingApiApplication.service;


import com.lhind.internship.FlightBookingApiApplication.mapper.BookingMapper;
import com.lhind.internship.FlightBookingApiApplication.model.entity.Booking;
import com.lhind.internship.FlightBookingApiApplication.model.entity.Flight;
import com.lhind.internship.FlightBookingApiApplication.model.entity.User;
import com.lhind.internship.FlightBookingApiApplication.model.resource.BookingResource;
import com.lhind.internship.FlightBookingApiApplication.repository.BookingRepository;
import com.lhind.internship.FlightBookingApiApplication.repository.FlightRepository;
import com.lhind.internship.FlightBookingApiApplication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository,
                          FlightRepository flightRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.bookingMapper = bookingMapper;
    }

    public List<BookingResource> getAllBookingsOrderedByDate() {
        return bookingRepository.findAllByOrderByBookingDateDesc().stream()
                .map(bookingMapper::toResource)
                .collect(Collectors.toList());
    }

    public BookingResource getBookingForUser(Long userId, Long bookingId) {
        Booking booking = bookingRepository.findByIdAndUserId(bookingId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Booking with ID " + bookingId + " not found for user ID " + userId));
        return bookingMapper.toResource(booking);
    }

    public List<BookingResource> getAllBookingsForUser(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(bookingMapper::toResource)
                .collect(Collectors.toList());
    }

    public List<BookingResource> getAllBookingsForFlight(Long flightId) {
        return bookingRepository.findByFlightId(flightId).stream()
                .map(bookingMapper::toResource)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingResource saveBooking(BookingResource bookingResource) {
        Booking booking;

        if (bookingResource.id() != null) {
            booking = bookingRepository.findById(bookingResource.id())
                    .orElseThrow(() -> new EntityNotFoundException("Booking with ID " + bookingResource.id() + " not found"));
            bookingMapper.updateBooking(booking, bookingResource);
        } else {
            User user = userRepository.findById(bookingResource.userId())
                    .orElseThrow(() -> new EntityNotFoundException("User with ID " + bookingResource.userId() + " not found"));

            Flight flight = flightRepository.findById(bookingResource.flightId())
                    .orElseThrow(() -> new EntityNotFoundException("Flight with ID " + bookingResource.flightId() + " not found"));

            booking = bookingMapper.toEntity(bookingResource, user, flight);
        }

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toResource(savedBooking);
    }

    public void deleteBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new EntityNotFoundException("Booking with ID " + bookingId + " not found");
        }
        bookingRepository.deleteById(bookingId);
    }
}


