package src.controllers;

import src.database.FlightDB;

import src.datastructures.Flight;
import src.datastructures.Airplain;
import src.datastructures.Airport;

import java.time.*;

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

  	// Notkun: Flight[] f = flightMananger.search(date);
    // Fyrir:  flightMananger er hlutur af taginu FlightMananger.
    //         date er hlutur af taginu Date, date má bara vera nákvæmt að
    //         dagsetningu ekki tíma sólahrings.
    // Eftir:  f er listi af flugum sem inniheldur öll flug sem eru flogin
    //         á dagsetningu date.
  	public Flight[] search(LocalDate date) {
  	    // Náum í öll flug úr gagnagrunninum
  	    // FlightDB db = new FlightDB();
  	    // flights = db.search();
  	    // Teljum hversu mörg flug eru
  	    int count = 0;
  	    for(Flight f : flights) {
  	        if(date.equals(f.getDate().toLocalDate())) {
  	          count++;
  	        }
  	    }
  	    Flight[] flug = new Flight[count];
  	    count = 0;
  	    // Síum svo út öll flug sem við viljum ekki
  	    for(Flight f : flights) {
  	        if(date.equals(f.getDate().toLocalDate())) {
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

  	public Flight[] search(LocalDate date, Airport airport, Boolean to) {
    
  		return flights;
  	}

  	public Flight[] search(Airport from, Airport to) {
    
  		return flights;
  	}
  
  	public Flight[] search(LocalDate date, Airport from, Airport to) {
  		
  		return flights;
  	}
}