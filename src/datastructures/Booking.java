package src.datastructures;

import java.time.LocalDateTime;

public class Booking {
  private char seat;
  private int row;
  private Person person;
  private Flight flight;
  private LocalDateTime bookingTime;

  public Booking(char seat, int row, Person person, Flight flight, LocalDateTime bookingTime) {
    this.seat = seat;
    this.row = row;
    this.person = person;
    this.flight = flight;
    this.bookingTime = bookingTime;
  }

  public char getSeat() {
    return seat;
  }

  public int getRow() {
    return row;
  }

  public void setSeat(char s) {
    seat = s;
  }

  public void setRow(int r) {
    row = r;
  }

  public Person getPerson() {
    return person;
  }

  public Flight getFlight() {
    return flight;
  }

  public LocalDateTime getBookingTime() {
    return bookingTime;
  }

  public String toString() {
    String bokun = "Þín bókun:";
    bokun += "=====================================";
    bokun += "\nNafn: "+person.getName();
    bokun += "\nKennitala: "+person.getSsn();
    bokun += "\nNetfang: "+person.getEmail();
    bokun += "\n";
    bokun += "\nFlugið:";
    bokun += "\nFlugnúmer: "+flight.getFlightNumber();
    bokun += "\nFrá: "+flight.getFrom();
    bokun += "\nTil: "+flight.getTo();
    bokun += "\nDagsetning: "+flight.getDate().toString();
    bokun += "\n";
    bokun += "\nSæti: "+seat;
    bokun += "\nRöð: "+row;
    bokun += "\n";
    bokun += "\nTími bókunnar: "+bookingTime.toString();
    bokun += "=====================================";
    return bokun;
  }
}