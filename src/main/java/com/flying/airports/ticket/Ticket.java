package com.flying.airports.ticket;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity (name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "tickets",
        uniqueConstraints = {
                @UniqueConstraint(name = "landing_airport_unique", columnNames = "landing_airport")
        }
)
public class Ticket {

    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(
            name = "landing_airport",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String landingAirport;

    public Ticket(String landingAirport) {
        this.landingAirport = landingAirport;
    }
}
