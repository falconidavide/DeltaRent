package Utente;

import java.sql.Date;

public class Privato extends Utente{
	
	
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	
	
	public Privato( String email, String password, String nome, String cognome, Date nascita) {
		super(email, password);
		this.nome=nome;
		this.cognome=cognome;
		this.dataDiNascita=nascita;
	}

}
