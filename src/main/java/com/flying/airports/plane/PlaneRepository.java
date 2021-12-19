package com.flying.airports.plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository<Plane,Long> {

    Plane findByPlaneName(String planeName);

    @Query("SELECT p FROM Plane p WHERE p.planeName = ?1")
    Optional<Plane> optionalFindByPlaneName(String planeName);
}
