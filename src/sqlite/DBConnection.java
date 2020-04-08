package src.sqlite;

import java.sql.*;
import java.time.LocalDateTime;

import src.datastructures.Flight;

public class DBConnection {
	public static void main(String[] args) throws ClassNotFoundException {
		
	
		Class.forName("org.sqlite.JDBC");
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/database/Flights.db");

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			
			// addar inn flugi í db - er þegar til því kemur villa
			String sql = "INSERT INTO flight values('Ísafjarðarflugvöllur','Reykjavíkurflugvöllur',"
					+ "'TF-123','IR-2345','2020-03-09','9-15')";
			statement.executeUpdate(sql);


		}
		
		catch(SQLException e) {System.err.println(e.getMessage()); }
		 finally {
		 	try {
		 		if(connection != null)
		 			connection.close();
		 	}
		 	catch(SQLException e) {
		 		System.err.println(e);
		 	}
		 }
		
	}

}
