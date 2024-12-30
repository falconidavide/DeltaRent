package GUI;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

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

        JButton btnRentCar = createButton("Noleggia Auto");
        JButton btnViewBookings = createButton("Le Mie Prenotazioni");
        JButton btnProfile = createButton("Profilo");
        JButton btnSupport = createButton("Supporto");

        // Aggiunta dei pulsanti
        leftColumn.add(avatarLabel);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20))); // Spaziatura
        leftColumn.add(btnRentCar);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
        leftColumn.add(btnViewBookings);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
        leftColumn.add(btnProfile);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
        leftColumn.add(btnSupport);

        // Contenuto principale
        JPanel mainContent = new JPanel();
        mainContent.setBackground(new Color(245, 239, 231));
        mainContent.setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("Benvenuto su DeltaRent!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 36));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setForeground(new Color(32, 52, 85));

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText("Con DeltaRent puoi noleggiare auto in modo facile e veloce. Esplora la nostra flotta e prenota subito il tuo prossimo viaggio!");
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

        mainContent.add(lblWelcome, BorderLayout.NORTH);
        mainContent.add(descriptionArea, BorderLayout.CENTER);
        mainContent.add(btnExploreFleet, BorderLayout.SOUTH);

        // Aggiunta dei pannelli al layout principale
        mainPanel.add(leftColumn, BorderLayout.WEST);
        mainPanel.add(mainContent, BorderLayout.CENTER);

        // Eventi pulsanti
        btnRentCar.addActionListener(e -> {
            String brand = "Audi"; // Dati di esempio
            String model = "A4";
            String pickupDay = "01";
            String pickupMonth = "01";
            String pickupYear = "2024";
            String returnDay = "10";
            String returnMonth = "01";
            String returnYear = "2024";
            switchToPanel(new SearchCarList(brand, model, pickupDay, pickupMonth, pickupYear, returnDay, returnMonth, returnYear), this);
        });

        btnViewBookings.addActionListener(e -> switchToPanel(new Prenotazione(), this));
        btnProfile.addActionListener(e -> switchToPanel(new LogIn(), this));
        btnSupport.addActionListener(e -> switchToPanel(new LogIn(), this));
        btnExploreFleet.addActionListener(e -> switchToPanel(new SearchPage(), this));
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

    private void switchToPanel(JFrame targetPanel, JFrame currentFrame) {
        currentFrame.setVisible(false); // Nasconde il pannello attuale
        targetPanel.setVisible(true); // Mostra il nuovo pannello
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
        });
    }
}
