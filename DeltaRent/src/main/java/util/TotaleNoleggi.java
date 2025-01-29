package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DatabaseConnection;
import utente.Utente;

public class TotaleNoleggi {
	
	public static int getTotaleNoleggi(Utente utente) {
		
		int totale=0;

		String query = "SELECT count(*) as totale FROM Prenotazione WHERE emailUtente=?";

		try {

			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, utente.getEmail());

			ResultSet rs = stmt.executeQuery();

			// Se esiste almeno una tupla, imposta disponibile a false
			if (rs.next()) {
				totale=rs.getInt("totale");
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totale;
	}

}
