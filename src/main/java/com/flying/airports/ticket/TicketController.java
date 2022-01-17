package com.flying.airports.ticket;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('admin:write')")
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

    @PostMapping(path = "save")
    public void registerNewTicket(@RequestBody Ticket ticket) {
        ticketService.addNewTicket(ticket);
    }

    @DeleteMapping(path = "{ticketId}")
    public void deleteTicket(@PathVariable("ticketId") Long ticketId) {
        ticketService.deleteTicket(ticketId);
    }
}
