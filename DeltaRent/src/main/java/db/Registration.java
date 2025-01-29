package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration {

	public static boolean registerUser(String email, String password, String nome, String cognome, String dataNascita,
			String nomeAzienda, String partitaIva) {
		try {
			if (nome != null) {
				Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(
						"INSERT INTO Utente (email, password, nome, cognome, dataDiNascita) VALUES (?, ?, ?, ?, ?)");
				stmt.setString(1, email);
				stmt.setString(2, password);
				stmt.setString(3, nome);
				stmt.setString(4, cognome);
				stmt.setString(5, dataNascita);

				int rowsAffected = stmt.executeUpdate();
				return rowsAffected > 0;
			} else {
				Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(
						"INSERT INTO Utente (email, password, nomeAzienda, partitaIVA, isPrivato) VALUES (?, ?, ?, ?, 0)");
				stmt.setString(1, email);
				stmt.setString(2, password);
				stmt.setString(3, nomeAzienda);
				stmt.setString(4, partitaIva);

				int rowsAffected = stmt.executeUpdate();
				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			// System.err.println("Errore durante la registrazione dell'utente: " +
			// e.getMessage());
			return false;
		}
	}
}
