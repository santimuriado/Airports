package com.flying.airports.ticket;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping(path = "{ticketId}")
    public Ticket getSingleTicket(@PathVariable("ticketId") Long ticketId) {
        return ticketService.getSingleTicket(ticketId);
    }

    @PostMapping
    public void registerNewTicket(@RequestBody Ticket ticket) {
        ticketService.addNewTicket(ticket);
    }
}
