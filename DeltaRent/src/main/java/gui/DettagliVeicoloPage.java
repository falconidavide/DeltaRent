package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.DateSelectable;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserListener;

import db.GestionePrenotazioni;
import my.components.Button;
import my.components.ButtonOutLine;
import prenotazione.Prenotazione;
import utente.Utente;
import util.Disponibilita;
import veicolo.Automobile;
import veicolo.Furgone;
import veicolo.Veicolo;

public class DettagliVeicoloPage extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblMarcaModello;
	private JLabel lblImmagineAlimentazione;
	private JLabel lblImmagineAlimentazione2;
	private JLabel lblAlimentazione;
	private Button btnDisponibile; // Cambiato da JLabel a Button
	private JLabel lblPrezzo;
	private ButtonOutLine btnNoleggia;
	private DateChooser dateChooser;
	private JComboBox<String> comboOraInizio;
	private JComboBox<String> comboOraFine;
	private boolean isSettingDate = false;
	private Veicolo veicolo;
	private Utente utente;
	private boolean disponibile;
	private double prezzoTotale;
	public Button btnPrezzoTotale;

	public DettagliVeicoloPage(Automobile auto, Utente utente) {
		this.veicolo = auto;
		this.utente = utente;
		setupPanel();
		populatePanel(auto, true);
	}

	public DettagliVeicoloPage(Furgone furgone, Utente utente) {
		this.veicolo = furgone;
		this.utente = utente;
		setupPanel();
		populatePanel(furgone, false);
	}

	private void setupPanel() {
		setLayout(new BorderLayout());
		setBackground(new Color(28, 51, 84));
	}

	private void populatePanel(Object veicolo, boolean isAutomobile) {
		double prezzo = isAutomobile ? ((Automobile) veicolo).getPrezzoOrario()
				: ((Furgone) veicolo).getPrezzoGiornaliero();

		setLayout(new BorderLayout()); // Imposta il layout a BorderLayout

		JPanel infoAndDescriptionPanel = new JPanel();
		infoAndDescriptionPanel.setLayout(new BoxLayout(infoAndDescriptionPanel, BoxLayout.Y_AXIS));
		infoAndDescriptionPanel.setBackground(new Color(60, 87, 121));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setBackground(new Color(60, 87, 121));
		infoAndDescriptionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		infoAndDescriptionPanel.add(buttonPanel);

		Button btnTornaIndietro = new Button();
		btnTornaIndietro.setFont(new Font("sansserif", Font.BOLD, 18));
		btnTornaIndietro.setPreferredSize(new Dimension(250, 45));
		btnTornaIndietro.setMaximumSize(new Dimension(250, 45));
		btnTornaIndietro.setMinimumSize(new Dimension(250, 45));
		btnTornaIndietro.setForeground(new Color(245, 245, 245));
		btnTornaIndietro.addActionListener(e -> HomePage.cardLayout.show(HomePage.mainContentPanel, "rentCar"));
		btnTornaIndietro.setBackground(new Color(45, 64, 98));
		btnTornaIndietro.setText("Torna alla Ricerca");

		buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		buttonPanel.add(btnTornaIndietro);
		buttonPanel.add(Box.createHorizontalGlue());

		JPanel infoPanel = createInfoPanel(veicolo, isAutomobile);
		infoAndDescriptionPanel.add(infoPanel); // Usa BorderLayout.CENTER per infoPanel

		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setBackground(new Color(60, 87, 121));
		descriptionPanel.add(Box.createRigidArea(new Dimension(0, 150)));
		infoAndDescriptionPanel.add(descriptionPanel); // Usa BorderLayout.CENTER per infoPanel

		JPanel dateAndBottomPanel = new JPanel();
		dateAndBottomPanel.setBackground(new Color(60, 87, 121));
		dateAndBottomPanel.setLayout(new BoxLayout(dateAndBottomPanel, BoxLayout.Y_AXIS));

		JLabel titledate = new JLabel("Scegli le tue date");
		titledate.setFont(new Font("sansserif", 1, 45));
		titledate.setForeground(Color.WHITE);
		titledate.setAlignmentX(CENTER_ALIGNMENT);

		JPanel dateTitlePanel = new JPanel();
		dateTitlePanel.setLayout(new BoxLayout(dateTitlePanel, BoxLayout.Y_AXIS));
		dateTitlePanel.setBackground(new Color(60, 87, 121));
		dateTitlePanel.add(Box.createRigidArea(new Dimension(0, 40)));
		dateTitlePanel.add(titledate);
		dateAndBottomPanel.add(dateTitlePanel); // Usa BorderLayout.CENTER per infoPanel

		JPanel datePanel = createDatePanel(prezzo, isAutomobile);
		dateAndBottomPanel.add(datePanel); // Aggiungi il datePanel al dateAndBottomPanel

		JPanel bottomPanel = createBottomPanel(prezzo, isAutomobile);
		dateAndBottomPanel.add(bottomPanel); // Aggiungi il bottomPanel al dateAndBottomPanel

		add(dateAndBottomPanel, BorderLayout.EAST); // Usa BorderLayout.SOUTH per dateAndBottomPanel
		add(infoAndDescriptionPanel, BorderLayout.CENTER); // Usa BorderLayout.SOUTH per dateAndBottomPanel

		// Aggiungi il listener al pulsante "Noleggia"
		btnNoleggia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				creaPrenotazione();
			}
		});
	}

	private JPanel createInfoPanel(Object veicolo, boolean isAutomobile) {
		String[] imgPaths = isAutomobile ? ((Automobile) veicolo).getPathImgs() : ((Furgone) veicolo).getPathImgs();
		String marca = isAutomobile ? ((Automobile) veicolo).getMarca() : ((Furgone) veicolo).getMarca();
		String modello = isAutomobile ? ((Automobile) veicolo).getModello() : ((Furgone) veicolo).getModello();
		String alimentazione = isAutomobile ? ((Automobile) veicolo).getAlimentazione()
				: ((Furgone) veicolo).getAlimentazione();
		double prezzo = isAutomobile ? ((Automobile) veicolo).getPrezzoOrario()
				: ((Furgone) veicolo).getPrezzoGiornaliero();

		JPanel infoPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		infoPanel.setBorder(new LineBorder(new Color(216, 195, 182), 0, true));
		infoPanel.setSize(700, 500);
		// CardLayout per scorrere le immagini
		JPanel imagePanel = new JPanel(new CardLayout());
		imagePanel.setSize(600, 400);
		imagePanel.setMaximumSize(new Dimension(600, 400));
		imagePanel.setMinimumSize(new Dimension(600, 400));
		imagePanel.setPreferredSize(new Dimension(600, 400));
		imagePanel.setBackground(new Color(60, 87, 121));

		JProgressBar progressBar;

		if (imgPaths.length != 0) {

			progressBar = new JProgressBar(0, imgPaths.length - 1);
		} else {
			progressBar = new JProgressBar(0, 1);
		}
		progressBar.setValue(0); // Valore iniziale
		progressBar.setBackground(new Color(200, 200, 200));

		JLabel imgLabel = new JLabel();

		for (String imgPath : imgPaths) {
			imgLabel = new JLabel();
			imgLabel.setIcon(SearchPage.resizeImageIcon(imgPath, 500, 333));
			imagePanel.add(imgLabel);
		}

		if (imgPaths.length == 0) {
			imgLabel.setIcon(SearchPage.resizeImageIcon(null, 500, 333));
			imagePanel.add(imgLabel);
		}

		// Pulsanti per navigare tra le immagini
		Button btnPrev = new Button();
		btnPrev.setText("<");
		btnPrev.setPreferredSize(new Dimension(75, 35));
		btnPrev.setMaximumSize(new Dimension(75, 35));
		btnPrev.setMinimumSize(new Dimension(75, 35));
		btnPrev.setForeground(new Color(245, 245, 245));
		btnPrev.setBackground(new Color(45, 64, 98));
		btnPrev.setFont(new Font("sansserif", 1, 17));
		Button btnNext = new Button();
		btnNext.setText(">");
		btnNext.setPreferredSize(new Dimension(75, 35));
		btnNext.setMaximumSize(new Dimension(75, 35));
		btnNext.setMinimumSize(new Dimension(75, 35));
		btnNext.setForeground(new Color(245, 245, 245));
		btnNext.setBackground(new Color(45, 64, 98));
		btnNext.setFont(new Font("sansserif", 1, 17));

		btnPrev.addActionListener(e -> {
			CardLayout cl = (CardLayout) imagePanel.getLayout();
			cl.previous(imagePanel);
			int currentIndex;
			if (imgPaths.length != 0)
				currentIndex = (progressBar.getValue() + 1) % imgPaths.length;
			else
				currentIndex = 0;
			progressBar.setValue(currentIndex);
		});

		btnNext.addActionListener(e -> {
			CardLayout cl = (CardLayout) imagePanel.getLayout();
			cl.next(imagePanel);
			int currentIndex;
			if (imgPaths.length != 0)
				currentIndex = (progressBar.getValue() + 1) % imgPaths.length;
			else
				currentIndex = 0;
			progressBar.setValue(currentIndex);
		});

		// Aggiungi il pannello immagine e i pulsanti
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 5;
		infoPanel.add(imagePanel, gbc);

		// Aggiungi la barra progressiva
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel navigationPanel = new JPanel();
		navigationPanel.add(btnPrev);
		navigationPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		navigationPanel.add(progressBar);
		navigationPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		navigationPanel.add(btnNext);
		navigationPanel.setBackground(new Color(60, 87, 121));
		gbc.gridy = 5;
		infoPanel.add(navigationPanel, gbc);

		// Altri dettagli del veicolo
		Font font = new Font("sansserif", 1, 30);

		lblMarcaModello = new JLabel(marca + " " + modello);
		lblMarcaModello.setForeground(Color.WHITE);
		lblMarcaModello.setFont(font);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		infoPanel.add(lblMarcaModello, gbc);

		lblAlimentazione = new JLabel(alimentazione);
		lblAlimentazione.setForeground(Color.WHITE);
		lblAlimentazione.setFont(font);
		gbc.gridy = 1;
		infoPanel.add(lblAlimentazione, gbc);

		lblImmagineAlimentazione = new JLabel();
		lblImmagineAlimentazione2 = new JLabel();
		switch (alimentazione) {
		case "Benzina":
			lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("benzina.png", 40, 40));
			lblImmagineAlimentazione2.setIcon(null);
			break;
		case "Diesel":
			lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("diesel.png", 40, 40));
			lblImmagineAlimentazione2.setIcon(null);
			break;
		case "Elettrica":
			lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("elettrica.png", 40, 40));
			lblImmagineAlimentazione2.setIcon(null);
			break;
		case "Ibrida":
			lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("elettrica.png", 40, 40));
			lblImmagineAlimentazione2.setIcon(SearchPage.resizeImageIcon("benzina.png", 40, 40));
			break;
		case "GPL":
			lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("gpl.png", 40, 40));
			lblImmagineAlimentazione2.setIcon(null);
			break;
		}
		JPanel alimentazionePanel = new JPanel();
		alimentazionePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		alimentazionePanel.setBackground(new Color(60, 87, 121));
		alimentazionePanel.add(lblImmagineAlimentazione);
		if (lblImmagineAlimentazione2.getIcon() != null) {
			alimentazionePanel.add(lblImmagineAlimentazione2);
		}
		gbc.gridy = 2;
		infoPanel.add(alimentazionePanel, gbc);

		lblPrezzo = new JLabel(isAutomobile ? "Prezzo €/h: €" + prezzo : "Prezzo €/day: €" + prezzo);
		lblPrezzo.setForeground(Color.WHITE);
		lblPrezzo.setFont(font);
		gbc.gridy = 3;
		infoPanel.add(lblPrezzo, gbc);

		infoPanel.setBackground(new Color(60, 87, 121));
		return infoPanel;
	}

	private JPanel createDatePanel(double prezzo, boolean isAutomobile) {
		JPanel datePanel = new JPanel(new GridBagLayout());
		datePanel.setBackground(new Color(60, 87, 121));
		datePanel.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(216, 195, 182), 0, true),
						BorderFactory.createEmptyBorder(20, 20, 20, 20) // Padding aggiunto
				));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		dateChooser = new DateChooser();
		dateChooser.setThemeColor(getBackground());
		dateChooser.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		dateChooser.setPreferredSize(new Dimension(450, 263)); // Imposta la dimensione preferita
		dateChooser.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
		dateChooser.setDateSelectable(new DateSelectable() {
			@Override
			public boolean isDateSelectable(Date date) {
				Date today = new Date();
				if (date.before(today) && !isSameDay(date, today)) {
					return false;
				}
				return true;
			}
		});

		comboOraInizio = createHourComboBox();
		comboOraFine = createHourComboBox();
		comboOraFine.setSelectedIndex(0);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		datePanel.add(dateChooser, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;

		JLabel orainizio = new JLabel("Ora Inizio:");
		orainizio.setFont(new Font("sansserif", 1, 18));
		orainizio.setForeground(Color.WHITE);
		datePanel.add(Box.createRigidArea(new Dimension(0, 75)), gbc);
		datePanel.add(orainizio, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		datePanel.add(comboOraInizio, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;

		JLabel orafine = new JLabel("Ora Fine:");
		orafine.setFont(new Font("sansserif", 1, 18));
		orafine.setForeground(Color.WHITE);
		datePanel.add(orafine, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		datePanel.add(comboOraFine, gbc);

		// Listener per aggiornare il prezzo totale in diretta
		dateChooser.addActionDateChooserListener(new DateChooserListener() {
			@Override
			public void dateChanged(Date date, DateChooserAction action) {
				if (isSettingDate)
					return;
				isSettingDate = true;

				// Calcola il prezzo totale
				calcolaPrezzo(prezzo, isAutomobile);

				isSettingDate = false;
			}

			@Override
			public void dateBetweenChanged(DateBetween dateBetween, DateChooserAction action) {

				calcolaPrezzo(prezzo, isAutomobile);
				if (isSettingDate)
					return;
				isSettingDate = true;

				isSettingDate = false;
			}
		});

		comboOraInizio.addActionListener(e -> calcolaPrezzo(prezzo, isAutomobile));
		comboOraFine.addActionListener(e -> calcolaPrezzo(prezzo, isAutomobile));

		return datePanel;
	}

	private JPanel createBottomPanel(double prezzo, boolean isAutomobile) {
		JPanel bottomPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 15, 10); // Aggiungi un margine superiore di 10 pixel
		gbc.fill = GridBagConstraints.NONE; // Non riempire orizzontalmente
		bottomPanel.setBackground(new Color(60, 87, 121));
		bottomPanel.setBorder(new LineBorder(new Color(216, 195, 182), 0, true));

		// Inizializza btnDisponibile come campo di istanza
		btnDisponibile = new Button();
		btnDisponibile.setPreferredSize(new Dimension(350, 45));
		btnDisponibile.setMaximumSize(new Dimension(350, 45));
		btnDisponibile.setMinimumSize(new Dimension(350, 45));
		btnDisponibile.setForeground(new Color(0, 0, 0)); // Testo nero
		btnDisponibile.setFont(new Font("sansserif", 1, 25));
		btnDisponibile.setHorizontalAlignment(SwingConstants.CENTER);
		btnDisponibile.setEnabled(false);
		btnDisponibile.setFocusPainted(false);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER; // Centro il bottone
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		bottomPanel.add(btnDisponibile, gbc);

		// Aggiungi lblPrezzoTotale centrato
		btnPrezzoTotale = new Button();
		btnPrezzoTotale.setPreferredSize(new Dimension(350, 45));
		btnPrezzoTotale.setMaximumSize(new Dimension(350, 45));
		btnPrezzoTotale.setMinimumSize(new Dimension(350, 45));
		btnPrezzoTotale.setForeground(new Color(245, 245, 245));
		btnPrezzoTotale.setBackground(new Color(45, 64, 98));
		btnPrezzoTotale.setText("--");
		btnPrezzoTotale.setFont(new Font("sansserif", 1, 25));
		btnPrezzoTotale.setHorizontalAlignment(SwingConstants.CENTER);
		btnPrezzoTotale.setEnabled(false);
		btnPrezzoTotale.setFocusPainted(false);

		gbc.insets = new Insets(0, 10, 15, 10); // Resetta gli insets per gli altri componenti
		gbc.gridy = 1;
		bottomPanel.add(btnPrezzoTotale, gbc);

		// Aggiungi btnNoleggia centrato
		btnNoleggia = new ButtonOutLine();
		btnNoleggia.setText("Noleggia");
		btnNoleggia.setPreferredSize(new Dimension(350, 45));
		btnNoleggia.setMaximumSize(new Dimension(350, 45));
		btnNoleggia.setMinimumSize(new Dimension(350, 45));
		btnNoleggia.setForeground(new Color(245, 245, 245));
		btnNoleggia.setFont(new Font("sansserif", 1, 25));
		btnNoleggia.setFocusPainted(false);
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE; // Imposta il fill a NONE per il pulsante
		gbc.anchor = GridBagConstraints.CENTER;
		bottomPanel.add(btnNoleggia, gbc);

		return bottomPanel;
	}

	private JComboBox<String> createHourComboBox() {
		JComboBox<String> comboBox = new JComboBox<>();
		for (int h = 9; h <= 22; h++) {
			comboBox.addItem(String.format("%02d:00", h));
			comboBox.addItem(String.format("%02d:30", h));
		}
		comboBox.setSelectedIndex(0);
		return comboBox;
	}

	private void calcolaPrezzo(double prezzo, boolean isAutomobile) {
		try {
			DateBetween dateBetween = dateChooser.getSelectedDateBetween();

			String oraInizio = (String) comboOraInizio.getSelectedItem();
			String oraFine = (String) comboOraFine.getSelectedItem();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			Date inizio = sdf
					.parse(new SimpleDateFormat("dd-MM-yyyy").format(dateBetween.getFromDate()) + " " + oraInizio);
			Date fine = sdf.parse(new SimpleDateFormat("dd-MM-yyyy").format(dateBetween.getToDate()) + " " + oraFine);

			disponibile = Disponibilita.verificaDisponibilita(veicolo, inizio, fine);

			if (disponibile) {
				btnDisponibile.setText("Disponibile");
				btnDisponibile.setBackground(new Color(0, 150, 0)); // Verde
			} else {
				btnDisponibile.setText("Non Disponibile");
				btnDisponibile.setBackground(new Color(150, 0, 0)); // Rosso
			}

			if (fine.before(inizio) || fine.compareTo(inizio) == 0) {
				btnPrezzoTotale.setText("Date non valide");
				btnNoleggia.setEnabled(false);
				return;
			} else {
				if (disponibile)
					btnNoleggia.setEnabled(true);
			}

			long durata = fine.getTime() - inizio.getTime();
			prezzoTotale = isAutomobile ? (durata / (1000 * 60 * 60.0)) * prezzo
					: (durata / (1000 * 60 * 60 * 24.0)) * prezzo;

			btnPrezzoTotale.setText(String.format("€%.2f", prezzoTotale));
			return;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Errore nel calcolo del prezzo.");
		}
	}

	private void creaPrenotazione() {
		if (!Utente.isLoggato()) {
			JOptionPane.showMessageDialog(this, "Devi essere loggato per poter noleggiare un veicolo.");
			return;
		}

		try {
			DateBetween dateBetween = dateChooser.getSelectedDateBetween();
			String dataInizio = null;
			String dataFine = null;

			if (dateBetween != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				dataInizio = sdf.format(dateBetween.getFromDate());
				dataFine = sdf.format(dateBetween.getToDate());
			} else {
				System.out.println("Nessuna data selezionata nel date picker.");
			}

			String oraInizio = (String) comboOraInizio.getSelectedItem();
			String oraFine = (String) comboOraFine.getSelectedItem();

			String inizio = dataInizio + " " + oraInizio;
			String fine = dataFine + " " + oraFine;

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			sdf.setLenient(false); // Per rendere più rigoroso il parsing

			Date dataInizioD = sdf.parse(inizio);
			Date dataFineD = sdf.parse(fine);

			if (dataFineD.before(dataInizioD) || dataFineD.compareTo(dataInizioD) == 0) {
				JOptionPane.showMessageDialog(this, "Errore: Data di fine non valida");
				return;
			}
			Prenotazione prenotazione = new Prenotazione(inizio, fine, utente, veicolo, prezzoTotale);
			GestionePrenotazioni.aggiungiPrenotazione(prenotazione);

			JOptionPane.showMessageDialog(this, "Prenotazione effettuata con successo!");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage() + "Errore nella creazione della prenotazione.");
		}
	}

	private boolean isSameDay(Date date1, Date date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date1).equals(sdf.format(date2));
	}

}