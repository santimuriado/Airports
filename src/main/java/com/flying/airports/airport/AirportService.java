package com.flying.airports.airport;

import com.flying.airports.plane.Plane;
import com.flying.airports.plane.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;

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

        Optional<Airport> airportOptional = airportRepository.optionalFindByAirportName(airport.getAirportName());
        if(airportOptional.isPresent()) {
            throw new IllegalStateException("airport name taken");
        }
        airportRepository.save(airport);
    }

    @Transactional
    public void addNewPlane(String airportName,String planeName) {

        Plane plane = planeRepository.findByPlaneName(planeName);
        Airport airport = airportRepository.findByAirportName(airportName);

        Integer currentNumberPlanes = airport.getCurrentNumberPlanes();

        if(currentNumberPlanes < airport.getMaxNumberPlanes()) {
            airport.getPlanes().add(plane);
            airport.setCurrentNumberPlanes(++currentNumberPlanes);
        }
        else {
            throw new IllegalStateException("airport is at max capacity");
        }
    }

    public void deleteAirport(Long airportId) {

        Boolean exists = airportRepository.existsById(airportId);
        if(!exists) {
            throw new IllegalStateException ("airport with id does not exist");
        }
        airportRepository.deleteById(airportId);
    }

    @Transactional
    public void updateAirport(Long airportId,Integer maxNumberPlanes, String airportName) {

        Airport airport = airportRepository.findById(airportId).orElseThrow(() ->
                new IllegalStateException("airport with id " + "does not exist"));

        if(airportName != null && airportName.length() > 0) {
            airport.setAirportName(airportName);
        }

        if(maxNumberPlanes != null && maxNumberPlanes>0) {
            airport.setMaxNumberPlanes(maxNumberPlanes);
        }

    }

}
