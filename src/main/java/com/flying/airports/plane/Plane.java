package com.flying.airports.plane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "Plane")
@Table(name = "plane")
@NoArgsConstructor
@AllArgsConstructor
public class Plane {

    @Id
    @SequenceGenerator(
            name = "plane_sequence",
            sequenceName = "plane_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plane_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(
            name = "plane_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String planeName;

    @Column(
            name = "max_number_seats",
            nullable = false
    )
    private Integer maxNumberSeats;

    @Column(
            name = "landing_airport",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String landingAirport;

    public Plane(String planeName, Integer maxNumberSeats, String takeoffAirport, String landingAirport) {
        this.planeName = planeName;
        this.maxNumberSeats = maxNumberSeats;
        this.landingAirport = landingAirport;
    }
}
