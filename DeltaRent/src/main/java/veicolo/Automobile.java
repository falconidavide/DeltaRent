package veicolo;

public class Automobile extends Veicolo {

	private int prezzoOrario;

	public Automobile(String targa, String marca, String modello, Boolean disponibile, int prezzoO, String pathImg, String alimentazione, String[] pathImgs, String descrizione) {
		super(targa, marca, modello, disponibile, pathImg, alimentazione, pathImgs, descrizione);
		this.prezzoOrario = prezzoO;
	}

	public int getPrezzoOrario() {
		return prezzoOrario;
	}

	public void setPrezzoOrario(int prezzoOrario) {
		this.prezzoOrario = prezzoOrario;
	}
}
