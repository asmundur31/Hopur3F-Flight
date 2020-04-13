package src.controllers;

import src.datastructures.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightMananger {

	private Flight[] flights;

	public void ConnectToFlight(String sql, String[] gildi) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/database/Flights.db");
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.clearParameters();
			for(int i=1; i<=gildi.length; i++) {
				ps.setString(i,gildi[i-1]);
			}
			ps.setQueryTimeout(30);
			
			ArrayList<Flight> list = new ArrayList<Flight>();

			ResultSet resultSet = ps.executeQuery();

			Statement underStm = connection.createStatement();
			underStm.setQueryTimeout(30);
			while(resultSet.next()) {
				list.add(newFlight(resultSet, underStm));
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
	
	public Flight newFlight(ResultSet resultSet, Statement underStm) 
			throws SQLException {
		// create destination airport
		String table = "airport";
		String name = resultSet.getString("airportTo");
		String query = "SELECT * FROM " + table + " WHERE name IS '" + name + "'";

		ResultSet rs = underStm.executeQuery(query);		
		Airport to = new Airport(rs.getString(1),
								 rs.getString(2),
								 rs.getFloat(3),
								 rs.getFloat(4));
		
		// create departure airport
		name = resultSet.getString("airportFrom");
		query = "SELECT * FROM " + table + " WHERE name IS '" + name + "'";

		rs = underStm.executeQuery(query);
		Airport from = new Airport(rs.getString(1),
				 				   rs.getString(2),
				 				   rs.getFloat(3),
				 				   rs.getFloat(4));
		
		// create airplain
		table = "Airplain";
    name = resultSet.getString("airplain");
    String fDate = resultSet.getString("date")+"T"+
                   resultSet.getString("time");
    String fNumber = resultSet.getString("flight_number");
    query = "SELECT * FROM "+table+" WHERE name IS '"+name+
            "' AND flightDate IS '"+fDate+"' AND flightNumber IS '"+
            fNumber+"';";

		rs = underStm.executeQuery(query);
    int size = rs.getInt(5);
		Airplain airplain = new Airplain(rs.getString("name"),
										 toBoolean(rs.getString("availableSeats"),size),
										 toBoolean(rs.getString("needsAssistance"),size),
										 toBoolean(rs.getString("wantsFood"),size));
		
		return new Flight(to, from, airplain, resultSet.getString("flight_number"),
						  LocalDateTime.parse(resultSet.getString("date")+"T"+
								  		 	  resultSet.getString("time")));
	}
	
	public Boolean[][] toBoolean(String arr, int size) {
		//System.out.println(arr.length());
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

	public Flight[] search() throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		ConnectToFlight("SELECT * FROM Flight;", new String[0]);
		return flights;
	}
	
	public Flight[] search(LocalDate date) throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		String ps = "SELECT * FROM Flight WHERE date IS ?;";
		String[] gildi = {date.toString()};
		ConnectToFlight(ps, gildi);
		return flights;
	}
	
	public Flight[] search(String airportCity, Boolean to) throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		String[] gildi = {airportCity};
		if(to) {
			String ps = "SELECT * FROM Flight WHERE EXISTS(SELECT * FROM Airport WHERE "
					+ "airportTo IS name AND city IS ?);";
			ConnectToFlight(ps, gildi);
		} else {
			String ps = "SELECT * FROM Flight WHERE EXISTS(SELECT * FROM Airport WHERE "
					+ "airportFrom IS name AND city IS ?);";
			ConnectToFlight(ps, gildi);
		}
		return flights;
	}
	
	public Flight[] search(LocalDate date, String airportCity, Boolean to) throws ClassNotFoundException {
    // Sækjum flug í gagnagrunnin
    String[] gildi = {date.toString(), airportCity};
		if(to) {
			String ps = "SELECT * FROM Flight WHERE date IS ? AND EXISTS(SELECT * FROM Airport WHERE "
					+ "airportTo IS name AND city IS ?);";
			ConnectToFlight(ps, gildi);
		} else {
			String ps = "SELECT * FROM Flight WHERE date IS ? AND EXISTS(SELECT * FROM Airport WHERE "
					+ "airportFrom IS name AND city IS ?);";
			ConnectToFlight(ps, gildi);
		}
		return flights;
	}
	
	public Flight[] search(String airportFrom, String airportTo) throws ClassNotFoundException {
    // Sækjum flug í gagnagrunnin
    String[] gildi = {airportFrom, airportTo};
    String ps = "SELECT * FROM Flight WHERE EXISTS(SELECT * FROM Airport WHERE "
					+ "airportFrom IS name AND city IS ?) AND EXISTS(SELECT * FROM Airport WHERE "
          + "airportTo IS name AND city IS ?);";
    ConnectToFlight(ps, gildi);
		return flights;
	}
	
	public Flight[] search(LocalDate date, String airportFrom, String airportTo) throws ClassNotFoundException {
    // Sækjum flug í gagnagrunnin
    String[] gildi = {date.toString(), airportFrom, airportTo};
    String ps = "SELECT * FROM Flight WHERE date IS ? AND EXISTS(SELECT * FROM Airport WHERE "
					+ "airportFrom IS name AND city IS ?) AND EXISTS(SELECT * FROM Airport WHERE "
          + "airportTo IS name AND city IS ?);";
    ConnectToFlight(ps, gildi);
		return flights;
	}
}