package com.flying.airports.plane;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {

    private final PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public List<Plane> getPlanes() {
        return planeRepository.findAll();
    }

    public Plane getSinglePlane(Long planeId) {

        Optional<Plane> planeOptional = planeRepository.findById(planeId);
        if(planeOptional.isPresent()) {
            return planeOptional.get();
        }
        else {
            throw new IllegalStateException("plane with id does not exist");
        }
    }

    public void addNewPlane(Plane plane) {

        //TODO: implement findByPlaneName.
        /*Optional<Plane> planeOptional = planeRepository.findByPlaneName(plane.getPlaneName());
        if(planeOptional.isPresent()) {
            throw new IllegalStateException("plane with that name already exists");
        }*/
        planeRepository.save(plane);
    }

    public void deletePlane(Long planeId) {

        Boolean exists = planeRepository.existsById(planeId);
        if(!exists) {
            throw new IllegalStateException ("plane with id does not exist");
        }
        planeRepository.deleteById(planeId);
    }

    @Transactional
    public void updatePlaneLandingAirport(Long planeId, String landingAirport) {

        Plane plane = planeRepository.findById(planeId).orElseThrow(() ->
                new IllegalStateException("plane with id " + "does not exist"));

        plane.setLandingAirport(landingAirport);
    }


}
