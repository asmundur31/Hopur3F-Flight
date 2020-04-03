package src.datastructures;

import java.time.LocalDateTime;

public class Flight {
  private Airport to;
  private Airport from;
  private Airplain airplain;
  private String flightNumber;
  private LocalDateTime date;

  public Flight(Airport from, Airport to, Airplain airplain, 
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
}