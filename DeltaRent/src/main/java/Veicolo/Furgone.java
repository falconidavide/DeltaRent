package Veicolo;

import java.sql.Date;

public class Furgone extends Veicolo {

	private int prezzoGiornaliero;

	public Furgone(String targa, String marca, String modello, Boolean disponibile, int prezzoG) {
		super(targa, marca, modello, disponibile, null);
		this.prezzoGiornaliero = prezzoG;
	}
	public Furgone(String targa, String marca, String modello, Boolean disponibile, int prezzoG, Date dataConsegna) {
		super(targa, marca, modello, disponibile, dataConsegna);
		this.prezzoGiornaliero = prezzoG;
	}

	public int getPrezzoGiornaliero() {
		return prezzoGiornaliero;
	}

	public void setPrezzoGiornaliero(int prezzoGiornaliero) {
		this.prezzoGiornaliero = prezzoGiornaliero;
	}

}
