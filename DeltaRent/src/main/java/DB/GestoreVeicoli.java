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
			String query = "SELECT targa, marca, modello, disponibile, prezzoOrario, pathImg FROM Veicolo WHERE isFurgone=0";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String targa = rs.getString("targa");
					String marca = rs.getString("marca");
					String modello = rs.getString("modello");
					boolean disponibile = rs.getBoolean("disponibile");
					int prezzoOrario = rs.getInt("prezzoOrario");
					String pathImg = rs.getString("pathImg");
					Automobile auto = new Automobile(targa, marca, modello, disponibile, prezzoOrario, pathImg);
					automobili.add(auto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * for (Automobile veicolo : automobili) { System.out.println("Targa: " +
		 * veicolo.getTarga() + ", Marca: " + veicolo.getMarca() + ", Modello: " +
		 * veicolo.getModello() + ", Disponibile: " + veicolo.getDisponibile() +
		 * ", Prezzo orario: " + veicolo.getPrezzoOrario()); }
		 */
		return automobili;
	}

	public static List<Furgone> aggiornaListaFurgoni() {
		try {
			String query = "SELECT targa, marca, modello, disponibile, prezzoGiornaliero, pathImg FROM Veicolo WHERE isFurgone=1";
			try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					String targa = rs.getString("targa");
					String marca = rs.getString("marca");
					String modello = rs.getString("modello");
					boolean disponibile = rs.getBoolean("disponibile");
					int prezzoGiornaliero = rs.getInt("prezzoGiornaliero");
					String pathImg = rs.getString("pathImg");
					Furgone furgone = new Furgone(targa, marca, modello, disponibile, prezzoGiornaliero, pathImg);
					furgoni.add(furgone);
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

	public static List<String> getModelliByMarca(String marca) {
		
		List<String> modelli = new ArrayList<>();

		try {
			String query = "SELECT DISTINCT modello FROM Veicolo WHERE marca='"+marca+"'";
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