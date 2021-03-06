package com.flying.airports.airport;


import com.flying.airports.plane.Plane;
import com.flying.airports.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "airport")
@Table(
        name = "airports",
        uniqueConstraints = {
                @UniqueConstraint(name = "airport_name_unique", columnNames = "airport_name")
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @SequenceGenerator(
            name = "airport_sequence",
            sequenceName = "airport_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airport_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(
            name = "airport_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String airportName;

    @Column(
            name = "city",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String city;

    @Column(
            name = "max_number_planes",
            nullable = false
    )
    private Integer maxNumberPlanes;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Plane> planes = new ArrayList<>();

    @ManyToMany
    private List<Ticket> tickets = new ArrayList<>();

    public Airport(String airportName, String city, Integer maxNumberPlanes) {
        this.airportName = airportName;
        this.city = city;
        this.maxNumberPlanes = maxNumberPlanes;
    }
}
