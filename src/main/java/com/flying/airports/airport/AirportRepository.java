package com.flying.airports.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Long> {

    Optional<Airport> findByAirportName(String airportName);

    Optional<Airport> findByCity(String city);
}
