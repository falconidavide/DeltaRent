package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import db.GestionePrenotazioni;
import my.components.Button;
import my.components.ButtonOutLine;
import utente.Utente;

public class HomePage extends JFrame {
	private static final long serialVersionUID = 1L;
	public static CardLayout cardLayout;
	public static JPanel mainContentPanel;
	public static boolean logged = false; // Stato del login
	public static Utente loggedUser; // Oggetto utente loggato
	private JPanel gestioneAccount; // Pannello gestioneAccount
	public static JButton btnLogout; // Pulsante di logout
	private static JLabel lblWelcome = new JLabel("Benvenuto su DeltaRent!");

	public HomePage() {
		// Impostazioni della finestra principale
		setTitle("DeltaRent - Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1800, 1000));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		ImageIcon appIcon = new ImageIcon("img/appIcon.png"); // Sostituisci con il percorso corretto
		setIconImage(appIcon.getImage());

		// Layout principale
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.DARK_GRAY);
		getContentPane().add(mainPanel);

		// Colonna sinistra con sfondo a gradiente
		JPanel leftColumn = new GradientPanel(new Color(28, 51, 84), new Color(141, 148, 176));
		leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
		leftColumn.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(216, 195, 182), 0, true),
						BorderFactory.createEmptyBorder(0, 20, 0, 20) // Margine di 20 pixel a destra
				));

		JButton btnDeltaRent = createTransparentButton("DeltaRent");
		JButton btnRentCar = createTransparentButton("Noleggia");
		JButton btnViewBookings = createTransparentButton("Prenotazioni");

		JButton btnProfile = createTransparentButton("");
		ImageIcon userIcon = new ImageIcon(
				new ImageIcon("img/profilowhite.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnProfile.setIcon(userIcon);

		// Pulsante di logout
		btnLogout = createTransparentButton("Logout");
		btnLogout.setVisible(logged); // Mostra il pulsante solo se l'utente è loggato
		btnLogout.addActionListener(e -> {
			logged = false;
			loggedUser = null;
			removeGestioneAccountPanel(); // Rimuove il pannello gestioneAccount
			updateLogoutButton();
			aggiornaMessaggioBenvenuto();
			cardLayout.show(mainContentPanel, "login");
		});

		// Aggiunta dei pulsanti
		leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
		leftColumn.add(btnDeltaRent);
		leftColumn.add(Box.createRigidArea(new Dimension(0, 20))); // Spaziatura
		leftColumn.add(btnRentCar);
		leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
		leftColumn.add(btnViewBookings);
		leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
		leftColumn.add(btnProfile);
		leftColumn.add(Box.createVerticalGlue()); // Aggiunge spazio flessibile per spingere il logout in fondo
		leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
		leftColumn.add(btnLogout);
		leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));

		// Contenuto principale con CardLayout
		cardLayout = new CardLayout();
		mainContentPanel = new JPanel(cardLayout);

		JPanel homePanel = createHomePanel();
		JPanel rentCarPanel = new SearchPage(); // Pannello di noleggio auto
		JPanel profilePanel = new LogIn(); // Pannello di login

		mainContentPanel.add(homePanel, "home");
		mainContentPanel.add(rentCarPanel, "rentCar");
		mainContentPanel.add(profilePanel, "login");

		// Aggiunta del pannello delle prenotazioni
		JPanel bookingsPanel = new JPanel();
		mainContentPanel.add(bookingsPanel, "bookings");

		// Aggiunta dei pannelli al layout principale
		mainPanel.add(leftColumn, BorderLayout.WEST);
		mainPanel.add(mainContentPanel, BorderLayout.CENTER);

		// Eventi pulsanti
		btnDeltaRent.addActionListener(e -> cardLayout.show(mainContentPanel, "home"));
		btnRentCar.addActionListener(e -> cardLayout.show(mainContentPanel, "rentCar"));
		btnViewBookings.addActionListener(e -> {
			if (logged) {
				// Se l'utente è loggato, mostra il pannello delle prenotazioni
				mostraPrenotazioni(bookingsPanel);
				cardLayout.show(mainContentPanel, "bookings");
			} else {
				// Altrimenti, mostra il pannello di login
				cardLayout.show(mainContentPanel, "login");
			}
		});
		btnProfile.addActionListener(e -> {
			if (logged) {
				// Se l'utente è loggato, mostra il pannello di gestione account
				ensureGestioneAccountPanel();
				cardLayout.show(mainContentPanel, "account");
			} else {
				// Altrimenti, mostra il pannello di login
				cardLayout.show(mainContentPanel, "login");
			}
		});
	}

	private void removeGestioneAccountPanel() {
		if (gestioneAccount != null) {
			mainContentPanel.remove(gestioneAccount);
			gestioneAccount = null;
		}
	}

	private void ensureGestioneAccountPanel() {
		if (loggedUser != null && gestioneAccount == null) {
			gestioneAccount = new GestioneAccount();
			mainContentPanel.add(gestioneAccount, "account");
		}
	}

	private void updateLogoutButton() {
		btnLogout.setVisible(logged);
		Utente.setLoggato(false);
		SearchPage.mostraVeicoli();
	}

	private JButton createTransparentButton(String text) {
		JButton button = new ButtonOutLine();
		button.setPreferredSize(new Dimension(250, 45));
		button.setMaximumSize(new Dimension(250, 45));
		button.setMinimumSize(new Dimension(250, 45));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setText(text);
		button.setForeground(new Color(245, 245, 245));
		button.setFont(new Font("sansserif", 1, 17));
		button.setFocusPainted(false); // Rimuove il bordo di focus
		button.setContentAreaFilled(false); // Rende il pulsante trasparente
		button.setOpaque(false); // Rende il pulsante non opaco
		return button;
	}

	private JPanel createHomePanel() {
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(220, 240, 255)); // Leggermente più blu
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

		// Banner con immagine e messaggio di benvenuto
		JLabel bannerLabel = new JLabel(new ImageIcon("../Documenti/Logo/Logo DeltaRent small.png"));
		bannerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		lblWelcome = new JLabel("Benvenuto su DeltaRent!");
		lblWelcome.setFont(new Font("sansserif", 1, 55));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(new Color(32, 52, 85));
		lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

		homePanel.add(Box.createRigidArea(new Dimension(0, 40)));
		homePanel.add(bannerLabel);
		homePanel.add(Box.createRigidArea(new Dimension(0, 40)));
		homePanel.add(lblWelcome);
		homePanel.add(Box.createRigidArea(new Dimension(0, 50)));

		// Sezione con icone e descrizioni
		JPanel featuresPanel = new JPanel();
		featuresPanel.setBackground(new Color(220, 240, 255)); // Leggermente più blu
		featuresPanel.setLayout(new GridLayout(1, 3, 20, 20)); // Modificato per rendere le sezioni di dimensioni uguali
		addFeature(featuresPanel, "Noleggio Auto e Furgoni", "rent_car_icon.png",
				"Noleggia veicoli in modo rapido e semplice.");
		addFeature(featuresPanel, "Le Mie Prenotazioni", "bookings_icon.png", "Visualizza tutte le tue prenotazioni.");
		addFeature(featuresPanel, "Gestione Account", "account.png", "Gestisci il tuo account e le tue informazioni.");

		homePanel.add(featuresPanel);
		homePanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Sezione con pulsante per esplorare la flotta
		JButton btnExploreFleet = new Button();
		btnExploreFleet.setPreferredSize(new Dimension(500, 100)); // Ingrandito
		btnExploreFleet.setMaximumSize(new Dimension(500, 100)); // Ingrandito
		btnExploreFleet.setMinimumSize(new Dimension(500, 100)); // Ingrandito
		btnExploreFleet.setBackground(new Color(28, 51, 84));
		btnExploreFleet.setForeground(new Color(250, 250, 250));
		btnExploreFleet.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExploreFleet.addActionListener(e -> cardLayout.show(mainContentPanel, "rentCar"));
		btnExploreFleet.setText("Esplora la nostra Flotta");
		btnExploreFleet.setFont(new Font("sansserif", 1, 25));

		homePanel.add(btnExploreFleet);
		homePanel.add(Box.createRigidArea(new Dimension(0, 175)));

		return homePanel;
	}

	private void addFeature(JPanel panel, String title, String imagePath, String description) {
		JPanel featurePanel = new JPanel();
		featurePanel.setBackground(new Color(220, 240, 255)); // Leggermente più blu
		featurePanel.setLayout(new BoxLayout(featurePanel, BoxLayout.Y_AXIS));
		featurePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Ridimensiona l'immagine
		ImageIcon icon = new ImageIcon("img/" + imagePath);
		Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel lblImage = new JLabel(new ImageIcon(img));
		lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitle.setForeground(new Color(32, 52, 85));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel txtDescription = new JLabel(description);
		txtDescription.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDescription.setForeground(new Color(32, 52, 85));
		txtDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

		featurePanel.add(lblImage);
		featurePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		featurePanel.add(lblTitle);
		featurePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		featurePanel.add(txtDescription);

		panel.add(featurePanel);
	}

	private void mostraPrenotazioni(JPanel bookingsPanel) {
		bookingsPanel.removeAll(); // Pulisce il pannello delle prenotazioni

		// Ottiene le prenotazioni passate dell'utente loggato
		GestionePrenotazioni gestionePrenotazioni = new GestionePrenotazioni();

		// Impostazioni del layout
		Prenotazioni prenotazioniPanel = new Prenotazioni(gestionePrenotazioni, loggedUser.getEmail());
		bookingsPanel.setLayout(new BorderLayout());
		bookingsPanel.add(prenotazioniPanel, BorderLayout.CENTER);

		bookingsPanel.revalidate();
		bookingsPanel.repaint();
	}

	public static void aggiornaMessaggioBenvenuto() {
		if (logged) {
			lblWelcome.setText("Benvenuto " + Utente.getDisplayName() + " su DeltaRent!");
		} else {
			lblWelcome.setText("Benvenuto su DeltaRent!");
		}
	}
}
