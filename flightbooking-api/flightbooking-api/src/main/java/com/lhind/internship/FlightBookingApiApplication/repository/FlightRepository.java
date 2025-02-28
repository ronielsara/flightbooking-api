package com.lhind.internship.FlightBookingApiApplication.repository;

import com.lhind.internship.FlightBookingApiApplication.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureDateAndOrigin(LocalDateTime departureDate, String origin);
}

