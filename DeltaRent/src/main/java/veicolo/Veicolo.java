package veicolo;

public class Veicolo {

	private String targa;
	private String marca;
	private String modello;
	private Boolean disponibile;
	private String pathImg;
	private String alimentazione;

	public Veicolo(String targa, String marca, String modello, Boolean disponibile, String pathImg, String alimentazione) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.disponibile = disponibile;
		this.pathImg = pathImg;
		this.alimentazione = alimentazione;
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

	public String getPathImg() {
		return pathImg;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}

	public String getAlimentazione() {
		return alimentazione;
	}

	public void setAlimentazione(String alimentazione) {
		this.alimentazione = alimentazione;
	}

}
