package com.flying.airports;

import com.flying.airports.airport.Airport;
import com.flying.airports.airport.AirportService;
import com.flying.airports.plane.Plane;
import com.flying.airports.plane.PlaneService;
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
	CommandLineRunner run(AirportService airportService, PlaneService planeService) {
		return args -> {
			planeService.addNewPlane(new Plane("Hawker Hurricane",140,"Berlin"));
			planeService.addNewPlane(new Plane("U-2 Spy Plane",100,"London"));
			planeService.addNewPlane(new Plane("B-52 Stratofortress",75,"New York"));
			planeService.addNewPlane(new Plane("F-16 Fighting Falcon",50,"Paris"));

			airportService.addNewAirport(new Airport("Guangzhou Baiyun International Airport","Baiyun-Huadu",20));
			airportService.addNewAirport(new Airport("Hartsfield–Jackson Atlanta International Airport","Atlanta",10));
			airportService.addNewAirport(new Airport("Chengdu Shuangliu International Airport","Shuangliu-Wuhou",5));

			airportService.addNewPlane("Guangzhou Baiyun International Airport","Hawker Hurricane");
			airportService.addNewPlane("Guangzhou Baiyun International Airport","U-2 Spy Plane");
			airportService.addNewPlane("Guangzhou Baiyun International Airport","B-52 Stratofortress");
			airportService.addNewPlane("Guangzhou Baiyun International Airport","F-16 Fighting Falcon");

			airportService.addNewPlane("Hartsfield–Jackson Atlanta International Airport","Hawker Hurricane");
			airportService.addNewPlane("Hartsfield–Jackson Atlanta International Airport","U-2 Spy Plane");
			airportService.addNewPlane("Hartsfield–Jackson Atlanta International Airport","B-52 Stratofortress");

			airportService.addNewPlane("Chengdu Shuangliu International Airport","Hawker Hurricane");
			airportService.addNewPlane("Chengdu Shuangliu International Airport","U-2 Spy Plane");

		};
	}
}
