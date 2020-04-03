package src.database;

import src.datastructures.*;

import java.sql.*;
import java.time.LocalDate;

public class FlightDB {
	
	private static Flight[] flights;

	public static void ConnectToFlight(String sql) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/database/Flights.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
		//	ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM person");
		//	flights = new Flight
			
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println("to = " + resultSet.getString("to"));
				System.out.println("from = " + resultSet.getString("from"));
				System.out.println("airplain = " + resultSet.getString("airplain"));
				System.out.println("flight_number = " + resultSet.getString("flight_number"));
				System.out.println("date = " + resultSet.getString("date"));
				System.out.println("time = " + resultSet.getString("time"));
			}
		}
		
		catch(SQLException e) {System.err.println(e.getMessage()); }
		 finally {
		 	try {
		 		if(connection != null)
		 			connection.close();
		 	}
		 	catch(SQLException e) {
		 		System.err.println(e);
		 	}
		 }
		
	}

	
	public static Flight[] search() throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		ConnectToFlight("SELECT * FROM Flight");
		return flights;
	}
	
	public Flight[] search(LocalDate date) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public Flight[] search(Airport airport, Boolean to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public Flight[] search(LocalDate date, Airport airport, Boolean to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public Flight[] search(Airport from, Airport to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public Flight[] search(LocalDate date, Airport from, Airport to) {
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