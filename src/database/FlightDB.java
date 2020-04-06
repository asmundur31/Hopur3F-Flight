package src.database;

import src.datastructures.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
			
			ArrayList<Flight> list = new ArrayList<Flight>();
			
			//LocalDateTime time;
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				//time = LocalDateTime.of(resultSet.getString("date"),resultSet.getString("time"));
				list.add(new Flight(resultSet.getString("to"),
								resultSet.getString("from"),
								resultSet.getString("airplain"),
								resultSet.getString("flight_number"),
								LocalDateTime.parse(resultSet.getString("date")+"T"
													+resultSet.getString("time"))
								)
						);
				//System.out.println("to = " + resultSet.getString("to"));
				//System.out.println("from = " + resultSet.getString("from"));
				//System.out.println("airplain = " + resultSet.getString("airplain"));
				//System.out.println("flight_number = " + resultSet.getString("flight_number"));
				System.out.println("date = " + resultSet.getString("date"));
				//System.out.println("time = " + resultSet.getString("time"));
			}
			System.out.println(list.size());
		    flights = new Flight[list.size()];
			list.toArray(flights);
			for(Flight f : flights) {
				System.out.println(f);
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
	
	public static Flight[] search(LocalDate date) throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		String a = date.toString();
		ConnectToFlight("SELECT * FROM Flight WHERE date IS '"+a+"'");
		System.out.println("out from connect");
		return flights;
	}
	
	public static Flight[] search(Airport airport, Boolean to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public static Flight[] search(LocalDate date, Airport airport, Boolean to) {
		// Sækjum flug í gagnagrunnin
		return flights;
	}
	
	public static Flight[] search(Airport from, Airport to) {
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