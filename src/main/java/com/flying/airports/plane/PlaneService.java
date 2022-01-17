package com.flying.airports.plane;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

        Optional<Plane> planeOptional = planeRepository.findByPlaneName(plane.getPlaneName());
        if(planeOptional.isPresent()) {
            throw new IllegalStateException("plane with that name already exists");
        }
        log.info("Saving new plane {} to the database", plane.getPlaneName());
        planeRepository.save(plane);
    }

    public void deletePlane(Long planeId) {

        Boolean exists = planeRepository.existsById(planeId);
        if(!exists) {
            throw new IllegalStateException ("plane with id does not exist");
        }
        log.info("Deleting plane with id {}", planeId);
        planeRepository.deleteById(planeId);
    }

    @Transactional
    public void removeTicketFromPlane(Long planeId) {

        Optional<Plane> planeOptional = planeRepository.findById(planeId);
        if(planeOptional.isPresent()) {
            planeOptional.get().setTicket(null);
        }
        else {
            throw new IllegalStateException("plane with id does not exist");
        }

    }

}
