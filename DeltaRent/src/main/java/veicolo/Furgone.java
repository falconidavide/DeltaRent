package veicolo;

public class Furgone extends Veicolo {

	private int prezzoGiornaliero;

	public Furgone(String targa, String marca, String modello, Boolean disponibile, int prezzoG, String pathImg, String alimentazione, String[] pathImgs, String descrizione) {
		super(targa, marca, modello, disponibile, pathImg, alimentazione, pathImgs, descrizione);
		this.prezzoGiornaliero = prezzoG;
	}

	public int getPrezzoGiornaliero() {
		return prezzoGiornaliero;
	}

	public void setPrezzoGiornaliero(int prezzoGiornaliero) {
		this.prezzoGiornaliero = prezzoGiornaliero;
	}

}
