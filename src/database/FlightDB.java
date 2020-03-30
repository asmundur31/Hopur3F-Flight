package src.database;

import src.datastructures.*;
import java.util.Date;

public class FlightDB {
	
	private Flight[] flights;
	
	public FlightDB() {
	}
	
	public Flight[] search() {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public Flight[] search(Date date) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public Flight[] search(Airport airport, Boolean to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public Flight[] search(Date date, Airport airport, Boolean to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public Flight[] search(Airport from, Airport to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public Flight[] search(Date date, Airport from, Airport to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public void createBooking(Booking b) {
	
	}
	
	public void deleteBooking(Booking b) {
	    
	}
	
	public void createPerson(Person p) {
	    
	}
	
	public void deletePerson(Person p) {
	    
	}
}