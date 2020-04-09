package src.datastructures;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
  private Airport to;
  private Airport from;
  private Airplain airplain;
  private String flightNumber;
  private LocalDateTime date;

  public Flight(Airport to, Airport from, Airplain airplain, 
		  String flightNumber, LocalDateTime date) {
    this.to = to;
    this.from = from;
    this.airplain = airplain;
    this.flightNumber = flightNumber;
    this.date = date;
  }

  
  public Airport getTo() {
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
  }
  
  public String toString() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy hh:mm");

	  String text = date.format(formatter);
	  return getFrom() +" To "+getTo() + ",    " + text;
  }
}