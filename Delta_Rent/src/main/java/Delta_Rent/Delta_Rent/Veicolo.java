package Delta_Rent.Delta_Rent;

public class Veicolo {

	public int anno;
	public String marca;
	public String modello;
	public boolean disponibile;
	public String tipo;
	
	public Veicolo (int Anno,String Marca,String Modello,boolean Disponibile,String Tipo) 
	{
		this.anno=Anno;
		this.disponibile=Disponibile;
		this.marca=Marca;
		this.modello=Modello;
		this.tipo=Tipo;
	}
	
	public int getAnno() {
		return anno;
	}
	public boolean getDisponibilità() {
		return disponibile;
	}
	public String getMarca() {
		return marca;
	}
	public String getModello() {
		return modello;
	}
	public String getTipo() {
		return tipo;
	}
}

	