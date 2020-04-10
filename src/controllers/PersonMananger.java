package src.controllers;

import java.sql.*;
import src.datastructures.Person;

public class PersonMananger {
  private static Person person;

  public static void ConnectToPerson(String sql, String[] gildi, Boolean insert) throws ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/database/Flights.db");
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.clearParameters();
			for(int i=1; i<=gildi.length; i++) {
				ps.setString(i,gildi[i-1]);
			}
			ps.setQueryTimeout(30);
      if(insert) {
        ps.executeUpdate();
      } else {
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()) {
          String name = resultSet.getString("name");
          String ssn = resultSet.getString("ssn");
          String email = resultSet.getString("email");
          String phone = resultSet.getString("phone_number");
          person = new Person(name, ssn, email, phone);
        }
      }
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
		 	try {
		 		if(connection != null)
		 			connection.close();
		 	} catch(SQLException e) {
		 		System.err.println(e);
		 	}
		}
  }

  // Aðferð sem bætir persónu inn í gagnagrunninn
  public static Person createPerson(String name, String ssn, String email, String phoneNumber) throws ClassNotFoundException {
    // Athugum fyrst hvort þessi person er til
    person = null;
    String sql = "SELECT * FROM Person WHERE ssn IS ?";
    String[] gildi = {ssn};
    ConnectToPerson(sql, gildi, false);
    // Ef enginn persóna þá bætum við henni inn
    if(person == null) {
      sql = "INSERT INTO Person(name, ssn, bookings, email, phone_number) values(?,?,?,?,?);";
      String[] nyGildi = {name, ssn, "", email, phoneNumber};
      ConnectToPerson(sql, nyGildi, true);
    }
    return person;
  }

  // Aðferð sem skilar persónu með kennitölu ssn
  // Ef hún er ekki til þá er skilað Null
  public static Person getPerson(String ssn) throws ClassNotFoundException {
    String sql = "SELECT * FROM Person WHERE ssn IS ?;";
    String[] gildi = {ssn};
    ConnectToPerson(sql, gildi, false);
    return person;
  }
  
  // Aðferð sem eyðir persónu með kennitölu ssn úr gagnagrunninum
  public static void deletePerson(String ssn) throws ClassNotFoundException {
    String sql = "DELETE FROM Person WHERE ssn IS ?;";
    String[] gildi = {ssn};
    ConnectToPerson(sql, gildi, true);
  }
}