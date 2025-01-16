package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import GUI.HomePage;
import Prenotazione.Prenotazione;
import Veicolo.Automobile;

public class GestionePrenotazioni {

	// Metodo per ottenere tutte le prenotazioni passate per un utente
	public List<Prenotazione> getPrenotazioniPassate(String emailUtente) {
		List<Prenotazione> prenotazioni = new ArrayList<>();
		String query = "SELECT * FROM Prenotazione WHERE emailUtente = ?";

		try {
			Connection conn = DatabaseConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, emailUtente);
			ResultSet rs = stmt.executeQuery();

			int id = 0;
			String targa = null, dataPrenotazione = null, inizioPrenotazione = null, finePrenotazione = null;

			while (rs.next()) {
				id = rs.getInt("ID");
				targa = rs.getString("targa");
				dataPrenotazione = rs.getString("dataPrenotazione");
				inizioPrenotazione = rs.getString("inizioPrenotazione");
				finePrenotazione = rs.getString("finePrenotazione");

				String query2 = "SELECT * FROM Veicolo WHERE targa = ?";

				PreparedStatement stmt2 = conn.prepareStatement(query2);
				stmt2.setString(1, targa);

				ResultSet rs2 = stmt2.executeQuery();

				Automobile auto = null;

				while (rs2.next()) {
					String targa2 = rs2.getString("targa");
					String marca = rs2.getString("marca");
					String modello = rs2.getString("modello");
					boolean disponibile = rs2.getBoolean("disponibile");
					int prezzoOrario = rs2.getInt("prezzoOrario");
					String pathImg = rs2.getString("pathImg");
					String alimentazione = rs2.getString("alimentazione");
					auto = new Automobile(targa2, marca, modello, disponibile, prezzoOrario, pathImg, alimentazione);

				}
				Prenotazione prenotazione = new Prenotazione(id, inizioPrenotazione, finePrenotazione, HomePage.loggedUser, auto, dataPrenotazione);
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
}