package com.flying.airports.plane;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;

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

        Optional<Plane> planeOptional = planeRepository.optionalFindByPlaneName(plane.getPlaneName());
        if(planeOptional.isPresent()) {
            throw new IllegalStateException("plane with that name already exists");
        }
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
