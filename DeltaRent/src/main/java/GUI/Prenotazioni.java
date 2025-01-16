package GUI;

import DB.GestionePrenotazioni;
import Prenotazione.Prenotazione;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Prenotazioni extends JPanel {
	public Prenotazioni(GestionePrenotazioni gestionePrenotazioni, String emailUtente) {
		List<Prenotazione> prenotazioniPassate = gestionePrenotazioni.getPrenotazioniPassate(emailUtente);
		initializeUI(prenotazioniPassate);
	}

	private void initializeUI(List<Prenotazione> prenotazioniPassate) {
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);

		// Titolo
		JLabel titolo = new JLabel("Prenotazioni Passate", SwingConstants.CENTER);
		titolo.setForeground(new Color(216, 195, 182));
		titolo.setFont(new Font("Arial", Font.PLAIN, 30));
		add(titolo, BorderLayout.NORTH);

		// Pannello delle prenotazioni
		JPanel prenotazioniPanel = new JPanel();
		prenotazioniPanel.setLayout(new GridLayout(0, 3, 10, 10)); // Layout con 3 righe e 3 colonne
		prenotazioniPanel.setBackground(new Color(32, 52, 85));
		prenotazioniPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		if (prenotazioniPassate.isEmpty()) {
			JLabel noPrenotazioniLabel = new JLabel("Nessuna prenotazione passata.", SwingConstants.CENTER);
			noPrenotazioniLabel.setForeground(Color.WHITE);
			noPrenotazioniLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			prenotazioniPanel.add(noPrenotazioniLabel);
		} else {
			for (Prenotazione prenotazione : prenotazioniPassate) {
				prenotazioniPanel.add(createPrenotazionePanel(prenotazione));
			}
		}

		// Aggiungi pannelli vuoti (segnaposto) per mantenere il layout rettangolare
		int totalPrenotazioni = prenotazioniPassate.size();
		int totalSlots = 9; // Numero totale di slot nel layout 3x3
		for (int i = totalPrenotazioni; i < totalSlots; i++) {
			prenotazioniPanel.add(creaPannelloSegnaposto());
		}

		// ScrollPane per rendere scrollabile il pannello delle prenotazioni
		JScrollPane scrollPane = new JScrollPane(prenotazioniPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().setBackground(new Color(32, 52, 85)); // Imposta lo sfondo dello scrollPane
		add(scrollPane, BorderLayout.CENTER);
	}

	private JPanel createPrenotazionePanel(Prenotazione prenotazione) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(60, 87, 121));
		panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

		// Pannello per centrare l'immagine
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new GridBagLayout());
		imagePanel.setBackground(new Color(60, 87, 121));

		// Aggiungi l'immagine del veicolo
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(resizeImageIcon(prenotazione.getVeicolo().getPathImg(), 200, 150));
		imagePanel.add(imageLabel, new GridBagConstraints());

		panel.add(imagePanel, BorderLayout.NORTH);

		// Aggiungi i dettagli del veicolo
		JPanel detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		detailsPanel.setBackground(new Color(60, 87, 121));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel marcaModelloLabel = new JLabel(prenotazione.getVeicolo().getMarca() + " " + prenotazione.getVeicolo().getModello());
		marcaModelloLabel.setForeground(Color.WHITE);
		marcaModelloLabel.setFont(new Font("Arial", Font.BOLD, 18));
		marcaModelloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		marcaModelloLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		detailsPanel.add(marcaModelloLabel);

		String dataInizio = prenotazione.getInizioPrenotazione();
		String dataFine = prenotazione.getFinePrenotazione();

		JLabel dataInizioLabel = new JLabel("Inizio: " + dataInizio);
		dataInizioLabel.setForeground(Color.LIGHT_GRAY);
		dataInizioLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		dataInizioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		dataInizioLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		detailsPanel.add(dataInizioLabel);

		JLabel dataFineLabel = new JLabel("Fine: " + dataFine);
		dataFineLabel.setForeground(Color.LIGHT_GRAY);
		dataFineLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		dataFineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		dataFineLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		detailsPanel.add(dataFineLabel);

		panel.add(detailsPanel, BorderLayout.CENTER);

		return panel;
	}

	private static JPanel creaPannelloSegnaposto() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 52, 85));
		panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
		return panel;
	}

	private ImageIcon resizeImageIcon(String path, int width, int height) {
		if (path == null || path.isEmpty()) {
			// Immagine predefinita
			path = "img/default_car.jpg";
		} else {
			path = "img/" + path;
		}

		try {
			BufferedImage img = ImageIO.read(new File(path));
			Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImage);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}