package Veicolo;

public class Furgone extends Veicolo{
	
	private int prezzoGiornaliero;
	
	public Furgone(String marca, String modello, Boolean disponibile, int prezzoG) {
		super(marca, modello, disponibile);
		this.prezzoGiornaliero=prezzoG;
	}

}
