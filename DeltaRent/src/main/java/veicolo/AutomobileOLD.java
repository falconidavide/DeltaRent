package veicolo;

public class AutomobileOLD extends Veicolo {

	private int prezzoOrario;

	public AutomobileOLD(String targa, String marca, String modello, int prezzoO, String pathImg, String alimentazione, String[] pathImgs, String descrizione) {
		super(targa, marca, modello, pathImg, alimentazione, pathImgs, descrizione);
		this.prezzoOrario = prezzoO;
	}

	public int getPrezzoOrario() {
		return prezzoOrario;
	}

	public void setPrezzoOrario(int prezzoOrario) {
		this.prezzoOrario = prezzoOrario;
	}
}
