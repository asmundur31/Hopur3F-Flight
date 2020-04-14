package src.controllers;

import src.datastructures.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightMananger {

	private Flight[] flights;

  /* Fastayrðing gagna
        - flights er listi af flugum sem á að skila eftir hverja
          leitaraðferð. Hann er tómur ef engin flug finnast annars
          inniheldur hann öll flug sem uppfylla skilyrði leitar-
          aðferðarinnar.
  */

  // Aðferð sem tengist við gagnagrunnin Flights.db.
  // Notkun: connectToFlight(sql,gildi)
  // Fyrir:  sql er strengur sem inniheldur SQL fyrirspurnina og
  //         gildi er listi af strengjum sem á eftir að setja inn
  //         í sql.
  // Eftir:  Búið er að setja stökin í gildi inn í sql, framkvæma
  //         leitina í gagnagrunninum og setja öll flug sem fundust
  //         í listan flights.
  private void connectToFlight(String sql, String[] gildi)
        throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
        "jdbc:sqlite:src/database/Flights.db");
			
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
  
  // Hjálparfall við ConnectToFlight sem býr til nýtt flug.
  // Notkun: Flight f = newFlight(resultSet,underStm)
  // Fyrir:  resultSet er útkoman úr SQL fyrirspurninni sem var
  //         framkvæmd í ConnectToFlight og underStm er Statement
  //         sem hefur tengingu við gagnagrunnin.
  // Eftir:  Út frá resultSet er búið til nýtt flug og sett í f.
	private Flight newFlight(ResultSet resultSet, Statement underStm) 
			  throws SQLException {
		// create destination airport
		String table = "airport";
		String name = resultSet.getString("airportTo");
    String query = "SELECT * FROM " + table + " WHERE name IS '" +
                   name + "'";

		ResultSet rs = underStm.executeQuery(query);		
		Airport to = new Airport(rs.getString(1),
								 rs.getString(2),
								 rs.getFloat(3),
								 rs.getFloat(4));
		
		// create departure airport
		name = resultSet.getString("airportFrom");
    query = "SELECT * FROM " + table + " WHERE name IS '" +
            name + "'";

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
		
    return new Flight(
      to,
      from, 
      airplain, 
      resultSet.getString("flight_number"),
			LocalDateTime.parse(resultSet.getString("date")+"T"+
                           resultSet.getString("time")));
	}
  
  // Hjálparfall fyrir newFlight sem breytir streng í tvívítt
  // Boolean fylki.
  // Notkun: Boolean[][] b = toBoolean(arr,size)
  // Fyrir:  arr er strengur sem á að breyta yfir í tvívítt Boolean
  //         fylki og size er stærð strengsins, þar sem einingar
  //         sætið í tölunni segir til um fjölda dálka en hinar
  //         tölurnar segja til um fjölda raða.
  // Eftir:  b er tvívítt rxc Boolean fylki, þar sem r er allar 
  //         tölurnar nema talan í einingarsætinu og c er talan í 
  //         einingar sætinu.
  // Ath.:   T.d. ef size = 104 þá eru 4 dálkar og 10 raðir.
	private Boolean[][] toBoolean(String arr, int size) {
    int row = size/10;
		int col = size%10;
		Boolean[][] bool = new Boolean[row][col];
		int counter = col*row;
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(arr.charAt(--counter) == '1') bool[i][j] = true;
				else bool[i][j] = false;
			}
		}
		return bool;
	}

  // Notkun: Flight[] flights = fm.search()
  // Fyrir:  fm er hlutur af taginu FlightMananger
  // Efrir:  flights inniheldur öll flug í gagnagrunninum.
	public Flight[] search() throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		connectToFlight("SELECT * FROM Flight;", new String[0]);
		return flights;
	}
  
  // Notkun: Flight[] flights = fm.search(d)
  // Fyrir:  fm er hlutur af taginu FlightMananger og d er 
  //         brottfaratími sem notandi sló inn.
  // Efrir:  flights inniheldur öll flug í gagnagrunninum sem hafa
  //         brottfaratíma d.
  public Flight[] search(LocalDate date) 
        throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		String ps = "SELECT * FROM Flight WHERE date IS ?;";
		String[] gildi = {date.toString()};
		connectToFlight(ps, gildi);
		return flights;
  }
  
	// Notkun: Flight[] flights = fm.search(airportCity,to)
  // Fyrir:  fm er hlutur af taginu FlightMananger.
  //         to er sanngildi hvort eigi að leita eftir brottfarar-
  //         stað eða komustað.
  //         airportCity er borgin/bærin sem notandinn langar að 
  //         fljúga frá/til.
  // Efrir:  flights inniheldur öll flug í gagnagrunninum sem fljúga
  //         frá/til airportCity (fer eftir því hvort to er satt eða
  //         ósatt).
  public Flight[] search(String airportCity, Boolean to)
        throws ClassNotFoundException {
		// Sækjum flug í gagnagrunnin
		String[] gildi = {airportCity};
		if(to) {
      String ps = "SELECT * FROM Flight WHERE EXISTS(SELECT * FROM"+
                  " Airport WHERE airportTo IS name AND city IS ?);";
			connectToFlight(ps, gildi);
		} else {
      String ps = "SELECT * FROM Flight WHERE EXISTS(SELECT * FROM"+
                  " Airport WHERE airportFrom IS name AND city IS ?);";
			connectToFlight(ps, gildi);
		}
		return flights;
	}
  
  // Notkun: Flight[] flights = fm.search(d,airportCity,to)
  // Fyrir:  fm er hlutur af taginu FlightMananger.
  //         to er sanngildi hvort eigi að leita eftir brottfarar-
  //         stað eða komustað.
  //         airportCity er borgin/bærin sem notandinn langar að 
  //         fljúga frá/til.
  //         d er brottfarartími sem notandan langar að fara.
  // Efrir:  flights inniheldur öll flug í gagnagrunninum sem fljúga
  //         frá/til airportCity (fer eftir því hvort to er satt eða
  //         ósatt) þar sem brottfarartíminn er d.
  public Flight[] search(LocalDate date, String airportCity,
        Boolean to) throws ClassNotFoundException {
    // Sækjum flug í gagnagrunnin
    String[] gildi = {date.toString(), airportCity};
		if(to) {
      String ps = "SELECT * FROM Flight WHERE date IS ? AND EXISTS"+
                  "(SELECT * FROM Airport WHERE airportTo IS name "+
                  "AND city IS ?);";
			connectToFlight(ps, gildi);
		} else {
      String ps = "SELECT * FROM Flight WHERE date IS ? AND EXISTS"+
                  "(SELECT * FROM Airport WHERE airportFrom IS name"+
                  " AND city IS ?);";
			connectToFlight(ps, gildi);
		}
		return flights;
	}
  
  // Notkun: Flight[] flights = fm.search(airportFrom,airportTo)
  // Fyrir:  fm er hlutur af taginu FlightMananger.
  //         airportFrom er borgin/bærin sem notandinn langar að 
  //         fljúga frá.
  //         airportTo er borgin/bærin sem notandinn langar að 
  //         fljúga til.
  // Efrir:  flights inniheldur öll flug í gagnagrunninum sem fljúga
  //         frá airportFrom til airportTo.
  public Flight[] search(String airportFrom, String airportTo)
        throws ClassNotFoundException {
    // Sækjum flug í gagnagrunnin
    String[] gildi = {airportFrom, airportTo};
    String ps = "SELECT * FROM Flight WHERE EXISTS(SELECT * FROM"+
                " Airport WHERE airportFrom IS name AND city IS ?)"+
                " AND EXISTS(SELECT * FROM Airport WHERE airportTo"+
                " IS name AND city IS ?);";
    connectToFlight(ps, gildi);
		return flights;
	}
  
  // Notkun: Flight[] flights = fm.search(d,airportFrom,airportTo)
  // Fyrir:  fm er hlutur af taginu FlightMananger.
  //         d er brottfarartími sem notandan langar að fara.
  //         airportFrom er borgin/bærin sem notandinn langar að 
  //         fljúga frá.
  //         airportTo er borgin/bærin sem notandinn langar að 
  //         fljúga til.
  // Efrir:  flights inniheldur öll flug í gagnagrunninum sem fljúga
  //         frá airportFrom til airportTo með brottfaratíman d.
  public Flight[] search(LocalDate date, String airportFrom,
        String airportTo) throws ClassNotFoundException {
    // Sækjum flug í gagnagrunnin
    String[] gildi = {date.toString(), airportFrom, airportTo};
    String ps = "SELECT * FROM Flight WHERE date IS ? AND EXISTS"+
                "(SELECT * FROM Airport WHERE airportFrom IS name"+
                " AND city IS ?) AND EXISTS(SELECT * FROM Airport"+
                " WHERE airportTo IS name AND city IS ?);";
    connectToFlight(ps, gildi);
		return flights;
  }
  
  public Flight flightFromBooking(String string) throws ClassNotFoundException {
	  String[] s1 = string.split("\\|");
	  String[] s2 = s1[1].split("T");
	  String[] g = {s1[0], s2[0], s2[1]};
	  String s = "SELECT * FROM flight WHERE flight_number IS ? AND date IS ? " +
	  			 "AND ?";
	  connectToFlight(s, g);
	  return flights[0];
	  
  }

  public void makeFlight() {
	// TODO Auto-generated method stub
	
  }
}