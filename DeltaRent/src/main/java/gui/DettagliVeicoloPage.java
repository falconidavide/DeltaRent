package gui;

import db.Prenota;
import prenotazione.Prenotazione;
import utente.Utente;
import util.Disponibilita;
import veicolo.Automobile;
import veicolo.Furgone;
import veicolo.Veicolo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DettagliVeicoloPage extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel lblMarcaModello;
    private JLabel lblImmagineAlimentazione; 
    private JLabel lblImmagineAlimentazione2;
    private JLabel lblAlimentazione;
    private JLabel lblDisponibile = new JLabel();
    private JLabel lblPrezzo;
    private JLabel lblPrezzoTotale;
    private JButton btnNoleggia;
    private DateChooser dateChooser;
    private JComboBox<String> comboOraInizio, comboOraFine;
    private boolean isSettingDate = false;
    private Veicolo veicolo;
    private Utente utente;
    private boolean disponibile;
    private double prezzoTotale;

    public DettagliVeicoloPage(Automobile auto, Utente utente) {
        this.veicolo = auto;
        this.utente = utente;
        setupPanel();
        populatePanel(auto, true);
    }

    public DettagliVeicoloPage(Furgone furgone, Utente utente) {
        this.veicolo = furgone;
        this.utente = utente;
        setupPanel();
        populatePanel(furgone, false);
    }

    private void setupPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(28, 51, 84));
        JButton btnTornaIndietro = new JButton("Torna alla Ricerca");
        btnTornaIndietro.setFont(new Font("Arial", Font.BOLD, 18));
        btnTornaIndietro.addActionListener(e -> HomePage.cardLayout.show(HomePage.mainContentPanel, "rentCar"));
        add(btnTornaIndietro, BorderLayout.NORTH); // Aggiungi il vincolo BorderLayout.NORTH
    }

    private void populatePanel(Object veicolo, boolean isAutomobile) {
        double prezzo = isAutomobile ? ((Automobile) veicolo).getPrezzoOrario() : ((Furgone) veicolo).getPrezzoGiornaliero();

        setLayout(new BorderLayout()); // Imposta il layout a BorderLayout

        JPanel infoPanel = createInfoPanel(veicolo, isAutomobile);
        add(infoPanel, BorderLayout.CENTER); // Usa BorderLayout.CENTER per infoPanel

        JPanel dateAndBottomPanel = new JPanel();
        dateAndBottomPanel.setLayout(new BoxLayout(dateAndBottomPanel, BoxLayout.Y_AXIS));

        JPanel datePanel = createDatePanel(prezzo, isAutomobile);
        dateAndBottomPanel.add(datePanel); // Aggiungi il datePanel al dateAndBottomPanel

        JPanel bottomPanel = createBottomPanel(prezzo, isAutomobile);
        dateAndBottomPanel.add(bottomPanel); // Aggiungi il bottomPanel al dateAndBottomPanel

        add(dateAndBottomPanel, BorderLayout.SOUTH); // Usa BorderLayout.SOUTH per dateAndBottomPanel

        // Aggiungi il listener al pulsante "Noleggia"
        btnNoleggia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaPrenotazione();
            }
        });
    }
    private JPanel createInfoPanel(Object veicolo, boolean isAutomobile) {
        String[] imgPaths = isAutomobile ? ((Automobile) veicolo).getPathImgs() : ((Furgone) veicolo).getPathImgs();
        String marca = isAutomobile ? ((Automobile) veicolo).getMarca() : ((Furgone) veicolo).getMarca();
        String modello = isAutomobile ? ((Automobile) veicolo).getModello() : ((Furgone) veicolo).getModello();
        String alimentazione = isAutomobile ? ((Automobile) veicolo).getAlimentazione() : ((Furgone) veicolo).getAlimentazione();
        double prezzo = isAutomobile ? ((Automobile) veicolo).getPrezzoOrario() : ((Furgone) veicolo).getPrezzoGiornaliero();

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        infoPanel.setBorder(new LineBorder(new Color(216, 195, 182), 3, true));
        infoPanel.setSize(700, 500);

        // CardLayout per scorrere le immagini
        JPanel imagePanel = new JPanel(new CardLayout());
        imagePanel.setBackground(new Color(60, 87, 121));

        JProgressBar progressBar = new JProgressBar(0, imgPaths.length - 1);
        progressBar.setValue(0); // Valore iniziale
        progressBar.setBackground(new Color(200, 200, 200));

        for (String imgPath : imgPaths) {
            JLabel imgLabel = new JLabel();
            imgLabel.setIcon(SearchPage.resizeImageIcon(imgPath, 340, 225));
            imagePanel.add(imgLabel);
        }

        // Pulsanti per navigare tra le immagini
        JButton btnPrev = new JButton("<");
        JButton btnNext = new JButton(">");

        btnPrev.addActionListener(e -> {
            CardLayout cl = (CardLayout) imagePanel.getLayout();
            cl.previous(imagePanel);
            int currentIndex = (progressBar.getValue() - 1 + imgPaths.length) % imgPaths.length;
            progressBar.setValue(currentIndex);
        });

        btnNext.addActionListener(e -> {
            CardLayout cl = (CardLayout) imagePanel.getLayout();
            cl.next(imagePanel);
            int currentIndex = (progressBar.getValue() + 1) % imgPaths.length;
            progressBar.setValue(currentIndex);
        });

        // Aggiungi il pannello immagine e i pulsanti
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        infoPanel.add(imagePanel, gbc);

        // Aggiungi la barra progressiva
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel navigationPanel = new JPanel();
        navigationPanel.add(btnPrev);
        navigationPanel.add(progressBar);
        navigationPanel.add(btnNext);
        navigationPanel.setBackground(new Color(60, 87, 121));
        gbc.gridy = 4;
        infoPanel.add(navigationPanel, gbc);

        // Altri dettagli del veicolo
        Font font = new Font("Arial", Font.BOLD, 22);

        lblMarcaModello = new JLabel(marca + " " + modello);
        lblMarcaModello.setForeground(Color.WHITE);
        lblMarcaModello.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        infoPanel.add(lblMarcaModello, gbc);

        lblAlimentazione = new JLabel(alimentazione);
        lblAlimentazione.setForeground(Color.WHITE);
        lblAlimentazione.setFont(font);
        gbc.gridy = 1;
        infoPanel.add(lblAlimentazione, gbc);

        lblImmagineAlimentazione = new JLabel();
        lblImmagineAlimentazione2 = new JLabel();
        switch (alimentazione) {
            case "Benzina":
                lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("benzina.png", 35, 35));
                lblImmagineAlimentazione2.setIcon(null);
                break;
            case "Diesel":
                lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("diesel.png", 35, 35));
                lblImmagineAlimentazione2.setIcon(null);
                break;
            case "Elettrica":
                lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("elettrica.png", 35, 35));
                lblImmagineAlimentazione2.setIcon(null);
                break;
            case "Ibrida":
                lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("elettrica.png", 35, 35));
                lblImmagineAlimentazione2.setIcon(SearchPage.resizeImageIcon("benzina.png", 35, 35));
                break;
            case "GPL":
                lblImmagineAlimentazione.setIcon(SearchPage.resizeImageIcon("gpl.png", 35, 35));
                lblImmagineAlimentazione2.setIcon(null);
                break;
        }
        JPanel alimentazionePanel = new JPanel();
        alimentazionePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        alimentazionePanel.setBackground(new Color(60, 87, 121));
        alimentazionePanel.add(lblImmagineAlimentazione);
        if (lblImmagineAlimentazione2.getIcon() != null) {
            alimentazionePanel.add(lblImmagineAlimentazione2);
        }
        gbc.gridy = 2;
        infoPanel.add(alimentazionePanel, gbc);

        lblPrezzo = new JLabel(isAutomobile ? "Prezzo €/h: €" + prezzo : "Prezzo €/day: €" + prezzo);
        lblPrezzo.setForeground(Color.WHITE);
        lblPrezzo.setFont(new Font("sansserif", Font.BOLD, 18));
        gbc.gridy = 3;
        infoPanel.add(lblPrezzo, gbc);

        infoPanel.setBackground(new Color(60, 87, 121));
        return infoPanel;
    }

    private JPanel createDatePanel(double prezzo, boolean isAutomobile) {
        JPanel datePanel = new JPanel(new GridBagLayout());
        datePanel.setBackground(new Color(60, 87, 121));
        datePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(216, 195, 182), 3, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20) // Padding aggiunto
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        dateChooser = new DateChooser();
        dateChooser.setThemeColor(getBackground());
        dateChooser.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        dateChooser.setPreferredSize(new Dimension(450, 260)); // Imposta la dimensione preferita
        dateChooser.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);

        comboOraInizio = createHourComboBox();
        comboOraFine = createHourComboBox();
        comboOraFine.setSelectedIndex(0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        datePanel.add(dateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        datePanel.add(new JLabel("Ora Inizio:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        datePanel.add(comboOraInizio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        datePanel.add(new JLabel("Ora Fine:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        datePanel.add(comboOraFine, gbc);

        // Listener per aggiornare il prezzo totale in diretta
        dateChooser.addActionDateChooserListener(new DateChooserListener() {
            @Override
            public void dateChanged(Date date, DateChooserAction action) {
                if (isSettingDate) return;
                isSettingDate = true;

                // Calcola il prezzo totale
                calcolaPrezzo(prezzo, isAutomobile);

                isSettingDate = false;
            }

            @Override
            public void dateBetweenChanged(DateBetween dateBetween, DateChooserAction action) {
                if (isSettingDate) return;
                isSettingDate = true;

                // Calcola il prezzo totale
                calcolaPrezzo(prezzo, isAutomobile);

                isSettingDate = false;
            }
        });

        comboOraInizio.addActionListener(e -> calcolaPrezzo(prezzo, isAutomobile));
        comboOraFine.addActionListener(e -> calcolaPrezzo(prezzo, isAutomobile));

        return datePanel;
    }


    private JPanel createBottomPanel(double prezzo, boolean isAutomobile) {
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 15, 10); // Aggiungi un margine superiore di 10 pixel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.setBackground(new Color(60, 87, 121));
        bottomPanel.setBorder(new LineBorder(new Color(216, 195, 182), 3, true));

        // Aggiungi lblDisponibile centrato
        lblDisponibile.setFont(new Font("Arial", Font.BOLD, 22));
        lblDisponibile.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        bottomPanel.add(lblDisponibile, gbc);

        // Aggiungi lblPrezzoTotale centrato
        lblPrezzoTotale = new JLabel("Prezzo totale: €0");
        lblPrezzoTotale.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrezzoTotale.setForeground(Color.WHITE);
        lblPrezzoTotale.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.insets = new Insets(0, 10, 15, 10); // Resetta gli insets per gli altri componenti
        gbc.gridy = 1;
        bottomPanel.add(lblPrezzoTotale, gbc);

        // Aggiungi btnNoleggia centrato
        btnNoleggia = new JButton("Noleggia");
        btnNoleggia.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE; // Imposta il fill a NONE per il pulsante
        gbc.anchor = GridBagConstraints.CENTER;
        bottomPanel.add(btnNoleggia, gbc);

        return bottomPanel;
    }


    private JComboBox<String> createHourComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        for (int h = 9; h <= 22; h++) {
            comboBox.addItem(String.format("%02d:00", h));
            comboBox.addItem(String.format("%02d:30", h));
        }
        comboBox.setSelectedIndex(0);
        return comboBox;
    }

    private void calcolaPrezzo(double prezzo, boolean isAutomobile) {
        try {
            DateBetween dateBetween = dateChooser.getSelectedDateBetween();

            String oraInizio = (String) comboOraInizio.getSelectedItem();
            String oraFine = (String) comboOraFine.getSelectedItem();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date inizio = sdf.parse(new SimpleDateFormat("dd-MM-yyyy").format(dateBetween.getFromDate()) + " " + oraInizio);
            Date fine = sdf.parse(new SimpleDateFormat("dd-MM-yyyy").format(dateBetween.getToDate()) + " " + oraFine);

            disponibile = Disponibilita.verificaDisponibilita(veicolo, inizio, fine);

            lblDisponibile.setText(disponibile ? "Disponibile" : "Non Disponibile");
            lblDisponibile.setForeground(disponibile ? Color.GREEN : Color.RED);
            lblDisponibile.setFont(new Font("Arial", Font.BOLD, 22));

            if (fine.before(inizio) || fine.compareTo(inizio) == 0) {
                lblPrezzoTotale.setText("Errore: Data di fine non valida");
                btnNoleggia.setEnabled(false);
                return;
            } else {
                if (disponibile)
                    btnNoleggia.setEnabled(true);
            }

            long durata = fine.getTime() - inizio.getTime();
            prezzoTotale = isAutomobile ? (durata / (1000 * 60 * 60.0)) * prezzo : (durata / (1000 * 60 * 60 * 24.0)) * prezzo;

            lblPrezzoTotale.setText(String.format("Prezzo totale: €%.2f", prezzoTotale));
            lblPrezzoTotale.setFont(new Font("Arial", Font.BOLD, 22));
            return;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore nel calcolo del prezzo.");
        }
    }

    private void creaPrenotazione() {
        if (!Utente.isLoggato()) {
            JOptionPane.showMessageDialog(this, "Devi essere loggato per poter noleggiare un veicolo.");
            return;
        }

        try {
            DateBetween dateBetween = dateChooser.getSelectedDateBetween();
            String dataInizio = null;
            String dataFine = null;

            if (dateBetween != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                dataInizio = sdf.format(dateBetween.getFromDate());
                dataFine = sdf.format(dateBetween.getToDate());
            } else {
                System.out.println("Nessuna data selezionata nel date picker.");
            }

            String oraInizio = (String) comboOraInizio.getSelectedItem();
            String oraFine = (String) comboOraFine.getSelectedItem();

            String inizio = dataInizio + " " + oraInizio;
            String fine = dataFine + " " + oraFine;

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            sdf.setLenient(false); // Per rendere più rigoroso il parsing

            Date dataInizioD = sdf.parse(inizio);
            Date dataFineD = sdf.parse(fine);

            if (dataFineD.before(dataInizioD) || dataFineD.compareTo(dataInizioD) == 0) {
                JOptionPane.showMessageDialog(this, "Errore: Data di fine non valida");
                return;
            }
            Prenotazione prenotazione = new Prenotazione(inizio, fine, utente, veicolo, prezzoTotale);
            Prenota prenota = new Prenota();
            prenota.aggiungiPrenotazione(prenotazione);

            JOptionPane.showMessageDialog(this, "Prenotazione effettuata con successo!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + "Errore nella creazione della prenotazione.");
        }
    }
}