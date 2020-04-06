package src.datastructures;

import java.time.LocalDateTime;

public class Flight {
  private String to;
  private String from;
  private String airplain;
  private String flightNumber;
  private LocalDateTime date;

  public Flight(String from, String to, String airplain, 
		  String flightNumber, LocalDateTime date) {
    this.to = to;
    this.from = from;
    this.airplain = airplain;
    this.flightNumber = flightNumber;
    this.date = date;
  }

 /* public Airport getTo() {
    return to;
  }

  public Airport getFrom() {
    return from;
  }

  public Airplain getAirplain() {
    return airplain;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public LocalDateTime getDate() {
    return date;
  }*/
  
  public String getTo() {
	    return to;
	  }

	  public String getFrom() {
	    return from;
	  }

	  public String getAirplain() {
	    return airplain;
	  }

	  public String getFlightNumber() {
	    return flightNumber;
	  }

	  public LocalDateTime getDate() {
	    return date;
	  }
  
  public String toString() {
	  return "To: " + getTo() + " From: " + getFrom() + " Airplain: " + getAirplain()
	  			+" FlightNumber: "+ getFlightNumber() + " Date: " + getDate();
  }
}