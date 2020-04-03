package src.controllers;

import java.time.LocalDate;
import java.util.Date;

import src.datastructures.Booking;
import src.datastructures.Person;
import src.datastructures.Flight;

public class BookingMananger {
  private Booking[] bookings;

  public BookingMananger() {
  }

  public Booking create(char s, int r, Person p, Flight flight) {
	  LocalDate	now = LocalDate.now();
	  Booking booking = new Booking(s,r,p,flight,now);
	  return booking;
  }

  public void delete(Booking b) {
    
  }
}