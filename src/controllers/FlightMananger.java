package src.controllers;

import src.datastructures.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.ArrayList;

public class FlightMananger {

	private static Flight[] flights;

	public static void ConnectToFlight(String sql) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/database/Flight.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			ArrayList<Flight> list = new ArrayList<Flight>();
			
			//LocalDateTime time;
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list.add(new Flight(
					resultSet.getString("airportToName"),
					resultSet.getString("airportFromName"),
					resultSet.getString("airplainName"),
					resultSet.getString("flightNr"),
					LocalDateTime.parse(
						resultSet.getString("date")+"T"+
						resultSet.getString("time")
					)
				));
			}
			flights = new Flight[list.size()];
			list.toArray(flights);
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
		 	try {
		 		if(connection != null)
		 			connection.close();
		 	} catch(SQLException e) {
		 		System.err.println(e);
		 	}
		}
	}

	public static Flight[] search() throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		ConnectToFlight("SELECT * FROM Flights");
		return flights;
	}
	
	public static Flight[] search(LocalDate date) throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		String a = date.toString();
		ConnectToFlight("SELECT * FROM Flights WHERE date IS '"+a+"'");
		System.out.println("out from connect");
		return flights;
	}
	
	public static Flight[] search(String airportName, Boolean to) throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		if(to) {
			ConnectToFlight("SELECT * FROM Flights WHERE airportToName IS '"+airportName+"'");
		} else {
			ConnectToFlight("SELECT * FROM Flights WHERE airportFromName IS '"+airportName+"'");
		}
		return flights;
	}
	
	public static Flight[] search(LocalDate date, String airport, Boolean to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public static Flight[] search(String airportFrom, String airportTo) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public static Flight[] search(LocalDate date, String airportFrom, String airportTo) {
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