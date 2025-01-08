package GUI;

import DB.GestoreVeicoli;
import Veicolo.Automobile;
import Veicolo.Furgone;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class SearchPage extends JPanel {
    @SuppressWarnings("unused")
    private JTextField textField_2;
    @SuppressWarnings("unused")
    private JTextField textField_3;
    static JComboBox<String> modelComboBox;
    static JComboBox<String> brandComboBox = new JComboBox<>();
    // Combobox per giorno, mese e anno di ritiro
    JComboBox<Integer> pickupDay = new JComboBox<>();
    JComboBox<Integer> pickupMonth = new JComboBox<>();
    JComboBox<Integer> pickupYear = new JComboBox<>();
    // Combobox per giorno, mese e anno di restituzione
    JComboBox<Integer> returnDay = new JComboBox<>();
    JComboBox<Integer> returnMonth = new JComboBox<>();
    JComboBox<Integer> returnYear = new JComboBox<>();
    private JPanel vehicleDisplayPanel;

    public SearchPage() {
        // Layout principale
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

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

        JButton btnNewButton = new JButton("Mostra Veicoli");
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

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(60, 87, 121));
        panel_1.setBounds(162, 319, 956, 500); // Aumenta l'altezza per visualizzare più veicoli
        searchPanel.add(panel_1);
        panel_1.setLayout(new GridLayout(0, 3, 10, 10)); // 3 veicoli per riga

        // Pannello di visualizzazione dei veicoli
        vehicleDisplayPanel = panel_1;

        // Aggiunta dei pannelli al layout principale
        add(rightColumn, BorderLayout.CENTER);

        // Aggiunta dell'azione al pulsante
        btnNewButton.addActionListener(e -> mostraVeicoli());

        // Popola la combo box delle marche
        aggiornaComboBoxMarche();

        // Aggiunge un listener alla combo box delle marche per aggiornare la combo box dei modelli
        brandComboBox.addActionListener(e -> {
            String selectedBrand = (String) brandComboBox.getSelectedItem();
            aggiornaComboBoxModelli(selectedBrand);
        });
    }

    private void aggiornaComboBoxMarche() {
        List<String> marche = GestoreVeicoli.getMarcheVeicoli();
        brandComboBox.removeAllItems();
        brandComboBox.addItem("Tutte le Marche");
        for (String marca : marche) {
            brandComboBox.addItem(marca);
        }
    }

    private void aggiornaComboBoxModelli(String marca) {
        modelComboBox.removeAllItems();
        if (marca != null && !marca.equals("Tutte le Marche")) {
            List<String> modelli = GestoreVeicoli.getModelliByMarca(marca);
            modelComboBox.addItem("Tutti i Modelli");
            for (String modello : modelli) {
                modelComboBox.addItem(modello);
            }
        } else {
            modelComboBox.addItem("Tutti i Modelli");
        }
    }

    private void mostraVeicoli() {
        vehicleDisplayPanel.removeAll();
        List<Automobile> automobili = GestoreVeicoli.aggiornaListaAutomobili();
        List<Furgone> furgoni = GestoreVeicoli.aggiornaListaFurgoni();

        String marcaSelezionata = (String) brandComboBox.getSelectedItem();
        String modelloSelezionato = (String) modelComboBox.getSelectedItem();

        for (Automobile auto : automobili) {
            if ((marcaSelezionata == null || marcaSelezionata.equals("Tutte le Marche") || auto.getMarca().equals(marcaSelezionata)) &&
                (modelloSelezionato == null || modelloSelezionato.equals("Tutti i Modelli") || auto.getModello().equals(modelloSelezionato))) {
                vehicleDisplayPanel.add(creaPannelloVeicolo(auto.getMarca(), auto.getModello(), auto.getTarga(), auto.getDisponibile(), auto.getPrezzoOrario(), auto.getPathImg()));
            }
        }

        for (Furgone furgone : furgoni) {
            if ((marcaSelezionata == null || marcaSelezionata.equals("Tutte le Marche") || furgone.getMarca().equals(marcaSelezionata)) &&
                (modelloSelezionato == null || modelloSelezionato.equals("Tutti i Modelli") || furgone.getModello().equals(modelloSelezionato))) {
                vehicleDisplayPanel.add(creaPannelloVeicolo(furgone.getMarca(), furgone.getModello(), furgone.getTarga(), furgone.getDisponibile(), furgone.getPrezzoGiornaliero(), furgone.getPathImg()));
            }
        }

        vehicleDisplayPanel.revalidate();
        vehicleDisplayPanel.repaint();
    }

    private JPanel creaPannelloVeicolo(String marca, String modello, String targa, boolean disponibile, int prezzo, String pathImg) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(60, 87, 121));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel lblImg = new JLabel();
        lblImg.setIcon(resizeImageIcon(pathImg, 200, 150)); // Ridimensiona l'immagine mantenendo le proporzioni
        lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMarca = new JLabel("Marca: " + marca);
        lblMarca.setForeground(Color.WHITE);
        lblMarca.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblModello = new JLabel("Modello: " + modello);
        lblModello.setForeground(Color.WHITE);
        lblModello.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTarga = new JLabel("Targa: " + targa);
        lblTarga.setForeground(Color.WHITE);
        lblTarga.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDisponibile = new JLabel("Disponibile: " + (disponibile ? "Sì" : "No"));
        lblDisponibile.setForeground(Color.WHITE);
        lblDisponibile.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblPrezzo = new JLabel("Prezzo: €" + prezzo);
        lblPrezzo.setForeground(Color.WHITE);
        lblPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(lblImg);
        panel.add(lblMarca);
        panel.add(lblModello);
        panel.add(lblTarga);
        panel.add(lblDisponibile);
        panel.add(lblPrezzo);

        return panel;
    }

    private ImageIcon resizeImageIcon(String path, int width, int height) {
        if (path == null || path.isEmpty()) {
            // Se il percorso è null o vuoto, usa un'immagine predefinita
            path = "path/to/default/image.jpg";
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