package Delta_Rent.Delta_Rent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.HashMap;

public class SearchPage extends JFrame {
    private JTextField textField_2;
    private JTextField textField_3;
    private JComboBox<String> modelComboBox;
    private HashMap<String, String[]> carData;

    public SearchPage() {
        // Impostazioni della finestra principale
        setTitle("DeltaRent - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 950);
        setLocationRelativeTo(null);

        // Layout principale
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        getContentPane().add(mainPanel);

        // Colonna sinistra (Invariata)
        JPanel leftColumn = new JPanel();
        leftColumn.setBackground(new Color(62, 88, 121));
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        leftColumn.setBorder(BorderFactory.createLineBorder(new Color(216, 195, 182), 3));

        JLabel avatarLabel = new JLabel(new ImageIcon("path/to/avatar/image")); // Sostituisci con il percorso immagine
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnFavorites = createButton("Preferiti");
        JButton btnSearch = createButton("Ricerca");
        JButton btnProfile = createButton("Profilo");
        btnProfile.addActionListener(e -> {
            new LogIn().setVisible(true);
            dispose(); // Chiude la finestra corrente
        });

        leftColumn.add(avatarLabel);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20))); // Spaziatura
        leftColumn.add(btnFavorites);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20))); // Spaziatura
        leftColumn.add(btnSearch);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20))); // Spaziatura
        leftColumn.add(btnProfile);

        // Colonna destra
        JPanel rightColumn = new JPanel();
        rightColumn.setBackground(new Color(32, 52, 85));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        // Pannello di ricerca
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(32, 52, 85));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        rightColumn.add(searchPanel);
        searchPanel.setLayout(null);

        JLabel lblSubtitle = new JLabel("Scegli la tua prossima auto da noleggiare");
        lblSubtitle.setForeground(new Color(216, 195, 182));
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 50));
        lblSubtitle.setAlignmentX(0.5f);
        lblSubtitle.setBounds(162, 31, 922, 59);
        searchPanel.add(lblSubtitle);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(62, 88, 121));
        panel.setBorder(BorderFactory.createLineBorder(new Color(216, 195, 182), 3));
        panel.setBounds(119, 176, 956, 100);
        panel.setSize(1000, 100);
        searchPanel.add(panel);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("Mostra Auto");
        btnNewButton.setBounds(850, 25, 117, 45);
        panel.add(btnNewButton);

        JLabel lblDataDiRitiro = new JLabel("Data di ritiro");
        lblDataDiRitiro.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblDataDiRitiro.setBounds(345, 20, 110, 16);
        panel.add(lblDataDiRitiro);

        JLabel lblDataDiRestituzione = new JLabel("Data di restituzione");
        lblDataDiRestituzione.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblDataDiRestituzione.setBounds(570, 20, 171, 16);
        panel.add(lblDataDiRestituzione);
        // Combobox per giorno, mese e anno di ritiro
        JComboBox<Integer> pickupDay = new JComboBox<>();
        JComboBox<Integer> pickupMonth = new JComboBox<>();
        JComboBox<Integer> pickupYear = new JComboBox<>();

        for (int i = 1; i <= 31; i++) {
            pickupDay.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            pickupMonth.addItem(i);
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i <= currentYear + 5; i++) {
            pickupYear.addItem(i);
        }

        pickupDay.setBounds(337, 45, 60, 25);
        pickupMonth.setBounds(389, 45, 66, 25);
        pickupYear.setBounds(447, 45, 86, 25);

        panel.add(pickupDay);
        panel.add(pickupMonth);
        panel.add(pickupYear);

        // Combobox per giorno, mese e anno di restituzione
        JComboBox<Integer> returnDay = new JComboBox<>();
        JComboBox<Integer> returnMonth = new JComboBox<>();
        JComboBox<Integer> returnYear = new JComboBox<>();

        for (int i = 1; i <= 31; i++) {
            returnDay.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            returnMonth.addItem(i);
        }
        for (int i = currentYear; i <= currentYear + 5; i++) {
            returnYear.addItem(i);
        }

        returnDay.setBounds(567, 45, 60, 25);
        returnMonth.setBounds(619, 45, 66, 25);
        returnYear.setBounds(677, 45, 86, 25);

        panel.add(returnDay);
        panel.add(returnMonth);
        panel.add(returnYear);


        JComboBox<String> brandComboBox = new JComboBox<>();
        brandComboBox.setBounds(21, 43, 117, 27);
        panel.add(brandComboBox);

        modelComboBox = new JComboBox<>();
        modelComboBox.setBounds(179, 43, 117, 27);
        panel.add(modelComboBox);

        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblMarca.setBounds(26, 20, 61, 16);
        panel.add(lblMarca);

        JLabel lblModello = new JLabel("Modello");
        lblModello.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblModello.setBounds(183, 20, 93, 16);
        panel.add(lblModello);

        // Popola i dati delle marche e modelli
        carData = new HashMap<>();
        carData.put("Audi", new String[]{"A1", "A2", "A3", "A4", "A5", "A6", "A7"});
        carData.put("BMW", new String[]{"X1", "X2", "X3", "X4", "X5", "X6", "X7"});
        carData.put("Volkswagen", new String[]{"T-Roc", "T-Cross", "Tiguan", "Touareg", "Passat", "Golf"});
        carData.put("Fiat", new String[]{"Panda", "500", "Bravo", "Fiorino", "Freemont"});
        carData.put("Mercedes-Benz", new String[]{"A-180d", "B-200d", "C63-AMG", "EQA", "G63-AMG"});

        // Aggiunge le marche al primo menu a scelta
        brandComboBox.addItem("...");
        for (String brand : carData.keySet()) {
            brandComboBox.addItem(brand);
        }

        brandComboBox.addActionListener(e -> {
            String selectedBrand = (String) brandComboBox.getSelectedItem();
            updateModelComboBox(selectedBrand);
        });

        // Aggiunta dei pannelli al layout principale
        mainPanel.add(leftColumn, BorderLayout.WEST);
        mainPanel.add(rightColumn, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(150, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private void updateModelComboBox(String brand) {
        modelComboBox.removeAllItems();
        if (brand != null && carData.containsKey(brand)) {
            for (String model : carData.get(brand)) {
                modelComboBox.addItem(model);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchPage searchPage = new SearchPage();
            searchPage.setVisible(true);
        });
    }
}
