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
			planeService.addNewPlane(new Plane("Plane 1",250));
			planeService.addNewPlane(new Plane("Plane 2",200));
			planeService.addNewPlane(new Plane("Plane 3",175));
			planeService.addNewPlane(new Plane("Plane 4",150));
			planeService.addNewPlane(new Plane("Plane 5",125));
			planeService.addNewPlane(new Plane("Plane 6",100));
			planeService.addNewPlane(new Plane("Plane 7",75));
			planeService.addNewPlane(new Plane("Plane 8",50));
			planeService.addNewPlane(new Plane("Plane 9",25));


			airportService.addNewAirport(new Airport("London Airport","London",20));
			airportService.addNewAirport(new Airport("Atlanta Airport","Atlanta",10));
			airportService.addNewAirport(new Airport("Berlin Airport","Berlin",5));

			airportService.addNewPlane("London Airport","Plane 1");
			airportService.addNewPlane("London Airport","Plane 2");
			airportService.addNewPlane("London Airport","Plane 3");
			airportService.addNewPlane("London Airport","Plane 4");

			airportService.addNewPlane("Atlanta Airport","Plane 5");
			airportService.addNewPlane("Atlanta Airport","Plane 6");
			airportService.addNewPlane("Atlanta Airport","Plane 7");

			airportService.addNewPlane("Berlin Airport","Plane 8");
			airportService.addNewPlane("Berlin Airport","Plane 9");

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

			airportService.assignTicketToPlane("Plane 1","Berlin Airport");

			appUserService.purchaseTicket("London Airport","Berlin Airport","admin@gmail.com");
		};
	}
}
