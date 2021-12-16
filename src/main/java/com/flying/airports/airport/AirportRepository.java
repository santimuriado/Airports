package com.flying.airports.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Long> {

    @Query("SELECT a FROM Airport a WHERE a.airportName = ?1")
    Optional<Airport> optionalFindByAirportName(String airportName);

    Airport findByAirportName(String airportName);

    @Query("SELECT a FROM Airport a WHERE a.city = ?1")
    Optional<Airport> findByCity(String city);
}
