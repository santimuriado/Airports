package com.flying.airports.ticket;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {

    private final TicketRepository ticketRepository;

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getSingleTicket(Long ticketId) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isPresent()) {
            return ticketOptional.get();
        }
        else {
            throw new IllegalStateException("ticket with that id does not exist");
        }
    }

    public Ticket getSingleTicket(String landingAirport) {

        Optional<Ticket> ticketOptional = ticketRepository.findByLandingAirport(landingAirport);
        if(ticketOptional.isPresent()) {
            return ticketOptional.get();
        }
        else {
            throw new IllegalStateException("ticket with that landing airport does not exist");
        }
    }

    public void addNewTicket(Ticket ticket) {

        Optional<Ticket> ticketOptional = ticketRepository.findByLandingAirport(ticket.getLandingAirport());
        if(ticketOptional.isPresent()) {
            throw new IllegalStateException("ticket with that landing airport already exists");
        }
        log.info("Saving new ticket {} to the database", ticket.getLandingAirport());
        ticketRepository.save(ticket);
    }

}
