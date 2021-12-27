package com.flying.airports.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByLandingAirport(String landingAirport);

}
