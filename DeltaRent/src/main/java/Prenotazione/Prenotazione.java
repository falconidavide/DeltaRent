package Prenotazione;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import Veicolo.Veicolo;
import Utente.Utente;

public class Prenotazione {
    
    private Date inizioPrenotazione;
    private Date finePrenotazione;
    private Utente utente;
    private Veicolo veicolo;
    
    public Prenotazione(Date inizio, Date fine, Utente utente, Veicolo veicolo) {
        this.inizioPrenotazione = inizio;
        this.finePrenotazione = fine;
        this.utente = utente;
        this.veicolo = veicolo;
    }
    
    // Metodo per ottenere la data di inizio prenotazione
    public Date getInizioPrenotazione() {
        return inizioPrenotazione;
    }
    
    // Metodo per ottenere la data di fine prenotazione
    public Date getFinePrenotazione() {
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
    
    // Metodo per calcolare la durata della prenotazione in giorni
    public long getDurataPrenotazione() {
        long diffInMillies = Math.abs(finePrenotazione.getTime() - inizioPrenotazione.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
    
    // Metodo per verificare se il veicolo è disponibile per un determinato periodo
    public boolean isVeicoloDisponibile(Date inizio, Date fine) {
        // Verifica se le date di prenotazione si sovrappongono
        return !(inizio.before(finePrenotazione) && fine.after(inizioPrenotazione));
    }
    
    // Metodo per visualizzare i dettagli della prenotazione
    public String dettagliPrenotazione() {
        return "Prenotazione:\n" + 
               "Utente: " + utente.getNome() + "\n" +
               "Veicolo: " + veicolo.getModello() + "\n" +
               "Inizio: " + inizioPrenotazione + "\n" + 
               "Fine: " + finePrenotazione + "\n" + 
               "Durata: " + getDurataPrenotazione() + " giorni";
    }
}