package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String DB_URL = "jdbc:sqlite:DeltaRentDataBase.db";
	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(DB_URL);
			}
			return conn;
		} catch (SQLException e) {
			System.err.println("Errore durante la connessione al database: " + e.getMessage());
			return null;
		}
	}

	public static void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			System.err.println("Errore durante la chiusura della connessione: " + e.getMessage());
		}
	}
}
