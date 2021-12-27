package com.flying.airports.plane;

import com.flying.airports.ticket.Ticket;
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

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Plane(String planeName, Integer maxNumberSeats) {
        this.planeName = planeName;
        this.maxNumberSeats = maxNumberSeats;
    }
}
