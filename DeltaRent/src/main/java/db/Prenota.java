package db;

import java.text.SimpleDateFormat;

import prenotazione.Prenotazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Prenota {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	public void aggiungiPrenotazione(Prenotazione prenotazione) throws SQLException {
		String query = "INSERT INTO Prenotazione (inizioPrenotazione, finePrenotazione, emailUtente, targa, dataPrenotazione, prezzo) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);

			String inizioStr = prenotazione.getInizioPrenotazione();
			String fineStr = prenotazione.getFinePrenotazione();
			String now = DATE_FORMAT.format(System.currentTimeMillis());

			stmt.setString(1, inizioStr);
			stmt.setString(2, fineStr);
			stmt.setString(3, prenotazione.getUtente().getEmail());
			stmt.setString(4, prenotazione.getVeicolo().getTarga());
			stmt.setString(5, now);
			stmt.setDouble(6, prenotazione.getPrezzo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Errore durante la connessione al database: " + e.getMessage());
		}
	}
}