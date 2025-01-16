package Veicolo;

public class Automobile extends Veicolo {

	private int prezzoOrario;

	public Automobile(String targa, String marca, String modello, Boolean disponibile, int prezzoO, String pathImg, String alimentazione) {
		super(targa, marca, modello, disponibile, pathImg, alimentazione);
		this.prezzoOrario = prezzoO;
	}

	public int getPrezzoOrario() {
		return prezzoOrario;
	}

	public void setPrezzoOrario(int prezzoOrario) {
		this.prezzoOrario = prezzoOrario;
	}
}
