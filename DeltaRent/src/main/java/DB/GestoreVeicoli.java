package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Veicolo.Automobile;
import Veicolo.Furgone;

public class GestoreVeicoli {

	private static Connection conn = DatabaseConnection.getConnection();
	public static List<Automobile> automobili = new ArrayList<>();
	public static List<Furgone> furgoni = new ArrayList<>();

	public static List<Automobile> aggiornaListaAutomobili() {
		try {
			String query = "SELECT targa, marca, modello, disponibile, prezzoOrario, dataConsegna FROM Veicolo WHERE isFurgone=0";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String targa = rs.getString("targa");
					String marca = rs.getString("marca");
					String modello = rs.getString("modello");
					boolean disponibile = rs.getBoolean("disponibile");
					int prezzoOrario = rs.getInt("prezzoOrario");
					Date dataConsegna = rs.getDate("dataconsegna");
					Automobile auto = new Automobile(targa, marca, modello, disponibile, prezzoOrario, dataConsegna);
					automobili.add(auto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		for (Automobile veicolo : automobili) {
			System.out.println("Targa: " + veicolo.getTarga() + ", Marca: " + veicolo.getMarca() + ", Modello: "
					+ veicolo.getModello() + ", Disponibile: " + veicolo.getDisponibile() + ", Prezzo orario: "
					+ veicolo.getPrezzoOrario());
		}
		*/
		return automobili;
	}

	public static List<Furgone> aggiornaListaFurgoni() {
		try {
			String query = "SELECT targa, marca, modello, disponibile, prezzoGiornaliero, dataConsegna FROM Veicolo WHERE isFurgone=1";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String targa = rs.getString("targa");
					String marca = rs.getString("marca");
					String modello = rs.getString("modello");
					boolean disponibile = rs.getBoolean("disponibile");
					int prezzoGiornaliero = rs.getInt("prezzoGiornaliero");
					Date dataConsegna = rs.getDate("dataconsegna");
					Furgone furgone = new Furgone(targa, marca, modello, disponibile, prezzoGiornaliero, dataConsegna);
					furgoni.add(furgone);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		for (Furgone veicolo : furgoni) {
			System.out.println("Targa: " + veicolo.getTarga() + ", Marca: " + veicolo.getMarca() + ", Modello: "
					+ veicolo.getModello() + ", Disponibile: " + veicolo.getDisponibile() + ", Prezzo giornaliero: "
					+ veicolo.getPrezzoGiornaliero());
		}
		*/
		return furgoni;
	}
}