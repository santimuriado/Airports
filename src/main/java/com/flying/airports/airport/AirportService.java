package com.flying.airports.airport;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    public Airport getSingleAirport(Long airportId) {

        Optional<Airport> airportOptional = airportRepository.findById(airportId);
        if(airportOptional.isPresent()) {
            return airportOptional.get();
        }
        else {
            throw new IllegalStateException("airport with id does not exist");
        }
    }

    public void addNewAirport(Airport airport) {
        //TODO: make findByCity.
        /*Optional<Airport> airportOptional = airportRepository.findByCity(airport.getCity());
        if(airportOptional.isPresent()) {
            throw new IllegalStateException("city taken");
        }*/
        airportRepository.save(airport);
    }

    public void deleteAirport(Long airportId) {

        Boolean exists = airportRepository.existsById(airportId);
        if(!exists) {
            throw new IllegalStateException ("airport with id does not exist");
        }
        airportRepository.deleteById(airportId);
    }

    @Transactional
    public void updateAirportNumberOfPlanes(Long airportId,Integer maxNumberPlanes) {

        Airport airport = airportRepository.findById(airportId).orElseThrow(() ->
                new IllegalStateException("airport with id " + "does not exist"));

        airport.setMaxNumberPlanes(maxNumberPlanes);
    }

    @Transactional
    public void updateAirportName(Long airportId, String airportName) {

        Airport airport = airportRepository.findById(airportId).orElseThrow(() ->
                new IllegalStateException("airport with id " + "does not exist"));

        airport.setAirportName(airportName);
    }
}
