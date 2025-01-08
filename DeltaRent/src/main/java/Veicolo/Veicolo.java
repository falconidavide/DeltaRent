package Veicolo;

public class Veicolo {

	private String targa;
	private String marca;
	private String modello;
	private Boolean disponibile;

	public Veicolo(String targa, String marca, String modello, Boolean disponibile) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.disponibile = disponibile;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public Boolean getDisponibile() {
		return disponibile;
	}

	public void setDisponibile(Boolean disponibile) {
		this.disponibile = disponibile;
	}

}
