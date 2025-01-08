package Veicolo;

import java.sql.Date;

public class Automobile extends Veicolo {

	private int prezzoOrario;

	public Automobile(String targa, String marca, String modello, Boolean disponibile, int prezzoO,  String pathImg) {
		super(targa, marca, modello, disponibile, pathImg);
		this.prezzoOrario = prezzoO;
	}

	public int getPrezzoOrario() {
		return prezzoOrario;
	}

	public void setPrezzoOrario(int prezzoOrario) {
		this.prezzoOrario = prezzoOrario;
	}
}
