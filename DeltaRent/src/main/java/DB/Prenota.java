package DB;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Prenotazione.Prenotazione;

public class Prenota {
	
	 private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	public void aggiungiPrenotazione(Prenotazione prenotazione) throws SQLException {
		String query = "INSERT INTO Prenotazione (inizioPrenotazione, finePrenotazione, emailUtente, targa, dataPrenotazione) VALUES (?, ?, ?, ?, ?)";

		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			
			String inizioStr = DATE_FORMAT.format(prenotazione.getInizioPrenotazione());
            String fineStr = DATE_FORMAT.format(prenotazione.getFinePrenotazione());
            String now= DATE_FORMAT.format(System.currentTimeMillis());


			stmt.setString(1, inizioStr);
			stmt.setString(2, fineStr);
			stmt.setString(3, prenotazione.getUtente().getEmail());
			stmt.setString(4, prenotazione.getVeicolo().getTarga());
			stmt.setString(5, now);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Errore durante la connessione al database: " + e.getMessage());
		}
	}
}