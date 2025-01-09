package GUI;

import javax.swing.*;
import java.awt.*;
import Utente.Utente;
import DB.GestionePrenotazioni;
import java.util.List;

public class HomePage extends JFrame {

    protected static CardLayout cardLayout;
    protected static JPanel mainContentPanel;
    static boolean logged = false; // Stato del login
    public static Utente loggedUser; // Oggetto utente loggato
    private JPanel gestioneAccount; // Pannello gestioneAccount
    protected static JButton btnLogout; // Pulsante di logout

    public HomePage() {
        // Impostazioni della finestra principale
        setTitle("DeltaRent - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 950);
        setLocationRelativeTo(null);

        // Layout principale
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        getContentPane().add(mainPanel);

        // Colonna sinistra
        JPanel leftColumn = new JPanel();
        leftColumn.setBackground(new Color(62, 88, 121));
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        leftColumn.setBorder(BorderFactory.createLineBorder(new Color(216, 195, 182), 3));

        JLabel avatarLabel = new JLabel(new ImageIcon("path/to/avatar/image")); // Sostituisci con il percorso immagine
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnDeltaRent = createUniformButton("DeltaRent");
        JButton btnRentCar = createUniformButton("Noleggia Auto");
        JButton btnViewBookings = createUniformButton("Le Mie Prenotazioni");

        JButton btnProfile = createUniformButton("");
        ImageIcon userIcon = new ImageIcon(
                new ImageIcon("userIcon.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnProfile.setIcon(userIcon);

        // Pulsante di logout
        btnLogout = createUniformButton("Logout");
        btnLogout.setVisible(logged); // Mostra il pulsante solo se l'utente è loggato
        btnLogout.addActionListener(e -> {
            logged = false;
            loggedUser = null;
            removeGestioneAccountPanel(); // Rimuove il pannello gestioneAccount
            updateLogoutButton();
            cardLayout.show(mainContentPanel, "login");
        });

        // Aggiunta dei pulsanti
        leftColumn.add(avatarLabel);
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

    private JButton createUniformButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 40));
        button.setMaximumSize(new Dimension(200, 40));
        button.setMinimumSize(new Dimension(200, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(245, 239, 231));
        button.setForeground(new Color(62, 88, 121));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel();
        homePanel.setBackground(new Color(245, 239, 231));
        homePanel.setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("Benvenuto su DeltaRent!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 36));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setForeground(new Color(32, 52, 85));

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText(
                "Con DeltaRent puoi noleggiare auto in modo facile e veloce. Esplora la nostra flotta e prenota subito il tuo prossimo viaggio!");
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setForeground(new Color(32, 52, 85));
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnExploreFleet = new JButton("Esplora la Flotta");
        btnExploreFleet.setFont(new Font("Arial", Font.BOLD, 20));
        btnExploreFleet.setBackground(new Color(62, 88, 121));
        btnExploreFleet.setForeground(new Color(245, 239, 231));
        btnExploreFleet.setFocusPainted(false);

        homePanel.add(lblWelcome, BorderLayout.NORTH);
        homePanel.add(descriptionArea, BorderLayout.CENTER);
        homePanel.add(btnExploreFleet, BorderLayout.SOUTH);
        return homePanel;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage frame = new HomePage();
            frame.setVisible(true);
        });
    }
}