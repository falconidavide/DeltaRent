package Veicolo;

import java.sql.Date;

public class Furgone extends Veicolo {

	private int prezzoGiornaliero;


	public Furgone(String targa, String marca, String modello, Boolean disponibile, int prezzoG, String pathImg) {
		super(targa, marca, modello, disponibile, pathImg);
		this.prezzoGiornaliero = prezzoG;
	}

	public int getPrezzoGiornaliero() {
		return prezzoGiornaliero;
	}

	public void setPrezzoGiornaliero(int prezzoGiornaliero) {
		this.prezzoGiornaliero = prezzoGiornaliero;
	}

}
