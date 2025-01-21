package gui;

import utente.Utente;
import veicolo.Automobile;
import veicolo.Furgone;

import javax.swing.*;
import javax.swing.border.LineBorder;

import db.GestoreVeicoli;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchPage extends JPanel {
	private static final long serialVersionUID = 1L;
	static JComboBox<String> modelComboBox;
	static JComboBox<String> brandComboBox;
	static JComboBox<String> supplyComboBox;
	private static JPanel vehicleDisplayPanel;
	static List<Automobile> automobili = null;
	static List<Furgone> furgoni = null;
	private static JComboBox<String> sortComboBox;

	public SearchPage() {
		// Inizializza i componenti
		modelComboBox = new JComboBox<>();
		brandComboBox = new JComboBox<>();
		supplyComboBox = new JComboBox<>();

		// Inizializza il combo box per l'ordinamento
		sortComboBox = new JComboBox<>(new String[] { "Ordina per", "Prezzo Crescente", "Prezzo Decrescente", "Alfabetico Crescente", "Alfabetico Decrescente" });

		// Layout principale
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);

		// Colonna destra
		JPanel rightColumn = new JPanel();
		rightColumn.setBackground(new Color(32, 52, 85));
		rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

		// Pannello di ricerca
		JPanel searchPanel = new GradientPanel(new Color(28, 51, 84), new Color(48, 69, 100));
		searchPanel.setLayout(new BorderLayout());
		searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel lblSubtitle = new JLabel("Scegli la tua prossima auto da noleggiare", SwingConstants.CENTER);
		lblSubtitle.setForeground(new Color(250, 250, 250));
		lblSubtitle.setFont(new Font("sansserif", 1,  50));
		lblSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Aggiungi margine inferiore per distanziamento
		searchPanel.add(lblSubtitle, BorderLayout.NORTH);

		JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		filterPanel.setBackground(new Color(62, 88, 121));
		filterPanel.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 1));

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblMarca.setForeground(Color.WHITE);
		filterPanel.add(lblMarca);
		filterPanel.add(brandComboBox);

		filterPanel.add(Box.createHorizontalStrut(15));

		JLabel lblModello = new JLabel("Modello");
		lblModello.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblModello.setForeground(Color.WHITE);
		filterPanel.add(lblModello);
		filterPanel.add(modelComboBox);

		filterPanel.add(Box.createHorizontalStrut(15));
		
		JLabel lblAlimentazione = new JLabel("Alimentazione");
		lblAlimentazione.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblAlimentazione.setForeground(Color.WHITE);
		filterPanel.add(lblAlimentazione);
		filterPanel.add(supplyComboBox);

		filterPanel.add(Box.createHorizontalStrut(15));

		// Aggiungi il combo box per l'ordinamento
		filterPanel.add(sortComboBox);

		searchPanel.add(filterPanel, BorderLayout.CENTER);

		rightColumn.add(searchPanel);

		JScrollPane container = new JScrollPane();
		container.getVerticalScrollBar().setUnitIncrement(16);
		JPanel panel_1 = new JPanel();
		container.setViewportView(panel_1);
		panel_1.setBackground(new Color(28, 51, 84));
		panel_1.setLayout(new GridLayout(0, 3, 10, 10)); // Imposta un layout con 3 colonne

		// Pannello di visualizzazione dei veicoli
		vehicleDisplayPanel = panel_1;

		// Aggiunta dei pannelli al layout principale
		add(rightColumn, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);

		aggiornaComboBoxAlimentazione();
		aggiornaComboBoxMarche();
		aggiornaComboBoxModelli(null);
		mostraVeicoli();

		// Aggiunge un listener alla combo box delle marche per aggiornare la combo box
		// dei modelli
		brandComboBox.addActionListener(e -> {
			String selectedBrand = (String) brandComboBox.getSelectedItem();
			aggiornaComboBoxModelli(selectedBrand);
			mostraVeicoli();
		});
		
		supplyComboBox.addActionListener(e -> {
			mostraVeicoli();
		});

		modelComboBox.addActionListener(e -> {
			mostraVeicoli();
		});

		// Aggiungi un listener per l'ordinamento
		sortComboBox.addActionListener(e -> mostraVeicoli());
	}

	private void aggiornaComboBoxMarche() {
		List<String> marche = GestoreVeicoli.getMarcheVeicoli();
		brandComboBox.removeAllItems();
		brandComboBox.addItem("Tutte le Marche");
		for (String marca : marche) {
			brandComboBox.addItem(marca);
		}
	}

	private void aggiornaComboBoxModelli(String alimetazine) {
		modelComboBox.removeAllItems();
		if (alimetazine != null && !alimetazine.equals("Tutte le Marche")) {
			List<String> modelli = GestoreVeicoli.getModelliByMarca(alimetazine);
			modelComboBox.addItem("Tutti i Modelli");
			for (String modello : modelli) {
				modelComboBox.addItem(modello);
			}
		} else {
			modelComboBox.addItem("Tutti i Modelli");
		}
	}
	private void aggiornaComboBoxAlimentazione() {
		List<String> alimentazioni = GestoreVeicoli.getAlimentazioneVeicoli();
		supplyComboBox.removeAllItems();
		supplyComboBox.addItem("Tutte le Alimentazioni");
		for (String alimentazione : alimentazioni) {
			supplyComboBox.addItem(alimentazione);
		}
	}

	public static void mostraVeicoli() {
		// Rimuovi tutti i veicoli attualmente visualizzati
		vehicleDisplayPanel.removeAll();

		// Recupera le liste aggiornate dei veicoli
		if (automobili == null || furgoni == null) {
			automobili = GestoreVeicoli.aggiornaListaAutomobili();
			furgoni = GestoreVeicoli.aggiornaListaFurgoni();
		}

		String marcaSelezionata = (String) brandComboBox.getSelectedItem();
		String modelloSelezionato = (String) modelComboBox.getSelectedItem();
		String alimentazioneSelezionata = (String) supplyComboBox.getSelectedItem();
		
		// Ordina le liste dei veicoli in base all'opzione selezionata
		String sortingOption = (String) sortComboBox.getSelectedItem();
		if (sortingOption != null) {
			switch (sortingOption) {
			case "Prezzo Crescente":
				automobili.sort(Comparator.comparingInt(Automobile::getPrezzoOrario));
				furgoni.sort(Comparator.comparingInt(Furgone::getPrezzoGiornaliero));
				break;
			case "Prezzo Decrescente":
				automobili.sort(Comparator.comparingInt(Automobile::getPrezzoOrario).reversed());
				furgoni.sort(Comparator.comparingInt(Furgone::getPrezzoGiornaliero).reversed());
				break;
			case "Alfabetico Crescente":
				automobili.sort(Comparator.comparing(Automobile::getMarca).thenComparing(Automobile::getModello));
				furgoni.sort(Comparator.comparing(Furgone::getMarca).thenComparing(Furgone::getModello));
				break;
			case "Alfabetico Decrescente":
				automobili.sort(
						Comparator.comparing(Automobile::getMarca).thenComparing(Automobile::getModello).reversed());
				furgoni.sort(Comparator.comparing(Furgone::getMarca).thenComparing(Furgone::getModello).reversed());
				break;
			}
		}

		if (Utente.isLoggato()) {
			if (Utente.getIsPrivato()) {
				mostraAuto(marcaSelezionata, modelloSelezionato, alimentazioneSelezionata);
			} else {
				mostraFurgoni(marcaSelezionata, modelloSelezionato, alimentazioneSelezionata);
			}
		} else {
			mostraAuto(marcaSelezionata, modelloSelezionato, alimentazioneSelezionata);
			mostraFurgoni(marcaSelezionata, modelloSelezionato, alimentazioneSelezionata);
		}

		// Aggiungi pannelli vuoti (segnaposto) per mantenere il layout quadrato
		int totalVehicles = vehicleDisplayPanel.getComponentCount();
		int totalSlots = 9; // Numero totale di slot nel layout 3x3
		for (int i = totalVehicles; i < totalSlots; i++) {
			vehicleDisplayPanel.add(creaPannelloSegnaposto());
		}

		// Revalidate e repaint per aggiornare la visualizzazione
		vehicleDisplayPanel.revalidate();
		vehicleDisplayPanel.repaint();
	}

	private static JPanel creaPannelloSegnaposto() { // crea pannello vuoto
		JPanel panel = new JPanel();
		panel.setBackground(new Color(28, 51, 84));
		panel.setVisible(false);
		return panel;
	}

	private static JPanel creaPannelloVeicolo(String marca, String modello, int prezzoOrario, int prezzoGiornaliero, String pathImg, String alimentazione) {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	    panel.setBackground(new Color(60, 87, 121));
	    panel.setBorder(new LineBorder(new Color(216, 195, 182), 0, true));

	    JLabel lblImg = new JLabel();
	    lblImg.setIcon(resizeImageIcon(pathImg, 200, 150)); // Ridimensiona l'immagine mantenendo le proporzioni
	    lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);

	    Font font = new Font("Arial", Font.BOLD, 18); // Font più grande e grassetto
	    int bottomMargin = 10; // Margine inferiore

	    JLabel lblMarcaModello = new JLabel(marca + " " + modello);
	    lblMarcaModello.setForeground(Color.WHITE);
	    lblMarcaModello.setAlignmentX(Component.CENTER_ALIGNMENT);
	    lblMarcaModello.setFont(font);
	    lblMarcaModello.setBorder(BorderFactory.createEmptyBorder(0, 0, bottomMargin, 0)); // Margine inferiore


	    JLabel lblPrezzo = new JLabel();
	    if (prezzoOrario != -1) {
	        lblPrezzo.setText("Prezzo \u20ac/h: €" + prezzoOrario);
	    } else {
	        lblPrezzo.setText("Prezzo \u20ac/day: €" + prezzoGiornaliero);
	    }
	    lblPrezzo.setForeground(Color.WHITE);
	    lblPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
	    lblPrezzo.setFont(font);
	    lblPrezzo.setBorder(BorderFactory.createEmptyBorder(0, 0, bottomMargin, 0)); // Margine inferiore

	    // Creazione delle etichette Alimentazione
	    JLabel Alimentazione = new JLabel();
	    JLabel Alimentazione2 = new JLabel();
	    
	    switch (alimentazione) {
	        case "Benzina":
	            Alimentazione.setIcon(resizeImageIcon("benzina.png", 35, 35));
	            Alimentazione2.setIcon(null);
	            break;
	        case "Diesel":
	            Alimentazione.setIcon(resizeImageIcon("diesel.png", 35, 35));
	            Alimentazione2.setIcon(null);
	            break;
	        case "Elettrica":
	            Alimentazione.setIcon(resizeImageIcon("elettrica.png", 35, 35));
	            Alimentazione2.setIcon(null);
	            break;
	        case "Ibrida":
	            Alimentazione.setIcon(resizeImageIcon("elettrica.png", 35, 35));
	            Alimentazione2.setIcon(resizeImageIcon("benzina.png", 35, 35));
	            break;
	        case "gpl":
	            Alimentazione.setIcon(resizeImageIcon("gpl.png", 35, 35));
	            Alimentazione2.setIcon(null);
	            break;
	    }


	    JPanel alimentazionePanel = new JPanel();
	    alimentazionePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0)); // Distanza di 5 pixel
	    alimentazionePanel.setBackground(panel.getBackground());
	    alimentazionePanel.add(Alimentazione);
	    if (Alimentazione2.getIcon() != null) {
	        alimentazionePanel.add(Alimentazione2);
	    }
	    alimentazionePanel.add(Box.createHorizontalStrut(5));

	    panel.add(Box.createRigidArea(new Dimension(0, 10)));
	    panel.add(alimentazionePanel);
	    panel.add(lblImg);
	    panel.add(lblMarcaModello);
	    panel.add(lblPrezzo);

	    return panel;
	}

	private static JPanel creaPannelloVeicolo(Automobile auto) {
		JPanel panel = creaPannelloVeicolo(auto.getMarca(), auto.getModello(), auto.getPrezzoOrario(), -1, auto.getPathImg(),auto.getAlimentazione());

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage.mainContentPanel.add(new DettagliVeicoloPage(auto, HomePage.loggedUser), "dettagliVeicolo");
				HomePage.cardLayout.show(HomePage.mainContentPanel, "dettagliVeicolo");
			}
		});

		return panel;
	}

	private static JPanel creaPannelloVeicolo(Furgone furgone) {
		JPanel panel = creaPannelloVeicolo(furgone.getMarca(), furgone.getModello(), -1, furgone.getPrezzoGiornaliero(), furgone.getPathImg(),furgone.getAlimentazione());

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage.mainContentPanel.add(new DettagliVeicoloPage(furgone, HomePage.loggedUser), "dettagliVeicolo");
				HomePage.cardLayout.show(HomePage.mainContentPanel, "dettagliVeicolo");
			}
		});

		return panel;
	}

	   public static ImageIcon resizeImageIcon(String path, int width, int height) {
	        if (path == null || path.isEmpty()) {
	            path = "img/default_car.jpg"; // Immagine predefinita
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

	private static void mostraAuto(String marcaSelezionata, String modelloSelezionato, String AlimentazioneSelezionata) {
		for (Automobile auto : automobili) {
			if ((marcaSelezionata == null || marcaSelezionata.equals("Tutte le Marche") || auto.getMarca().equals(marcaSelezionata))
					&& (modelloSelezionato == null || modelloSelezionato.equals("Tutti i Modelli")
							|| auto.getModello().equals(modelloSelezionato))
					&& (auto.getAlimentazione().equals(AlimentazioneSelezionata) 
							|| AlimentazioneSelezionata == null 
							|| AlimentazioneSelezionata.equals("Tutte le Alimentazioni"))) {
				vehicleDisplayPanel.add(creaPannelloVeicolo(auto));
			}
		}
	}

	private static void mostraFurgoni(String marcaSelezionata, String modelloSelezionato, String AlimentazioneSelezionata) {
		for (Furgone furgone : furgoni) {
			if ((marcaSelezionata == null 
							|| marcaSelezionata.equals("Tutte le Marche")
							|| furgone.getMarca().equals(marcaSelezionata))
					&& (modelloSelezionato == null 
							|| modelloSelezionato.equals("Tutti i Modelli")
							|| furgone.getModello().equals(modelloSelezionato))
					&& (furgone.getAlimentazione().equals(AlimentazioneSelezionata) 
							|| AlimentazioneSelezionata == null 
							|| AlimentazioneSelezionata.equals("Tutte le Alimentazioni"))) {
				vehicleDisplayPanel.add(creaPannelloVeicolo(furgone));
			}
		}
	}
}
