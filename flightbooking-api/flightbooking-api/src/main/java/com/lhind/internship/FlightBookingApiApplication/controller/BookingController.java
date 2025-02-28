package com.lhind.internship.FlightBookingApiApplication.controller;

import com.lhind.internship.FlightBookingApiApplication.model.resource.BookingResource;
import com.lhind.internship.FlightBookingApiApplication.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingResource>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookingsOrderedByDate());
    }

    @GetMapping("/{userId}/{bookingId}")
    public ResponseEntity<BookingResource> getUserBooking(
            @PathVariable Long userId, @PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingForUser(userId, bookingId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResource>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getAllBookingsForUser(userId));
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<BookingResource>> getBookingsOnFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(bookingService.getAllBookingsForFlight(flightId));
    }

    @PostMapping
    public ResponseEntity<BookingResource> createOrUpdateBooking(@RequestBody @Valid BookingResource bookingResource) {
        BookingResource savedBooking = bookingService.saveBooking(bookingResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
