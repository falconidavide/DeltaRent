package Utente;

import java.sql.Date;

public class Privato extends Utente{
	
	
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	
	
	public Privato(int id, String email, String password, String nome, String cognome, Date nascita) {
		super(id, email, password);
		this.nome=nome;
		this.cognome=cognome;
		this.dataDiNascita=nascita;
	}

}
