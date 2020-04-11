package src.controllers;

import java.sql.*;

import src.datastructures.*;

public class BookingMananger {
  private static Booking[] bookings;
  private static String availableSeats;

  public static void ConnectToBooking(String sql, String[] gildi, String type) throws ClassNotFoundException {
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
      if (type.equals("update")) {
        ps.executeUpdate();
      } else if(type.equals("getSeats")) {
        ResultSet resultSet = ps.executeQuery();
        availableSeats = resultSet.getString("availableSeats");
      } else if(type.equals("getBookings")) {
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()) {
          // Búa til booking út frá resultSet og setja í bookings
        }
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

  public static void createBooking(Booking b) throws ClassNotFoundException {
    // Uppfærum available_seat breytunna í töflunni Airplain
    String sql = "SELECT availableSeats FROM Airplain WHERE "+
                 "flightDate IS ? AND flightNumber IS ?;";
    String[] gild = {b.getFlight().getDate().toString(), b.getFlight().getFlightNumber()};
    ConnectToBooking(sql, gild, "getSeats");
    // Uppfæra availableSeats breytuna
    int row = b.getFlight().getAirplain().getAvailableSeats()[0].length;
    int r = b.getRow()-1;
    int s = b.getSeat() - 'A';
    availableSeats = availableSeats.substring(0, r*row+s) + '0' +
                     availableSeats.substring(r*row+s+1);
    sql = "UPDATE Airplain SET availableSeats=? WHERE " +
          "flightDate IS ? AND flightNumber IS ?;";
    String[] gildi = {availableSeats, b.getFlight().getDate().toString(), b.getFlight().getFlightNumber()};
    ConnectToBooking(sql, gildi, "update");
    // Bæta við bókun í Booking töfluna.
    sql = "INSERT INTO Booking(row, seat, person, flight, " +
          "booking_time) values(?,?,?,?,?);";
    String[] nyGildi = {b.getRow()+"", b.getSeat()+"", b.getPerson().getSsn(),
        b.getFlight().getFlightNumber()+"|"+b.getFlight().getDate(), b.getBookingTime().toString()};
    ConnectToBooking(sql, nyGildi, "update");
    // Uppfæra bookings í person töflunni fyrir person p.
    // Ég held að það sé óþarfi að hafa dálkinn bookings í person
    // töflunni því við getum sameinað töflurnar booking og person
    // með SQL skipunum
  }

  public static void delete(Booking b) {
    // Ef við viljum bjóða uppá að hætta við bókun
  }

  public static Booking[] getBookings(Person p) {
    // Ef við viljum bjóða uppá að sækja allar bókanir
    // fyrir einhverja manneskju p
    return bookings;
  }
}