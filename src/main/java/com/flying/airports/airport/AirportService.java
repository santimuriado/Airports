package com.flying.airports.airport;

import com.flying.airports.plane.Plane;
import com.flying.airports.plane.PlaneRepository;
import com.flying.airports.ticket.Ticket;
import com.flying.airports.ticket.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;
    private final TicketRepository ticketRepository;

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

    public Airport getSingleAirport(String airportName) {

        Optional<Airport> airportOptional = airportRepository.findByAirportName(airportName);
        if(airportOptional.isPresent()) {
            return airportOptional.get();
        }
        else {
            throw new IllegalStateException("airport with that name does not exist");
        }
    }

    public void addNewAirport(Airport airport) {

        Optional<Airport> airportOptional = airportRepository.findByAirportName(airport.getAirportName());
        if(airportOptional.isPresent()) {
            throw new IllegalStateException("airport name taken");
        }
        airportRepository.save(airport);
    }

    @Transactional
    public void addNewPlane(String airportName,String planeName) {

        Plane plane = planeRepository.findByPlaneName(planeName).get();
        Airport airport = airportRepository.findByAirportName(airportName).get();

        Integer currentNumberPlanes = airport.getPlanes().size();

        if(currentNumberPlanes < airport.getMaxNumberPlanes()) {
            airport.getPlanes().add(plane);
        }
        else {
            throw new IllegalStateException("airport is at max capacity");
        }
    }

    @Transactional
    public void addNewTicket(String airportName,String landingAirport) {

        Ticket ticket = ticketRepository.findByLandingAirport(landingAirport).get();
        Airport airport = airportRepository.findByAirportName(airportName).get();

        //TODO: check duplicate tickets.

        airport.getTickets().add(ticket);
    }

    public void deleteAirport(Long airportId) {

        Boolean exists = airportRepository.existsById(airportId);
        if(!exists) {
            throw new IllegalStateException ("airport with id does not exist");
        }
        airportRepository.deleteById(airportId);
    }

    @Transactional
    public void updateAirport(Long airportId,
                              Integer maxNumberPlanes,
                              String airportName,
                              String city) {

        Airport airport = airportRepository.findById(airportId).orElseThrow(() ->
                new IllegalStateException("airport with id " + "does not exist"));

        if(airportName != null && airportName.length() > 0) {
            airport.setAirportName(airportName);
        }

        if(maxNumberPlanes != null && maxNumberPlanes>0) {
            airport.setMaxNumberPlanes(maxNumberPlanes);
        }

        if(city != null && city.length() > 0) {
            airport.setCity(city);
        }
    }

    //Allows overwriting of tickets.
    @Transactional
    public void assignTicketToPlane(String planeName,String landingAirport) {

        Optional<Plane> planeOptional = planeRepository.findByPlaneName(planeName);
        Optional<Ticket> ticketOptional = ticketRepository.findByLandingAirport(landingAirport);

        if (planeOptional.isEmpty()) {
            throw new IllegalStateException("Plane with that name doesnt exist");
        }
        if (ticketOptional.isEmpty()) {
            throw new IllegalStateException("ticket with that landing airport doesnt exist");
        }

        planeOptional.get().setTicket(ticketOptional.get());
    }

    public Plane getSinglePlane(String landingAirport,Ticket ticket) {

        Airport airport = airportRepository.findByAirportName(landingAirport).get();
        List<Plane> airportPlanes = airport.getPlanes();

        int i = 0;
        boolean found = false;

        while(i < airportPlanes.size() && !found) {
            if(airportPlanes.get(i).getTicket() != null) {
                Ticket planeTicket = airportPlanes.get(i).getTicket();
                found = planeTicket.equals(ticket);
            }
            i++;
        }
        return airportPlanes.get(--i);
    }

    public List<Plane> getAirportPlanes(Long airportId) {

        Optional<Airport> airportOptional = airportRepository.findById(airportId);
        if(airportOptional.isPresent()) {
            return airportOptional.get().getPlanes();
        }
        else {
            throw new IllegalStateException("airport with id does not exist");
        }
    }

    public List<Plane> getAirportPlanes(String airportName) {

        Optional<Airport> airportOptional = airportRepository.findByAirportName(airportName);
        if(airportOptional.isPresent()) {
            return airportOptional.get().getPlanes();
        }
        else {
            throw new IllegalStateException("airport with that name does not exist");
        }
    }

    public List<Ticket> getAirportTickets(Long airportId) {

        Optional<Airport> airportOptional = airportRepository.findById(airportId);
        if(airportOptional.isPresent()) {
            return airportOptional.get().getTickets();
        }
        else {
            throw new IllegalStateException("airport with id does not exist");
        }
    }

    public List<Ticket> getAirportTickets(String airportName) {

        Optional<Airport> airportOptional = airportRepository.findByAirportName(airportName);
        if(airportOptional.isPresent()) {
            return airportOptional.get().getTickets();
        }
        else {
            throw new IllegalStateException("airport with that name does not exist");
        }
    }

}
