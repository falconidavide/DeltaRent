package veicolo;

public class Veicolo {

	private String targa;
	private String marca;
	private String modello;
	private String pathImg;
	private String alimentazione;
	private String[] pathImgs;

	public Veicolo(String targa, String marca, String modello, String pathImg, String alimentazione,
			String[] pathImgs) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.pathImg = pathImg;
		this.alimentazione = alimentazione;
		this.pathImgs = pathImgs;
	}

	public String getTarga() {
		return targa;
	}

	public String getMarca() {
		return marca;
	}

	public String getModello() {
		return modello;
	}

	public String getPathImg() {
		return pathImg;
	}

	public String getAlimentazione() {
		return alimentazione;
	}

	public String[] getPathImgs() {
		return pathImgs;
	}
}
