package com.lhind.internship.FlightBookingApiApplication.mapper;

import com.lhind.internship.FlightBookingApiApplication.model.entity.Flight;
import com.lhind.internship.FlightBookingApiApplication.model.resource.FlightResource;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightResource toResource(Flight flight) {
        if (flight == null) return null;
        return new FlightResource(
                flight.getId(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getAirline(),
                flight.getFlightNumber(),
                flight.getDepartureDate(),
                flight.getArrivalDate(),
                flight.getStatus()
        );
    }

    public Flight toEntity(FlightResource resource) {
        if (resource == null) return null;
        Flight flight = new Flight();
        flight.setId(resource.id());
        flight.setOrigin(resource.origin());
        flight.setDestination(resource.destination());
        flight.setAirline(resource.airline());
        flight.setFlightNumber(resource.flightNumber());
        flight.setDepartureDate(resource.departureDate());
        flight.setArrivalDate(resource.arrivalDate());
        flight.setStatus(resource.status());
        return flight;
    }

    public void updateFlight(final Flight flight, final FlightResource resource) {
        flight.setOrigin(resource.origin());
        flight.setDestination(resource.destination());
        flight.setAirline(resource.airline());
        flight.setFlightNumber(resource.flightNumber());
        flight.setDepartureDate(resource.departureDate());
        flight.setArrivalDate(resource.arrivalDate());
        flight.setStatus(resource.status());
    }

}

