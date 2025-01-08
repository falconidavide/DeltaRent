package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Veicolo.Veicolo;

public class GestoreVeicoli {
	
	Connection conn = DatabaseConnection.getConnection();
	List<Veicolo> veicoli = new ArrayList<>();
	
	public List<Veicolo> getListaAutomobili() {
		try {
			String query = "SELECT targa, marca, modello, disponibile FROM veicoli";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String targa = rs.getString("targa");
					String marca = rs.getString("marca");
					String modello = rs.getString("modello");
					Boolean disponibile = rs.getBoolean("disponibile");
					Veicolo veicolo = new Veicolo(targa, marca, modello, disponibile);
					veicoli.add(veicolo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        // Stampa la lista dei veicoli per verificare
        for (Veicolo veicolo : veicoli) {
            System.out.println("Targa: " + veicolo.getTarga() + 
                               ", Marca: " + veicolo.getMarca() + 
                               ", Modello: " + veicolo.getModello() + 
                               ", Disponibile: " + veicolo.getDisponibile());
        }
		return null;
	}
}