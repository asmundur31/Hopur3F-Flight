package src.controllers;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import src.datastructures.*;

public class BookingMananger {
  private static Booking[] bookings;

  public static void ConnectToPerson(String sql, String[] gildi, Boolean insert) throws ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:src/database/Flights.db");

      PreparedStatement ps = connection.prepareStatement(sql);
      ps.clearParameters();
      for (int i = 1; i <= gildi.length; i++) {
        ps.setString(i, gildi[i - 1]);
      }
      ps.setQueryTimeout(30);
      if (insert) {
        ps.executeUpdate();
      } else {
        ResultSet resultSet = ps.executeQuery();
        // Vantar
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    } finally {
      try {
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }

  public static void createBooking(Booking b, Person p) {
    // Hér þarf að uppfæra airplain þannig að sætið sem var verið
    // að bóka.

    // Bæta við bókun í Booking töfluna.

    // Uppfæra bookings í person töflunni fyrir person p.
  }

  public static void delete(Booking b) {
    // Ef við viljum bjóða uppá að hætta við bókun
  }

  public static Booking[] getBookings(Person p) {
    // Ef við viljum bjóða uppá að prenta út allar bókanir
    // fyrir einhverja manneskju
    return bookings;
  }
}