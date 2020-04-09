package src.controllers;

import src.datastructures.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightMananger {

	private static Flight[] flights;

	public static void ConnectToFlight(String sql) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/database/Flights.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			ArrayList<Flight> list = new ArrayList<Flight>();
			
			//LocalDateTime time;
			ResultSet resultSet = statement.executeQuery(sql);
			
			Statement underStm = connection.createStatement();
			underStm.setQueryTimeout(30);
			list.add(newFlight(statement, resultSet, underStm));

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
	
	public static Flight newFlight(Statement statement, ResultSet resultSet, Statement underStm) 
			throws SQLException {
		// create destination airport
		String table = "airport";
		String name = resultSet.getString("to");
		String query = "SELECT * FROM " + table + " WHERE name IS '" + name + "'";

		ResultSet rs = underStm.executeQuery(query);		
		Airport to = new Airport(rs.getString(1),
								 rs.getString(2),
								 rs.getFloat(3),
								 rs.getFloat(4));
		
		// create departure airport
		name = resultSet.getString("from");
		query = "SELECT * FROM " + table + " WHERE name IS '" + name + "'";

		rs = underStm.executeQuery(query);
		Airport from = new Airport(rs.getString(1),
				 				   rs.getString(2),
				 				   rs.getFloat(3),
				 				   rs.getFloat(4));
		
		// create airplain
		table = "airplain";
		name = resultSet.getString("airplain");
		query = "SELECT * FROM " + table + " WHERE name IS '" + name + "'";

		rs = underStm.executeQuery(query);
		int size = rs.getInt(5);
		Airplain airplain = new Airplain(rs.getString(1),
										 toBoolean(rs.getString(2),size),
										 toBoolean(rs.getString(3),size),
										 toBoolean(rs.getString(4),size));
		
		return new Flight(to, from, airplain, resultSet.getString("flight_number"),
						  LocalDateTime.parse(resultSet.getString("date")+"T"+
								  		 	  resultSet.getString("time")));
	}
	
	public static Boolean[][] toBoolean(String arr, int size) {
		System.out.println(arr.length());
		int col = size/10;
		int row = size%10;
		Boolean[][] bool = new Boolean[col][row];
		int counter = col*row;
		//System.out.println(counter + " "+arr.length());
		for(int i = 0; i<col; i++) {
			for(int j = 0; j < row; j++) {
				if(arr.charAt(--counter) == '1') bool[i][j] = true;
				else 						   	 bool[i][j] = false;
			}
		}
		return bool;
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
	
	public static Flight[] search(String airportName, Boolean to) throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		if(to) {
			ConnectToFlight("SELECT * FROM Flight WHERE airport IS '"+airportName+"'");
		} else {
			ConnectToFlight("SELECT * FROM Flight WHERE airport IS '"+airportName+"'");
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