package com.lhind.internship.FlightBookingApiApplication.repository;

import com.lhind.internship.FlightBookingApiApplication.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // ✅ Get all bookings ordered by booking date (recent first)
    List<Booking> findAllByOrderByBookingDateDesc();

    // ✅ Get a specific booking for a user
    Optional<Booking> findByIdAndUserId(Long bookingId, Long userId);

    // ✅ Get all bookings of a user
    List<Booking> findByUserId(Long userId);

    // ✅ Get all bookings on a flight
    List<Booking> findByFlightId(Long flightId);
}

