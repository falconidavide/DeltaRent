package Veicolo;

public class Furgone extends Veicolo{
	
	private int prezzoGiornaliero;
	
	public Furgone(String targa, String marca, String modello, Boolean disponibile, int prezzoG) {
		super(targa, marca, modello, disponibile);
		this.prezzoGiornaliero=prezzoG;
	}

}
