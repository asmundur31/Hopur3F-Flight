package src.controllers;

import src.datastructures.Flight;
import src.database.FlightDB;
import src.datastructures.Airplain;
import src.datastructures.Airport;

import java.util.ArrayList;
import java.util.Date;

public class FlightMananger {
	
	private Flight[] flights;

	public FlightMananger() {
  	}
	
  	public FlightMananger(Flight[] flights) {
  		this.flights = flights;
  	}

  	public Flight[] search() {
    
  		return flights;
  	}

  	public Flight[] search(Date date) {
  		// Náum í öll flug úr gagnagrunninum
  		// FlightDB db = new FlightDB();
  		// flights = db.search();
  		// Síum svo út öll flug sem við viljum ekki
  		int count = 0;
  		for(Flight f : flights) {
  			if(f.getDate().getDate() == date.getDate() && f.getDate().getMonth() == date.getMonth() && f.getDate().getYear() == date.getYear()) {
  				count++;
  			}
  		}
  		Flight[] flug = new Flight[count];
  		count = 0;
  		for(Flight f : flights) {
  			if(f.getDate().getDate() == date.getDate() && f.getDate().getMonth() == date.getMonth() && f.getDate().getYear() == date.getYear()) {
  				flug[count] = f;
  				count++;
  			}
  		}
  		return flug;
  	}
  	
  	public Flight[] search(Airport airport, Boolean to) {
  		int count = 0;
  		if(to) {
  			for(Flight f : flights) {
  		  		if(f.getTo().getName().equals(airport.getName())) {
  	  				count++;
  	  			}
  	  		}
  		} else {
  			for(Flight f : flights) {
  	  			if(f.getFrom().getName().equals(airport.getName())) {
  	  				count++;
  	  			}
  	  		}
  		}
  		Flight[] flug = new Flight[count];
  		count = 0;
  		if(to) {
  			for(Flight f : flights) {
  	  			if(f.getTo().getName().equals(airport.getName())) {
  	  				flug[count] = f;
  	  				count++;
  	  			}
  	  		}
  		} else {
  			for(Flight f : flights) {
  	  			if(f.getFrom().getName().equals(airport.getName())) {
  	  				flug[count] = f;
	  				count++;
  	  			}
  	  		}
  		}
  		return flug;
  	}

  	public Flight[] search(Date date, Airport airport, Boolean to) {
    
  		return flights;
  	}

  	public Flight[] search(Airport from, Airport to) {
    
  		return flights;
  	}
  
  	public Flight[] search(Date date, Airport from, Airport to) {
  		
  		return flights;
  	}
}