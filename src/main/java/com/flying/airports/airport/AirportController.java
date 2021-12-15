package com.flying.airports.airport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/airport" )
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAirports() {
        return airportService.getAirports();
    }

    @GetMapping(path = "{airportId}")
    public Airport getSingleAirport(@PathVariable("airportId") Long airportId) {
        return airportService.getSingleAirport(airportId);
    }

    @PostMapping
    public void registerNewAirport(@RequestBody Airport airport) {
        airportService.addNewAirport(airport);
    }

    @DeleteMapping(path = "{airportId}")
    public void deleteAirport(@PathVariable("airportId") Long airportId) {
        airportService.deleteAirport(airportId);
    }

    @PutMapping(path = "{airportId}")
    public void updateAirport(@PathVariable("airportId") Long airportId,
                              @RequestParam(required = false) Integer maxNumberPlanes,
                              @RequestParam(required = false) String airportName) {
        airportService.updateAirport(airportId, maxNumberPlanes, airportName);
    }
}
