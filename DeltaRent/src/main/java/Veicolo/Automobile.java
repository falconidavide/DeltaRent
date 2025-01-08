package Veicolo;

public class Automobile extends Veicolo{
	
	private int prezzoOrario;
	
	
	public Automobile(String targa, String marca, String modello, Boolean disponibile, int prezzoO) {
		super(targa, marca, modello, disponibile);
		this.prezzoOrario=prezzoO;
	}
}
