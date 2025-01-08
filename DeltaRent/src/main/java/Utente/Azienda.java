package Utente;

public class Azienda extends Utente{
	
	private String nomeAzienda;
	private String partitaIVA;
	
	
	public Azienda(String email, String password, String nomeAzienda, String partitaIVA) {
		super(email, password);
		this.nomeAzienda=nomeAzienda;
		this.partitaIVA=partitaIVA;
	}
	

}
