package GUI;

import DB.GestionePrenotazioni;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Prenotazioni extends JPanel {
    private GestionePrenotazioni gestionePrenotazioni;
    private String emailUtente;

    public Prenotazioni(GestionePrenotazioni gestionePrenotazioni, String emailUtente) {
        this.gestionePrenotazioni = gestionePrenotazioni;
        this.emailUtente = emailUtente;
        List<String> prenotazioniPassate = gestionePrenotazioni.getPrenotazioniPassate(emailUtente);
        initializeUI(prenotazioniPassate);
    }

    private void initializeUI(List<String> prenotazioniPassate) {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        // Titolo
        JLabel titolo = new JLabel("Prenotazioni Passate", SwingConstants.CENTER);
        titolo.setForeground(new Color(216, 195, 182));
        titolo.setFont(new Font("Arial", Font.PLAIN, 30));
        add(titolo, BorderLayout.NORTH);

        // Pannello delle prenotazioni
        JPanel prenotazioniPanel = new JPanel();
        prenotazioniPanel.setLayout(new BoxLayout(prenotazioniPanel, BoxLayout.Y_AXIS));
        prenotazioniPanel.setBackground(new Color(32, 52, 85));
        prenotazioniPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        if (prenotazioniPassate.isEmpty()) {
            JLabel noPrenotazioniLabel = new JLabel("Nessuna prenotazione passata.", SwingConstants.CENTER);
            noPrenotazioniLabel.setForeground(Color.WHITE);
            noPrenotazioniLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            prenotazioniPanel.add(noPrenotazioniLabel);
        } else {
            for (String prenotazione : prenotazioniPassate) {
                JLabel prenotazioneLabel = new JLabel(prenotazione);
                prenotazioneLabel.setForeground(Color.WHITE);
                prenotazioneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                prenotazioneLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                prenotazioniPanel.add(prenotazioneLabel);
            }
        }

        // ScrollPane per rendere scrollabile il pannello delle prenotazioni
        JScrollPane scrollPane = new JScrollPane(prenotazioniPanel);
        scrollPane.getViewport().setBackground(new Color(32, 52, 85)); // Imposta lo sfondo dello scrollPane
        add(scrollPane, BorderLayout.CENTER);
    }
}