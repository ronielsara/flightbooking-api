package com.lhind.internship.FlightBookingApiApplication.mapper;

import com.lhind.internship.FlightBookingApiApplication.model.entity.Booking;
import com.lhind.internship.FlightBookingApiApplication.model.entity.Flight;
import com.lhind.internship.FlightBookingApiApplication.model.entity.User;
import com.lhind.internship.FlightBookingApiApplication.model.resource.BookingResource;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResource toResource(Booking booking) {
        if (booking == null) return null;
        return new BookingResource(
                booking.getId(),
                booking.getBookingDate(),
                booking.getStatus(),
                booking.getUser().getId(),
                booking.getFlight().getId()
        );
    }

    public Booking toEntity(BookingResource resource, User user, Flight flight) {
        if (resource == null) return null;
        Booking booking = new Booking();
        booking.setId(resource.id());
        booking.setBookingDate(resource.bookingDate());
        booking.setStatus(resource.status());
        booking.setUser(user);
        booking.setFlight(flight);

        return booking;
    }


    public void updateBooking(Booking booking, BookingResource resource) {
            booking.setBookingDate(resource.bookingDate());
            booking.setStatus(resource.status());
    }
}
