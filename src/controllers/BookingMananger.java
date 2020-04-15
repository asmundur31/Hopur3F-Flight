package src.controllers;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import src.datastructures.*;

public class BookingMananger {

  private Booking[] bookings;
  private String availableSeats;

  /* Fastayrðing gagna
        - bookings er listi af bókunum sem á að skila ef leitað er
          af öllum bókunum sem einhver persóna hefur bókað.
        - availableSeats inniheldur streng með lausum sætum þegar
          á að uppfæra laus sæti í flugvél fyrir ákveðið flug. Það
          er uppfærsla á þessum streng þegar við bókum sæti.
  */

  // Aðferð sem tengist við gagnagrunnin Flights.db.
  // Notkun: ConnectToPerson(sql,gildi,type)
  // Fyrir:  sql er strengur sem inniheldur SQL fyrirspurnina og
  //         gildi er listi af strengjum sem á eftir að setja inn
  //         í sql.
  //         type er strengur sem inniheldur hvernig fyrirspurn sql
  //         er. type getur verið "update" eða "getSeats".
  // Eftir:  Ef type er "update" þá er búið að uppfæra 
  //         gagnagrunninn, ef type er "getSeats" þá er búið að 
  //         sækja í gagnagrunninn laus sæti í einhverju ákveðnu
  //         flugi.
  public void ConnectToBooking(String sql, String[] gildi,
        String type) throws ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(
        "jdbc:sqlite:src/database/Flights.db");

      PreparedStatement ps = connection.prepareStatement(sql);
      ps.clearParameters();
      for (int i = 1; i <= gildi.length; i++) {
        ps.setString(i, gildi[i - 1]);
      }
      ps.setQueryTimeout(30);
      if (type.equals("update")) {
        ps.executeUpdate();
      } else if(type.equals("getSeats")) {
        ResultSet resultSet = ps.executeQuery();
        availableSeats = resultSet.getString("availableSeats");
      } else if(type.equals("getBookings")) {
    	  ArrayList<Booking> list = new ArrayList<Booking>();
    	  ResultSet rs = ps.executeQuery();
    	  PersonMananger pm = new PersonMananger(); 
    	  FlightMananger fm = new FlightMananger();
    	  while (rs.next()) {    		
    		  list.add(new Booking(rs.getString("seat").charAt(0), rs.getInt("row"), 
    				   pm.getPerson(rs.getString("person")), 
    				   fm.flightFromBooking(rs.getString("flight")), 
    				   LocalDateTime.parse(rs.getString("booking_time"))));
    	  }
		    bookings = new Booking[list.size()];
		    list.toArray(bookings);
    	  
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    } finally {
      try {
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }

  // Notkun: bm.createBooking(b)
  // Fyrir:  bm er hlutur af taginu BookingMananger.
  //         b er bókun sem á að bæta við í gagnagrunninn.
  // Eftir:  Búið er að bæti við bókuninni b í gagnagrunninn og
  //         uppfæra sætið sem var bókað sem ekki laust.
  public void createBooking(Booking b) 
        throws ClassNotFoundException {
    // Uppfærum availableSeat, wantsFood og needsAssistance
    // breyturnar í töflunni Airplain
    String sql = "SELECT availableSeats FROM Airplain WHERE"+
                 " flightDate IS ? AND flightNumber IS ?;";
    String[] gild = {b.getFlight().getDate().toString(),
      b.getFlight().getFlightNumber()};
    ConnectToBooking(sql, gild, "getSeats");
    // Uppfæra availableSeats breytuna
    int row = 
      b.getFlight().getAirplain().getAvailableSeats()[0].length;
    int r = b.getRow()-1;
    int s = b.getSeat() - 'A';
    availableSeats = availableSeats.substring(0, r*row+s) + '0' +
                     availableSeats.substring(r*row+s+1);
    // Uppfæra needsAssistance breytuna
    Boolean[][] na = b.getFlight().getAirplain().getNeedsAssistance();
    String needsAssistance = "";
    for(int i=0; i<na.length; i++) {
      for(int j=0; j<na[0].length; j++) {
        if(na[i][j]) {
          needsAssistance = "1" + needsAssistance;
        } else {
          needsAssistance = "0" + needsAssistance;
        }
      }
    }
    Boolean[][] wf = b.getFlight().getAirplain().getWantsFood();
    String wantsFood = "";
    for(int i=0; i<na.length; i++) {
      for(int j=0; j<na[0].length; j++) {
        if(na[i][j]) {
          wantsFood = "1" + wantsFood;
        } else {
          wantsFood = "0" + wantsFood;
        }
      }
    }
    sql = "UPDATE Airplain SET availableSeats=?, needsAssistance=?,"+
          " wantsFood=? WHERE flightDate IS ? AND flightNumber IS ?;";
    String[] gildi = {
      availableSeats, 
      needsAssistance,
      wantsFood,
      b.getFlight().getDate().toString(), 
      b.getFlight().getFlightNumber()};
    ConnectToBooking(sql, gildi, "update");
    // Bæta við bókun í Booking töfluna.
    sql = "INSERT INTO Booking(row, seat, person, flight, " +
          "booking_time) values(?,?,?,?,?);";
    String[] nyGildi = {
      b.getRow()+"",
      b.getSeat()+"",
      b.getPerson().getSsn(),
      b.getFlight().getFlightNumber()+"|"+b.getFlight().getDate(),
      b.getBookingTime().toString()};
    ConnectToBooking(sql, nyGildi, "update");
  }

  public void delete(Booking b) {
    // Ef við viljum bjóða uppá að hætta við bókun
  }

  public Booking[] getBookings(Person p) throws ClassNotFoundException {
    // Ef við viljum bjóða uppá að sækja allar bókanir
    // fyrir einhverja manneskju p
	  String sql = "SELECT * FROM Booking WHERE person IS ?";
	  String [] gild = { p.getSsn() };
	  ConnectToBooking(sql, gild, "getBookings");
    return bookings;
  }
}