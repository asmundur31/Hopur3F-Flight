package src.controllers;

import src.datastructures.Booking;
import src.datastructures.Person;
import src.datastructures.Flight;

public class BookingMananger {
  private Booking[] bookings;

  public BookingMananger() {
  }

  public Booking create(char s, int r, Person p, Flight flight) {
    Booking booking = new Booking();
    return booking;
  }

  public void delete(Booking b) {
    
  }
}