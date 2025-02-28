package com.lhind.internship.FlightBookingApiApplication.service;

import com.lhind.internship.FlightBookingApiApplication.mapper.FlightMapper;
import com.lhind.internship.FlightBookingApiApplication.model.entity.Flight;
import com.lhind.internship.FlightBookingApiApplication.model.resource.FlightResource;
import com.lhind.internship.FlightBookingApiApplication.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public List<FlightResource> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toResource)
                .collect(Collectors.toList());
    }

    public FlightResource getFlightById(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found"));
        return flightMapper.toResource(flight);
    }

    public List<FlightResource> getFlightsByDepartureDateAndOrigin(LocalDateTime date, String origin) {
        return flightRepository.findByDepartureDateAndOrigin(date, origin).stream()
                .map(flightMapper::toResource)
                .collect(Collectors.toList());
    }

    public FlightResource saveFlight(FlightResource flightResource) {
        Flight flight;

        if (flightResource.id() != null) { // Check if updating an existing flight
            flight = flightRepository.findById(flightResource.id())
                    .orElseThrow(() -> new EntityNotFoundException("Flight not found"));

            flightMapper.updateFlight(flight, flightResource); // Ensure fields are updated
        } else {
            flight = flightMapper.toEntity(flightResource); // Creating a new flight
        }

        Flight savedFlight = flightRepository.save(flight); // Save updated or new entity
        return flightMapper.toResource(savedFlight);
    }


    public void deleteFlight(Long flightId) {
        if (!flightRepository.existsById(flightId)) {
            throw new EntityNotFoundException("Flight not found");
        }
        flightRepository.deleteById(flightId);
    }
}
