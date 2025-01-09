package GUI;

import DB.GestoreVeicoli;
import Utente.Utente;
import Veicolo.Automobile;
import Veicolo.Furgone;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class SearchPage extends JPanel {
    static JComboBox<String> modelComboBox;
    static JComboBox<String> brandComboBox;
    static JCheckBox availableOnlyCheckBox;
    private static JPanel vehicleDisplayPanel;
    static List<Automobile> automobili = null;
    static List<Furgone> furgoni = null;

    public SearchPage() {
        // Inizializza i componenti
        modelComboBox = new JComboBox<>();
        brandComboBox = new JComboBox<>();
        availableOnlyCheckBox = new JCheckBox("Mostra solo veicoli disponibili");

        // Layout principale
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        // Colonna destra
        JPanel rightColumn = new JPanel();
        rightColumn.setBackground(new Color(32, 52, 85));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        // Pannello di ricerca
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(32, 52, 85));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblSubtitle = new JLabel("Scegli la tua prossima auto da noleggiare", SwingConstants.CENTER);
        lblSubtitle.setForeground(new Color(216, 195, 182));
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 30));
        lblSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));  // Aggiungi margine inferiore per distanziamento
        searchPanel.add(lblSubtitle, BorderLayout.NORTH);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filterPanel.setBackground(new Color(62, 88, 121));
        filterPanel.setBorder(BorderFactory.createLineBorder(new Color(216, 195, 182), 3));

        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblMarca.setForeground(Color.WHITE);
        filterPanel.add(lblMarca);
        filterPanel.add(brandComboBox);

        JLabel lblModello = new JLabel("Modello");
        lblModello.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblModello.setForeground(Color.WHITE);
        filterPanel.add(lblModello);
        filterPanel.add(modelComboBox);

        availableOnlyCheckBox.setForeground(new Color(0, 0, 0));
        availableOnlyCheckBox.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        filterPanel.add(availableOnlyCheckBox);

        searchPanel.add(filterPanel, BorderLayout.CENTER);

        rightColumn.add(searchPanel);

        JScrollPane container = new JScrollPane();
        container.getVerticalScrollBar().setUnitIncrement(16);
        JPanel panel_1 = new JPanel();
        container.setViewportView(panel_1);
        panel_1.setBackground(new Color(60, 87, 121));
        panel_1.setLayout(new GridLayout(0, 3, 10, 10)); // Imposta un layout con 3 colonne

        // Pannello di visualizzazione dei veicoli
        vehicleDisplayPanel = panel_1;

        // Aggiunta dei pannelli al layout principale
        add(rightColumn, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);

        aggiornaComboBoxMarche();
        aggiornaComboBoxModelli(null);
        mostraVeicoli();

        // Aggiunge un listener alla combo box delle marche per aggiornare la combo box dei modelli
        brandComboBox.addActionListener(e -> {
            String selectedBrand = (String) brandComboBox.getSelectedItem();
            aggiornaComboBoxModelli(selectedBrand);
            mostraVeicoli();
        });

        modelComboBox.addActionListener(e -> {
            mostraVeicoli();
        });
        availableOnlyCheckBox.addActionListener(e -> {
            mostraVeicoli();
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
        boolean availableOnly = availableOnlyCheckBox.isSelected();
        
        if(Utente.isLoggato()) {
            if(Utente.getIsPrivato()) {
                mostraAuto(marcaSelezionata, modelloSelezionato, availableOnly);
            } else {
                mostraFurgoni(marcaSelezionata, modelloSelezionato, availableOnly);
            }
        } else {
            mostraAuto(marcaSelezionata, modelloSelezionato, availableOnly);
            mostraFurgoni(marcaSelezionata, modelloSelezionato, availableOnly);
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

    private static JPanel creaPannelloSegnaposto() { //crea pannello vuoto
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 87, 121));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0)); 
        return panel;
    }

    private static JPanel creaPannelloVeicolo(String marca, String modello, boolean disponibile, int prezzoOrario, int prezzoGiornaliero,
            String pathImg) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(60, 87, 121));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

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

        JLabel lblDisponibile = new JLabel();
        lblDisponibile.setText(disponibile ? "Disponibile" : "Non Disponibile");
        lblDisponibile.setForeground(disponibile ? Color.GREEN : Color.RED);
        lblDisponibile.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDisponibile.setFont(font);
        lblDisponibile.setBorder(BorderFactory.createEmptyBorder(0, 0, bottomMargin, 0)); // Margine inferiore

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

        panel.add(lblImg);
        panel.add(lblMarcaModello);
        panel.add(lblDisponibile);
        panel.add(lblPrezzo);

        return panel;
    }
    
    private static ImageIcon resizeImageIcon(String path, int width, int height) {
        if (path == null || path.isEmpty()) {
            // Se il percorso è null o vuoto, usa un'immagine predefinita
            path = "default_car.jpg";
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
    
    private static void mostraAuto(String marcaSelezionata, String modelloSelezionato, boolean availableOnly) {
        for (Automobile auto : automobili) {
            if ((marcaSelezionata == null || marcaSelezionata.equals("Tutte le Marche")
                    || auto.getMarca().equals(marcaSelezionata))
                    && (modelloSelezionato == null || modelloSelezionato.equals("Tutti i Modelli")
                            || auto.getModello().equals(modelloSelezionato))
                    && (!availableOnly || auto.getDisponibile())) {
                vehicleDisplayPanel.add(creaPannelloVeicolo(auto.getMarca(), auto.getModello(),
                        auto.getDisponibile(), auto.getPrezzoOrario(), -1, auto.getPathImg()));
            }
        }
        
        
    }
    private static void mostraFurgoni(String marcaSelezionata, String modelloSelezionato, boolean availableOnly) {
        for (Furgone furgone : furgoni) {
            if ((marcaSelezionata == null || marcaSelezionata.equals("Tutte le Marche")
                    || furgone.getMarca().equals(marcaSelezionata))
                    && (modelloSelezionato == null || modelloSelezionato.equals("Tutti i Modelli")
                            || furgone.getModello().equals(modelloSelezionato))
                    && (!availableOnly || furgone.getDisponibile())) {
                vehicleDisplayPanel
                        .add(creaPannelloVeicolo(furgone.getMarca(), furgone.getModello(),
                                furgone.getDisponibile(), -1, furgone.getPrezzoGiornaliero(), furgone.getPathImg()));
            }
        }
    }
}