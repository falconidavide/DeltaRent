package gui;

import db.GestionePrenotazioni;
import prenotazione.Prenotazione;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Prenotazioni extends JPanel {
    private static final long serialVersionUID = 1L;
    private GestionePrenotazioni gestionePrenotazioni;
    private String emailUtente;
    private JPanel prenotazioniPanel;

    public Prenotazioni(GestionePrenotazioni gestionePrenotazioni, String emailUtente) {
        this.gestionePrenotazioni = gestionePrenotazioni;
        this.emailUtente = emailUtente;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        // Titolo
        JLabel titolo = new JLabel("Prenotazioni", SwingConstants.CENTER);
        titolo.setForeground(new Color(216, 195, 182));
        titolo.setFont(new Font("Arial", Font.PLAIN, 30));
        add(titolo, BorderLayout.NORTH);

        // Pannello principale di prenotazioni
        prenotazioniPanel = new JPanel();
        prenotazioniPanel.setLayout(new BoxLayout(prenotazioniPanel, BoxLayout.Y_AXIS));
        prenotazioniPanel.setBackground(new Color(32, 52, 85));
        prenotazioniPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        updatePrenotazioniPanel();

        // ScrollPane per rendere scrollabile il pannello delle prenotazioni
        JScrollPane scrollPane = new JScrollPane(prenotazioniPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(new Color(32, 52, 85)); // Imposta lo sfondo dello scrollPane
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updatePrenotazioniPanel() {
        prenotazioniPanel.removeAll();

        List<Prenotazione> prenotazioniFuture = gestionePrenotazioni.getPrenotazioniFuture(emailUtente);
        List<Prenotazione> prenotazioniPassate = gestionePrenotazioni.getPrenotazioniPassate(emailUtente);

        // Sezione prenotazioni future
        JPanel prenotazioniFuturePanel = createSectionPanel("Prenotazioni Future", prenotazioniFuture, true);
        prenotazioniPanel.add(prenotazioniFuturePanel);

        // Sezione prenotazioni passate
        JPanel prenotazioniPassatePanel = createSectionPanel("Prenotazioni Passate", prenotazioniPassate, false);
        prenotazioniPanel.add(prenotazioniPassatePanel);

        prenotazioniPanel.revalidate();
        prenotazioniPanel.repaint();
    }

    private JPanel createSectionPanel(String title, List<Prenotazione> prenotazioni, boolean isFuture) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BorderLayout());
        sectionPanel.setBackground(new Color(32, 52, 85));
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Titolo della sezione
        JLabel sectionTitle = new JLabel(title, SwingConstants.CENTER);
        sectionTitle.setForeground(new Color(216, 195, 182));
        sectionTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        sectionPanel.add(sectionTitle, BorderLayout.NORTH);

        // Pannello delle prenotazioni
        JPanel prenotazioniInnerPanel = new JPanel();
        prenotazioniInnerPanel.setLayout(new GridLayout(0, 3, 10, 10)); // Layout con 3 righe e 3 colonne
        prenotazioniInnerPanel.setBackground(new Color(32, 52, 85));

        if (prenotazioni.isEmpty()) {
            JLabel noPrenotazioniLabel = new JLabel("Nessuna prenotazione.", SwingConstants.CENTER);
            noPrenotazioniLabel.setForeground(Color.WHITE);
            noPrenotazioniLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            prenotazioniInnerPanel.add(noPrenotazioniLabel);
        } else {
            for (Prenotazione prenotazione : prenotazioni) {
                prenotazioniInnerPanel.add(createPrenotazionePanel(prenotazione, isFuture));
            }

            // Aggiungi pannelli vuoti (segnaposto) per mantenere il layout rettangolare
            int totalPrenotazioni = prenotazioni.size();
            int totalSlots = (totalPrenotazioni + 2) / 3 * 3; // Calcola il numero di slot necessario per completare le righe
            for (int i = totalPrenotazioni; i < totalSlots; i++) {
                prenotazioniInnerPanel.add(creaPannelloSegnaposto());
            }
        }

        sectionPanel.add(prenotazioniInnerPanel, BorderLayout.CENTER);
        return sectionPanel;
    }

    private JPanel createPrenotazionePanel(Prenotazione prenotazione, boolean isFuture) {
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
        double prezzo = prenotazione.getPrezzo(); // Aggiungi il prezzo della prenotazione

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

        JLabel prezzoLabel = new JLabel("Prezzo: €" + prezzo);
        prezzoLabel.setForeground(Color.LIGHT_GRAY);
        prezzoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        prezzoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        prezzoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        detailsPanel.add(prezzoLabel);

        panel.add(detailsPanel, BorderLayout.CENTER);

        // Aggiungi pulsante di annullamento per prenotazioni future
        if (isFuture) {
            JButton annullaButton = new JButton("Annulla Prenotazione");
            annullaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            annullaButton.addActionListener(e -> {
                // Logica per annullare la prenotazione
                GestionePrenotazioni.annullaPrenotazione(prenotazione);
                JOptionPane.showMessageDialog(this, "Prenotazione annullata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                // Aggiorna la lista delle prenotazioni
                updatePrenotazioniPanel();
            });
            detailsPanel.add(annullaButton);
        }

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