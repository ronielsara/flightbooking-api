package com.lhind.internship.FlightBookingApiApplication.repository;

import com.lhind.internship.FlightBookingApiApplication.model.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}

