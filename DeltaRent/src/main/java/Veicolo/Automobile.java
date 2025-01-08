package Veicolo;

public class Automobile extends Veicolo{
	
	private int prezzoOrario;
	
	
	public Automobile(String marca, String modello, Boolean disponibile, int prezzoO) {
		super(marca, modello, disponibile);
		this.prezzoOrario=prezzoO;
	}
}
