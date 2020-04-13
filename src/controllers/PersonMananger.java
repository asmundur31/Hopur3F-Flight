package src.controllers;

import java.sql.*;
import src.datastructures.Person;

public class PersonMananger {

  private Person person;

  /* Fastayrðing gagna
        - person sú persóna sem sem var verið að leita af í þeim,
          tilfellum sem leitað er að persónu í gagnagrunninum.
  */

  // Aðferð sem tengist við gagnagrunnin Flights.db.
  // Notkun: ConnectToPerson(sql,gildi,insert)
  // Fyrir:  sql er strengur sem inniheldur SQL fyrirspurnina og
  //         gildi er listi af strengjum sem á eftir að setja inn
  //         í sql.
  //         insert er sanngildi sem segir til um hvort innsetning
  //         eða leit er framkvæmd á gagnagrunnin.
  // Eftir:  Búið er að setja stökin í gildi inn í sql og framkvæma
  //         leit eða innsetningu á gagnagrunninum. Ef insert var
  //         satt þá var gerð innsetning annars leit.
  private void ConnectToPerson(String sql, String[] gildi,
        Boolean insert) throws ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
		try {
			connection = DriverManager.getConnection(
        "jdbc:sqlite:src/database/Flights.db");
			
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

  // Aðferð sem bætir persónu inn í gagnagrunninn.
  // Notkun: pm.createPerson(p)
  // Fyrir:  pm er hlutur af taginu PersonMananger.
  //         p er persóna sem á að bæta inn í gagnagrunninn.
  // Eftir:  Búið er að bæta við persónu p inn í gagnagrunninn
  //         ef hún var ekki til annars gerum við ekkert.
  public void createPerson(Person p) throws ClassNotFoundException {
    // Athugum fyrst hvort þessi person er til
    String sql = "SELECT * FROM Person WHERE ssn IS ?";
    String[] gildi = {p.getSsn()};
    ConnectToPerson(sql, gildi, false);
    // Ef enginn persóna þá bætum við henni inn
    if(person == null) {
      sql = "INSERT INTO Person(name, ssn, bookings, email, phone_number) values(?,?,?,?,?);";
      String[] nyGildi = {p.getName(), p.getSsn(), "", p.getEmail(), p.getPhoneNumber()};
      ConnectToPerson(sql, nyGildi, true);
    }
  }

  // Aðferð sem leitar af persónu í gagnagrunninum.
  // Notkun: Person p = pm.getPerson(ssn)
  // Fyrir:  pm er hlutur af taginu PersonMananger.
  //         ssn er kennitala persónu sem á að finna í
  //         gagnagrunninum.
  // Eftir:  p er persóna með kennitölu ssn ef hún er til annars
  //         ef hún er ekki til þá er skilað null.
  public Person getPerson(String ssn) throws ClassNotFoundException {
    String sql = "SELECT * FROM Person WHERE ssn IS ?;";
    String[] gildi = {ssn};
    ConnectToPerson(sql, gildi, false);
    return person;
  }
  
  // Aðferð sem eyðir persónu p úr gagnagrunninum
  // Notkun: pm.deletePerson(p)
  // Fyrir:  pm er hlutur af taginu PersonMananger.
  //         p er persóna sem á að eyða út úr gagnagrunninum.
  // Eftir:  Búið er að eyða persónu p úr gagnagrunninum ef hún 
  //         var til.
  public void deletePerson(Person p) throws ClassNotFoundException {
    String sql = "DELETE FROM Person WHERE ssn IS ?;";
    String[] gildi = {p.getSsn()};
    ConnectToPerson(sql, gildi, true);
  }
}