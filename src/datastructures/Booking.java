package src.datastructures;

import java.time.LocalDate;

public class Booking {
  private char seat;
  private int row;
  private Person person;
  private Flight flight;
  private LocalDate bookingTime;

  public Booking(char seat, int row, Person person, Flight flight, LocalDate bookingTime) {
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

  public LocalDate getBookingTime() {
    return bookingTime;
  }
}