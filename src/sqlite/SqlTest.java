package src.sqlite;

import java.sql.*;

public class SqlTest {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/database/.db");

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);

			statement.executeUpdate("DROP TABLE IF EXISTS person");
			statement.executeUpdate("CREATE TABLE person(id INTEGER, name STRING)");
			int ids [] = {1,2,3,4,5};
			String names [] = {"Peter", "Pallar", "William", "Paul", "James Bond"};

			for(int i=0;i<ids.length;i++) {
				statement.executeUpdate("INSERT INTO person values(' "+ids[i]+"', '"+names[i]+"')");
			}

			statement.executeUpdate("INSERT INTO person values(6,'John')");

			ResultSet resultSet = statement.executeQuery("SELECT * from person");
			while(resultSet.next()) {
				System.out.println("name = " + resultSet.getString("name"));
				System.out.println("id = " + resultSet.getInt("id"));
			}
			
			
			resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM person");
			int count = resultSet.getInt("count");
			System.out.print(count);

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
