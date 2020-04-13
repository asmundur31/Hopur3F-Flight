package src.datastructures;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

  // Smiður fyrir bókun
  public Booking(char seat, int row, Person person, Flight flight, LocalDateTime bookingTime) {
    this.seat = seat;
    this.row = row;
    this.person = person;
    this.flight = flight;
    this.bookingTime = bookingTime;
  }

  // Notkun: char s = b.getSeat()
  // Fyrir:  b er hlutur af taginu Booking.
  // Eftir:  s er sætið sem er bókað, A<=s<=breidd vélar.
  public char getSeat() {
    return seat;
  }

  // Notkun: int r = b.getRow()
  // Fyrir:  b er hlutur af taginu Booking.
  // Eftir:  r er röðin sem er bókað, 1<=r<=lengd vélar.
  public int getRow() {
    return row;
  }

  // Notkun: Person p = b.getPerson()
  // Fyrir:  b er hlutur af taginu Booking.
  // Eftir:  p er persónan sem bókaði þessa bókun.
  public Person getPerson() {
    return person;
  }

  // Notkun: Flight f = b.getFlight()
  // Fyrir:  b er hlutur af taginu Booking.
  // Eftir:  f er flugið sem bókunin bókar.
  public Flight getFlight() {
    return flight;
  }

  // Notkun: LocalDateTime ldt = b.getBookingTime()
  // Fyrir:  b er hlutur af taginu Booking.
  // Eftir:  ldt er tíminn sem bókuninn var framkvæmd.
  public LocalDateTime getBookingTime() {
    return bookingTime;
  }

  // toString aðferð fyrir bókun.
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    String dagsetning = flight.getDate().format(formatter);
    bokun += "\nDagsetning: "+dagsetning;
    bokun += "\n";
    bokun += "\nRöð:  "+row;
    bokun += "\nSæti: "+seat;
    bokun += "\n";
    String timiBokun = bookingTime.format(formatter);
    bokun += "\nTími bókunnar: "+timiBokun;
    bokun += "\n=====================================";
    return bokun;
  }
}