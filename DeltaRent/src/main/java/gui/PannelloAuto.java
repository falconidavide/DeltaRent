package gui;

import javax.swing.JPanel;

import db.GestoreVeicoli;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;

public class PannelloAuto extends JPanel {

	private static final long serialVersionUID = 1L;
	String modello;
	Boolean disponibile;
	String potenza;
	String alimentazione;
	String prezzo;

	/**
	 * Create the panel.
	 */
	public PannelloAuto() {

		GestoreVeicoli.aggiornaListaAutomobili();

		setBackground(new Color(220, 221, 222));
		setSize(500, 300);
		setLayout(null);

		JLabel Modello = new JLabel("Modello");// dati dall'arraylist
		Modello.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Modello.setBounds(6, 6, 488, 58);
		add(Modello);

		JLabel Anno = new JLabel("Anno");// dati dall'arraylist
		Anno.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		Anno.setBounds(297, 102, 197, 33);
		add(Anno);

		JLabel Potenza = new JLabel("Potenza");// dati dall'arraylist
		Potenza.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		Potenza.setBounds(297, 147, 197, 33);
		add(Potenza);

		JLabel Alimentazione = new JLabel("Alimentazione");// dati dall'arraylist
		Alimentazione.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		Alimentazione.setBounds(297, 192, 197, 33);
		add(Alimentazione);

		JLabel PrezzoAuto = new JLabel("Prezzo");// dati dall'arraylist
		PrezzoAuto.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		PrezzoAuto.setBounds(297, 237, 197, 33);
		add(PrezzoAuto);

		JLabel immagineAuto = new JLabel("");
		immagineAuto.setBounds(6, 76, 279, 218);

		// Carica l'immagine
		immagineAuto.setIcon(new ImageIcon("audi_a7.jpg"));// dati dall'arraylist
		add(immagineAuto);
		ImageIcon originalIcon = new ImageIcon("audi_a7.jpg");
		Image scaledImage = originalIcon.getImage().getScaledInstance(279, 218, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		immagineAuto.setIcon(scaledIcon);

	}
}
