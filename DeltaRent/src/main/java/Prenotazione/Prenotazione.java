package Prenotazione;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Veicolo.Veicolo;
import Utente.Utente;

public class Prenotazione {
    
	private int ID;
    private String inizioPrenotazione;
    private String finePrenotazione;
    private Utente utente;
    private Veicolo veicolo;
    private String dataPrenotazione;
    
    public Prenotazione(int ID, String inizio, String fine, Utente utente, Veicolo veicolo, String dataPrenotazione) {
        this.inizioPrenotazione = inizio;
        this.finePrenotazione = fine;
        this.utente = utente;
        this.veicolo = veicolo;
        this.ID=ID;
        this.dataPrenotazione=dataPrenotazione;
    }

	public Prenotazione(String inizio, String fine, Utente utente, Veicolo veicolo) {
		this.inizioPrenotazione=inizio;
		this.finePrenotazione=fine;
		this.utente=utente;
		this.veicolo=veicolo;
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
        return "Prenotazione:\n" + 
               "Utente: " + utente.getNome() + "\n" +
               "Veicolo: " + veicolo.getModello() + "\n" +
               "Inizio: " + inizioPrenotazione + "\n" + 
               "Fine: " + finePrenotazione;
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
}