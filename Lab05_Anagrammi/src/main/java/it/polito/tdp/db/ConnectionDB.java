package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	// check user e password
		static private final String jdbcUrl = "jdbc:mysql://localhost/dizionario?user=root&password=Caraglio199627";

		public static Connection getConnection() {

			try {
					Connection connection = DriverManager.getConnection(jdbcUrl);
					return connection;

			} catch (SQLException e) {

				e.printStackTrace();
				throw new RuntimeException("Cannot get a connection " + jdbcUrl, e);
			}
		}

}
