package veicolo;

public class Veicolo {

	private String targa;
	private String marca;
	private String modello;
	private String pathImg;
	private String alimentazione;
	private String[] pathImgs;
	private String descrizione;

	public Veicolo(String targa, String marca, String modello, String pathImg, String alimentazione, String[] pathImgs) {
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

	public String[] getPathImgs() {
		return pathImgs;
	}

	public void setPathImgs(String[] pathImgs) {
		this.pathImgs = pathImgs;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
