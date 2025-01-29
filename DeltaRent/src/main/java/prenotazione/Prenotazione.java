package prenotazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import db.DatabaseConnection;
import utente.Utente;
import veicolo.Veicolo;

public class Prenotazione {

	private int ID;
	private String inizioPrenotazione;
	private String finePrenotazione;
	private Utente utente;
	private Veicolo veicolo;
	private String dataPrenotazione;
	private double prezzo;

	public Prenotazione(int ID, String inizio, String fine, Utente utente, Veicolo veicolo, String dataPrenotazione, double prezzo) {
		this.inizioPrenotazione = inizio;
		this.finePrenotazione = fine;
		this.utente = utente;
		this.veicolo = veicolo;
		this.ID = ID;
		this.dataPrenotazione = dataPrenotazione;
		this.prezzo=prezzo;
	}

	public Prenotazione(String inizio, String fine, Utente utente, Veicolo veicolo, double prezzo) {
		this.inizioPrenotazione = inizio;
		this.finePrenotazione = fine;
		this.utente = utente;
		this.veicolo = veicolo;
		this.prezzo=prezzo;
	}

	// Metodo per ottenere la data di inizio prenotazione
	public String getInizioPrenotazione() {
		return inizioPrenotazione;
	}

	// Metodo per ottenere la data di fine prenotazione
	public String getFinePrenotazione() {
		return finePrenotazione;
	}

	// Metodo per ottenere l'utente che ha effettuato la prenotazione
	public Utente getUtente() {
		return utente;
	}

	// Metodo per ottenere il veicolo prenotato
	public Veicolo getVeicolo() {
		return veicolo;
	}

	// Metodo per visualizzare i dettagli della prenotazione
	public String dettagliPrenotazione() {
		return "Prenotazione:\n" + "Utente: " + utente.getNome() + "\n" + "Veicolo: " + veicolo.getModello() + "\n" + "Inizio: " + inizioPrenotazione + "\n" + "Fine: " + finePrenotazione;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(String dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public double getPrezzo() {
		// TODO Auto-generated method stub
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
}