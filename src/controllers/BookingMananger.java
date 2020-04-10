package src.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import src.datastructures.Booking;
import src.datastructures.Person;
import src.datastructures.Flight;

public class BookingMananger {
  private Booking[] bookings;

  public Booking create(char s, int r, Person p, Flight flight) {
	  LocalDate localDate = LocalDate.now();
	  LocalDateTime now = localDate.atTime(LocalTime.now());
	  Booking booking = new Booking(s,r,p,flight,now);
	  return booking;
  }

  public void delete(Booking b) {
    
  }
}