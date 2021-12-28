package com.flying.airports;

import com.flying.airports.airport.Airport;
import com.flying.airports.airport.AirportService;
import com.flying.airports.appuser.AppUserService;
import com.flying.airports.plane.Plane;
import com.flying.airports.plane.PlaneService;
import com.flying.airports.registration.RegistrationRequest;
import com.flying.airports.registration.RegistrationService;
import com.flying.airports.security.ApplicationUserRole;
import com.flying.airports.ticket.Ticket;
import com.flying.airports.ticket.TicketService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AirportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportsApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AirportService airportService,
						  PlaneService planeService,
						  RegistrationService registrationService,
						  TicketService ticketService,
						  AppUserService appUserService) {
		return args -> {
			planeService.addNewPlane(new Plane("Hawker Hurricane",140));
			planeService.addNewPlane(new Plane("U-2 Spy Plane",100));
			planeService.addNewPlane(new Plane("B-52 Stratofortress",75));
			planeService.addNewPlane(new Plane("F-16 Fighting Falcon",50));

			airportService.addNewAirport(new Airport("London Airport","London",20));
			airportService.addNewAirport(new Airport("Atlanta Airport","Atlanta",10));
			airportService.addNewAirport(new Airport("Berlin Airport","Berlin",5));

			airportService.addNewPlane("London Airport","Hawker Hurricane");
			airportService.addNewPlane("London Airport","U-2 Spy Plane");
			airportService.addNewPlane("London Airport","B-52 Stratofortress");
			airportService.addNewPlane("London Airport","F-16 Fighting Falcon");

			airportService.addNewPlane("Atlanta Airport","Hawker Hurricane");
			airportService.addNewPlane("Atlanta Airport","U-2 Spy Plane");
			airportService.addNewPlane("Atlanta Airport","B-52 Stratofortress");

			airportService.addNewPlane("Berlin Airport","Hawker Hurricane");
			airportService.addNewPlane("Berlin Airport","U-2 Spy Plane");

			String adminToken = registrationService.register(new RegistrationRequest("admin","password","admin@gmail.com", ApplicationUserRole.ADMIN));
			String userToken = registrationService.register(new RegistrationRequest("user","password","user@gmail.com", ApplicationUserRole.USER));
			registrationService.confirmToken(adminToken);
			registrationService.confirmToken(userToken);

			ticketService.addNewTicket(new Ticket("Berlin Airport"));
			ticketService.addNewTicket(new Ticket("Atlanta Airport"));
			ticketService.addNewTicket(new Ticket("London Airport"));

			airportService.addNewTicket("London Airport","Berlin Airport");
			airportService.addNewTicket("London Airport","Atlanta Airport");

			airportService.addNewTicket("Berlin Airport","London Airport");
			airportService.addNewTicket("Berlin Airport","Atlanta Airport");

			airportService.addNewTicket("Atlanta Airport","Berlin Airport");
			airportService.addNewTicket("Atlanta Airport","London Airport");

			airportService.assignTicketToPlane("London Airport","Hawker Hurricane","Berlin Airport");

			appUserService.purchaseTicket("London Airport","Berlin Airport","admin@gmail.com");
		};
	}
}
