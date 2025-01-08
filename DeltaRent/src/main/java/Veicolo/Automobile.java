package Veicolo;

import java.sql.Date;

public class Automobile extends Veicolo {

	private int prezzoOrario;

	public Automobile(String targa, String marca, String modello, Boolean disponibile, int prezzoO) {
		super(targa, marca, modello, disponibile, null);
		this.prezzoOrario = prezzoO;
	}
	public Automobile(String targa, String marca, String modello, Boolean disponibile, int prezzoO, Date dataConsegna) {
		super(targa, marca, modello, disponibile, dataConsegna);
		this.prezzoOrario = prezzoO;
	}

	public int getPrezzoOrario() {
		return prezzoOrario;
	}

	public void setPrezzoOrario(int prezzoOrario) {
		this.prezzoOrario = prezzoOrario;
	}
}
