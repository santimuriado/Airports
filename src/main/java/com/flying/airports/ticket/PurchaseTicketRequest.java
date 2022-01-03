package com.flying.airports.ticket;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTicketRequest {

    private String takeOffAirport;
    private String landingAirport;
    private String userEmail;
}
