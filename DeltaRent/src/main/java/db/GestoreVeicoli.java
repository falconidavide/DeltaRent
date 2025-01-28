package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import veicolo.Automobile;
import veicolo.Furgone;

public class GestoreVeicoli {

	private static Connection conn = DatabaseConnection.getConnection();
	public static List<Automobile> automobili = new ArrayList<>();
	public static List<Furgone> furgoni = new ArrayList<>();

		public static List<Automobile> aggiornaListaAutomobili() {
		    List<Automobile> automobili = new ArrayList<>();
		    try {
		        String query = "SELECT targa, marca, modello, prezzoOrario, pathImg, alimentazione, pathImgs FROM Veicolo WHERE isFurgone=0";
		        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
		            while (rs.next()) {
		                String targa = rs.getString("targa");
		                String marca = rs.getString("marca");
		                String modello = rs.getString("modello");
		                int prezzoOrario = rs.getInt("prezzoOrario");
		                String pathImg = rs.getString("pathImg");
		                String alimentazione = rs.getString("alimentazione");
		                String pathImgsString = rs.getString("pathImgs");
		                String[] pathImgs = pathImgsString != null ? pathImgsString.split("\\n") : new String[0];

		                Automobile auto = new Automobile(targa, marca, modello, prezzoOrario, pathImg, alimentazione, pathImgs);
		                automobili.add(auto);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return automobili;
		}


	public static List<Furgone> aggiornaListaFurgoni() {
		try {
			String query = "SELECT targa, marca, modello, prezzoGiornaliero, pathImg, alimentazione, pathImgs FROM Veicolo WHERE isFurgone=1";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String targa = rs.getString("targa");
					String marca = rs.getString("marca");
					String modello = rs.getString("modello");
					int prezzoGiornaliero = rs.getInt("prezzoGiornaliero");
					String pathImg = rs.getString("pathImg");
					String alimentazione = rs.getString("alimentazione");
	                String pathImgsString = rs.getString("pathImgs");
	                String[] pathImgs = pathImgsString != null ? pathImgsString.split("\\n") : new String[0];

					Furgone furgone = new Furgone(targa, marca, modello, prezzoGiornaliero, pathImg, alimentazione, pathImgs);
					furgoni.add(furgone);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return furgoni;
	}

	public static List<String> getMarcheVeicoli() {

		List<String> marche = new ArrayList<>();

		try {
			String query = "SELECT DISTINCT marca FROM Veicolo";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String marca = rs.getString("marca");
					marche.add(marca);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * for (Furgone veicolo : furgoni) { System.out.println("Targa: " +
		 * veicolo.getTarga() + ", Marca: " + veicolo.getMarca() + ", Modello: " +
		 * veicolo.getModello() + ", Disponibile: " + veicolo.getDisponibile() +
		 * ", Prezzo giornaliero: " + veicolo.getPrezzoGiornaliero()); }
		 */
		return marche;
	}
	
	public static List<String> getAlimentazioneVeicoli() {

		List<String> alimentazioni = new ArrayList<>();

		try {
			String query = "SELECT DISTINCT alimentazione FROM Veicolo";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String alimentazione = rs.getString("alimentazione");
					alimentazioni.add(alimentazione);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * for (Furgone veicolo : furgoni) { System.out.println("Targa: " +
		 * veicolo.getTarga() + ", Marca: " + veicolo.getMarca() + ", Modello: " +
		 * veicolo.getModello() + ", Disponibile: " + veicolo.getDisponibile() +
		 * ", Prezzo giornaliero: " + veicolo.getPrezzoGiornaliero()); }
		 */
		return alimentazioni;
	}

	public static List<String> getModelliByMarca(String marca) {

		List<String> modelli = new ArrayList<>();

		try {
			String query = "SELECT DISTINCT modello FROM Veicolo WHERE marca='" + marca + "'";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String modello = rs.getString("modello");
					modelli.add(modello);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * for (Furgone veicolo : furgoni) { System.out.println("Targa: " +
		 * veicolo.getTarga() + ", Marca: " + veicolo.getMarca() + ", Modello: " +
		 * veicolo.getModello() + ", Disponibile: " + veicolo.getDisponibile() +
		 * ", Prezzo giornaliero: " + veicolo.getPrezzoGiornaliero()); }
		 */
		return modelli;
	}
}