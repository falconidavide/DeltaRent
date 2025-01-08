package GUI;

import javax.swing.*;
import java.awt.*;
import Utente.Utente;

public class HomePage extends JFrame {

    protected static CardLayout cardLayout;
    protected static JPanel mainContentPanel;
    static boolean logged = false; // Stato del login
    public static Utente loggedUser; // Oggetto utente loggato
    private JPanel gestioneAccount; // Pannello gestioneAccount

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

        JButton btnDeltaRent = createButton("Delta Rent");
        JButton btnRentCar = createButton("Noleggia Auto");
        JButton btnViewBookings = createButton("Le Mie Prenotazioni");

        JButton btnProfile = new JButton();
        ImageIcon userIcon = new ImageIcon(
                new ImageIcon("userIcon.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnProfile.setIcon(userIcon);
        btnProfile.setMaximumSize(new Dimension(200, 40));
        btnProfile.setHorizontalTextPosition(SwingConstants.CENTER);
        btnProfile.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnProfile.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnProfile.setVerticalTextPosition(SwingConstants.CENTER);
        btnProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnProfile.setBackground(new Color(245, 239, 231));
        btnProfile.setForeground(new Color(62, 88, 121));
        btnProfile.setFont(new Font("Arial", Font.BOLD, 16));
        btnProfile.setFocusPainted(false);
        btnProfile.setIconTextGap(10);

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

        // Contenuto principale con CardLayout
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);

        JPanel homePanel = createHomePanel();
        JPanel rentCarPanel = new SearchPage(); // Replace with actual panel
        JPanel profilePanel = new LogIn(); // Replace with actual panel

        mainContentPanel.add(homePanel, "home");
        mainContentPanel.add(rentCarPanel, "rentCar");
        mainContentPanel.add(profilePanel, "login");

        // Aggiunta dei pannelli al layout principale
        mainPanel.add(leftColumn, BorderLayout.WEST);
        mainPanel.add(mainContentPanel, BorderLayout.CENTER);

        // Eventi pulsanti
        btnDeltaRent.addActionListener(e -> cardLayout.show(mainContentPanel, "home"));
        btnRentCar.addActionListener(e -> cardLayout.show(mainContentPanel, "rentCar"));
        btnViewBookings.addActionListener(e -> cardLayout.show(mainContentPanel, "bookings"));
        btnProfile.addActionListener(e -> {
            System.out.println("btnProfile clicked"); // Debug
            if (logged) {
                System.out.println("User is logged in"); // Debug
                System.out.println(loggedUser);
                System.out.println(gestioneAccount);
                // Se l'utente è loggato, mostra il pannello di gestione account
                ensureGestioneAccountPanel();
                
            } else {
                System.out.println("User is not logged in"); // Debug
                // Altrimenti, mostra il pannello di login
                cardLayout.show(mainContentPanel, "login");
            }
        });
    }

    private void ensureGestioneAccountPanel() {
        if (loggedUser != null && gestioneAccount == null) {
            System.out.println("Creating GestioneAccount panel"); // Debug
            gestioneAccount = new GestioneAccount();
            mainContentPanel.add(gestioneAccount, "account");
            cardLayout.show(mainContentPanel, "account");
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(200, 40));
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
}