package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DatabaseConnection;
import veicolo.Veicolo;

public class Disponibilita {

	public static boolean verificaDisponibilita(Veicolo veicolo, Date dataInizio, Date dataFine) {
		boolean disponibile = true; // Imposta disponibile a true per default

		String query = "SELECT * \n" + "FROM Prenotazione \n" + "WHERE targa = ? \n" + "  AND (\n"
				+ "       SUBSTR(finePrenotazione, 7, 4) || '-' || SUBSTR(finePrenotazione, 4, 2) || '-' || SUBSTR(finePrenotazione, 1, 2) || ' ' || SUBSTR(finePrenotazione, 12, 5) > ?\n"
				+ "       AND SUBSTR(inizioPrenotazione, 7, 4) || '-' || SUBSTR(inizioPrenotazione, 4, 2) || '-' || SUBSTR(inizioPrenotazione, 1, 2) || ' ' || SUBSTR(inizioPrenotazione, 12, 5) < ?\n"
				+ "      )";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dataInizioStr = sdf.format(dataInizio);
			String dataFineStr = sdf.format(dataFine);

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, veicolo.getTarga());
			stmt.setString(2, dataInizioStr);
			stmt.setString(3, dataFineStr);

			ResultSet rs = stmt.executeQuery();

			// Se esiste almeno una tupla, imposta disponibile a false
			if (rs.next()) {
				disponibile = false;
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return disponibile;
	}
}