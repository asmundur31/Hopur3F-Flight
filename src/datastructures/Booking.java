package src.datastructures;

import java.time.LocalDateTime;

public class Booking {
  private char seat;
  private int row;
  private Person person;
  private Flight flight;
  private LocalDateTime bookingTime;

  /* Fastayrðing gagna:
        - seat inniheldur sætið sem er bókað og er bókstafur á bilinu
          A,...,breidd flugvélar.
        - row inniheldur röðina sem er bókuð og er tala á bilinu
          1,...,lengd flugvélar.
        - person er manneskjan sem framkvæmdi bókunina.
        - flight er flugið sem er bókað.
        - bookingTime er tíminn sem bókunin var gerð.
  */

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
    String bokun = "Þín bókun:\n";
    bokun += "=====================================";
    bokun += "\nNafn:      "+person.getName();
    bokun += "\nKennitala: "+person.getSsn();
    bokun += "\nNetfang:   "+person.getEmail();
    bokun += "\n";
    bokun += "\nFlugið:";
    bokun += "\nFlugnúmer:  "+flight.getFlightNumber();
    bokun += "\nFrá:        "+flight.getFrom();
    bokun += "\nTil:        "+flight.getTo();
    bokun += "\nDagsetning: "+flight.getDate().toString();
    bokun += "\n";
    bokun += "\nRöð:  "+row;
    bokun += "\nSæti: "+seat;
    bokun += "\n";
    bokun += "\nTími bókunnar: "+bookingTime.toString();
    bokun += "\n=====================================";
    return bokun;
  }
}