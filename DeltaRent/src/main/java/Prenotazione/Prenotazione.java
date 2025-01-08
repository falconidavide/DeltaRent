package Prenotazione;

import java.util.Date;

import Veicolo.Veicolo;
import Utente.Utente;

public class Prenotazione {
	
	private Date inizioPrenotazione;
	private Date finePrenotazione;
	
	private Utente u;
	private Veicolo v;
	
	public Prenotazione(Date inizio, Date fine, Utente u, Veicolo v){
		this.inizioPrenotazione=inizio;
		this.finePrenotazione=fine;
		this.u=u;
		this.v=v;
	}
}
