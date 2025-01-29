package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gui.HomePage;
import prenotazione.Prenotazione;
import veicolo.Automobile;
public class GestionePrenotazioni {

	// Metodo per ottenere tutte le prenotazioni passate per un utente
	public static List<Prenotazione> getPrenotazioniPassate(String emailUtente) {
		List<Prenotazione> prenotazioni = new ArrayList<>();
		String query = "SELECT * \r\n"
				+ "FROM Prenotazione \r\n"
				+ "WHERE emailUtente = ? \r\n"
				+ "  AND SUBSTR(inizioPrenotazione, 7, 4) || '-' || SUBSTR(inizioPrenotazione, 4, 2) || '-' || SUBSTR(inizioPrenotazione, 1, 2) || ' ' || SUBSTR(inizioPrenotazione, 12, 5) <= datetime('now');\r\n"
				+ "";

		try {
			Connection conn = DatabaseConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, emailUtente);
			ResultSet rs = stmt.executeQuery();

			int id = 0;
			String targa = null;
			String dataPrenotazione = null;
			String inizioPrenotazione = null;
			String finePrenotazione = null;
			double prezzo=0;

			while (rs.next()) {
				id = rs.getInt("ID");
				targa = rs.getString("targa");
				dataPrenotazione = rs.getString("dataPrenotazione");
				inizioPrenotazione = rs.getString("inizioPrenotazione");
				finePrenotazione = rs.getString("finePrenotazione");
				prezzo = rs.getDouble("prezzo");

				String query2 = "SELECT * FROM Veicolo WHERE targa = ?";

				PreparedStatement stmt2 = conn.prepareStatement(query2);
				stmt2.setString(1, targa);

				ResultSet rs2 = stmt2.executeQuery();

				Automobile auto = null;

				while (rs2.next()) {
					String targa2 = rs2.getString("targa");
					String marca = rs2.getString("marca");
					String modello = rs2.getString("modello");
					int prezzoOrario = rs2.getInt("prezzoOrario");
					String pathImg = rs2.getString("pathImg");
					String alimentazione = rs2.getString("alimentazione");

		                // Leggi e processa il campo pathImgs
		            String pathImgsString = rs2.getString("pathImgs");
		            String[] pathImgs = pathImgsString != null ? pathImgsString.split("\\n") : new String[0];
					auto = new Automobile(targa2, marca, modello, prezzoOrario, pathImg, alimentazione,pathImgs);

				}
				Prenotazione prenotazione = new Prenotazione(id, inizioPrenotazione, finePrenotazione, HomePage.loggedUser, auto, dataPrenotazione, prezzo);
				prenotazioni.add(prenotazione);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prenotazioni;
	}

	// Metodo per aggiungere una nuova prenotazione
	public boolean aggiungiPrenotazione(String emailUtente, String targa, String inizioPrenotazione, String finePrenotazione) {
		String query = "INSERT INTO Prenotazione (emailUtente, targa, dataPrenotazione, inizioPrenotazione, finePrenotazione) VALUES (?, ?, DATE('now'), ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, emailUtente);
			stmt.setString(2, targa);
			stmt.setString(3, inizioPrenotazione);
			stmt.setString(4, finePrenotazione);

			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Metodo per cancellare una prenotazione
	public boolean cancellaPrenotazione(int ID) {
		String query = "DELETE FROM Prenotazione WHERE id = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, ID);

			int rowsDeleted = stmt.executeUpdate();
			return rowsDeleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<Prenotazione> getPrenotazioniFuture(String emailUtente) {

		List<Prenotazione> prenotazioni = new ArrayList<>();
		String query = "SELECT * \r\n"
				+ "FROM Prenotazione \r\n"
				+ "WHERE emailUtente = ? \r\n"
				+ "  AND SUBSTR(inizioPrenotazione, 7, 4) || '-' || SUBSTR(inizioPrenotazione, 4, 2) || '-' || SUBSTR(inizioPrenotazione, 1, 2) || ' ' || SUBSTR(inizioPrenotazione, 12, 5) > datetime('now');\r\n";
		try {
			Connection conn = DatabaseConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, emailUtente);
			ResultSet rs = stmt.executeQuery();

			int id = 0;
			String targa = null;
			String dataPrenotazione = null;
			String inizioPrenotazione = null;
			String finePrenotazione = null;
			double prezzo=0;
			
		
			while (rs.next()) {
				id = rs.getInt("ID");
				targa = rs.getString("targa");
				dataPrenotazione = rs.getString("dataPrenotazione");
				inizioPrenotazione = rs.getString("inizioPrenotazione");
				finePrenotazione = rs.getString("finePrenotazione");
				prezzo = rs.getDouble("prezzo");
				

				String query2 = "SELECT * FROM Veicolo WHERE targa = ?";

				PreparedStatement stmt2 = conn.prepareStatement(query2);
				stmt2.setString(1, targa);

				ResultSet rs2 = stmt2.executeQuery();

				Automobile auto = null;

				while (rs2.next()) {
					String targa2 = rs2.getString("targa");
					String marca = rs2.getString("marca");
					String modello = rs2.getString("modello");
					int prezzoOrario = rs2.getInt("prezzoOrario");
					String pathImg = rs2.getString("pathImg");
					String alimentazione = rs2.getString("alimentazione");

		                // Leggi e processa il campo pathImgs
		            String pathImgsString = rs2.getString("pathImgs");
		            String[] pathImgs = pathImgsString != null ? pathImgsString.split("\\n") : new String[0];
					auto = new Automobile(targa2, marca, modello, prezzoOrario, pathImg, alimentazione,pathImgs);

				}
				Prenotazione prenotazione = new Prenotazione(id, inizioPrenotazione, finePrenotazione, HomePage.loggedUser, auto, dataPrenotazione, prezzo);
				prenotazioni.add(prenotazione);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prenotazioni;
	}

	public static void annullaPrenotazione(Prenotazione prenotazione) {
		
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query="DELETE FROM Prenotazione WHERE id=?";

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, prenotazione.getID());
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}