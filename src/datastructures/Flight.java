package src.datastructures;

import java.util.Date;

public class Flight {
  private Airport to;
  private Airport from;
  private Airplain airplain;
  private String flightNumber;
  private Date date;

  public Flight(Airport to, Airport from, Airplain airplain, String flightNumber, Date date) {
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

  public Date getDate() {
    return date;
  }
}