package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Veicolo.Automobile;
import Veicolo.Furgone;
import Veicolo.Veicolo;

public class GestoreVeicoli {

	Connection conn = DatabaseConnection.getConnection();
	public List<Veicolo> automobili = new ArrayList<>();
	public List<Veicolo> furgoni = new ArrayList<>();

	public List<Veicolo> getListaAutomobili() {
		try {
			String query = "SELECT targa, marca, modello, disponibile, prezzoOrario FROM veicoli WHERE isFurgone=0";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String targa = rs.getString("targa");
					String marca = rs.getString("marca");
					String modello = rs.getString("modello");
					boolean disponibile = rs.getBoolean("disponibile");
					int prezzoOrario = rs.getInt("prezzoOrario");
					Automobile auto = new Automobile(targa, marca, modello, disponibile, prezzoOrario);
					automobili.add(auto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Stampa la lista dei veicoli per verificare
		for (Veicolo veicolo : automobili) {
			System.out.println("Targa: " + veicolo.getTarga() + ", Marca: " + veicolo.getMarca() + ", Modello: "
					+ veicolo.getModello() + ", Disponibile: " + veicolo.getDisponibile());
		}
		return automobili;
	}
	
	
	public List<Veicolo> getListaFurgoni() {
		try {
			String query = "SELECT targa, marca, modello, disponibile, prezzoGiornaliero FROM veicoli WHERE isFurgone=1";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String targa = rs.getString("targa");
					String marca = rs.getString("marca");
					String modello = rs.getString("modello");
					boolean disponibile = rs.getBoolean("disponibile");
					int prezzoGiornaliero = rs.getInt("prezzoGiornaliero");
					Furgone furgone = new Furgone(targa, marca, modello, disponibile, prezzoGiornaliero);
					furgoni.add(furgone);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Stampa la lista dei veicoli per verificare
		for (Veicolo veicolo : furgoni) {
			System.out.println("Targa: " + veicolo.getTarga() + ", Marca: " + veicolo.getMarca() + ", Modello: "
					+ veicolo.getModello() + ", Disponibile: " + veicolo.getDisponibile());
		}
		return furgoni;
	}
}