package utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DatabaseConnection;

public class Utente {

	private String email;
	private String password;
	private String nome;
	private String cognome;
	private String dataDiNascita;
	private String nomeAzienda;
	private String partitaIVA;
	private static boolean isPrivato;
	private static boolean isLoggato = false;
	private static String displayName = "";

	public Utente(String email, String password, String nome, String cognome, String dataDiNascita, String nomeAzienda,
			String partitaIVA, int isPrivato) {
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.nomeAzienda = nomeAzienda;
		this.partitaIVA = partitaIVA;
		if (isPrivato == 1) {
			this.setIsPrivato(true);
			displayName = this.nome;
		} else {
			this.setIsPrivato(false);
			displayName = this.nomeAzienda;
		}
		isLoggato = true;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public static Utente getUserByEmailAndPassword(String email, String password) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Utente WHERE email = ? AND password = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Utente(rs.getString("email"), rs.getString("password"), rs.getString("nome"),
						rs.getString("cognome"), rs.getString("dataDiNascita"), rs.getString("nomeAzienda"),
						rs.getString("partitaIVA"), rs.getInt("isPrivato"));
			}
		} catch (SQLException e) {
			System.err.println("Errore durante la ricerca dell'utente: " + e.getMessage());
		}
		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static boolean getIsPrivato() {
		return isPrivato;
	}

	public void setIsPrivato(Boolean isPrivato) {
		Utente.isPrivato = isPrivato;
	}

	public static boolean isLoggato() {
		return isLoggato;
	}

	public static void setLoggato(boolean l) {
		isLoggato = l;
	}

	public static String getDisplayName() {
		return displayName;
	}
}
