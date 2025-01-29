package veicolo;

public class Furgone extends Veicolo {

	private int prezzoGiornaliero;

	public Furgone(String targa, String marca, String modello, int prezzoG, String pathImg, String alimentazione,
			String[] pathImgs) {
		super(targa, marca, modello, pathImg, alimentazione, pathImgs);
		this.prezzoGiornaliero = prezzoG;
	}

	public int getPrezzoGiornaliero() {
		return prezzoGiornaliero;
	}

}
