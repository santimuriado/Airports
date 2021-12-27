package com.flying.airports.airport;


import com.flying.airports.plane.Plane;
import com.flying.airports.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/airport" )
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<Airport> getAirports() {
        return airportService.getAirports();
    }

    @GetMapping(path = "{airportId}/planes")
    @PreAuthorize("hasAuthority('user:read')")
    public List<Plane> getAirportPlanes(@PathVariable("airportId") Long airportId) {
        return airportService.getAirportPlanes(airportId);
    }

    @GetMapping(path = "{airportId}/tickets")
    @PreAuthorize("hasAuthority('user:read')")
    public List<Ticket> getAirportTickets(@PathVariable("airportId") Long airportId) {
        return airportService.getAirportTickets(airportId);
    }

    @GetMapping(path = "{airportId}")
    @PreAuthorize("hasAuthority('admin:read')")
    public Airport getSingleAirport(@PathVariable("airportId") Long airportId) {
        return airportService.getSingleAirport(airportId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public void registerNewAirport(@RequestBody Airport airport) {
        airportService.addNewAirport(airport);
    }

    @DeleteMapping(path = "{airportId}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void deleteAirport(@PathVariable("airportId") Long airportId) {
        airportService.deleteAirport(airportId);
    }

    @PutMapping(path = "{airportId}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void updateAirport(@PathVariable("airportId") Long airportId,
                              @RequestParam(required = false) Integer maxNumberPlanes,
                              @RequestParam(required = false) String airportName,
                              @RequestParam(required = false) String city) {
        airportService.updateAirport(airportId, maxNumberPlanes, airportName, city);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public void updateAirportPlanes(@RequestParam String airportName,
                                    @RequestParam String planeName) {
        airportService.addNewPlane(airportName,planeName);
    }
}
