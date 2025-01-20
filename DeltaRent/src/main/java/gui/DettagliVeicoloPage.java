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
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import org.jdatepicker.impl.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

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
    private JDatePickerImpl datePickerInizio, datePickerFine;
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
        setLayout(new GridBagLayout());
        setBackground(new Color(28, 51, 84));
    }

    private void populatePanel(Object veicolo, boolean isAutomobile) {
        double prezzo = isAutomobile ? ((Automobile) veicolo).getPrezzoOrario() : ((Furgone) veicolo).getPrezzoGiornaliero();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel infoPanel = createInfoPanel(veicolo, isAutomobile);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(infoPanel, gbc);

        JPanel datePanel = createDatePanel(prezzo, isAutomobile);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(datePanel, gbc);

        JPanel bottomPanel = createBottomPanel(prezzo, isAutomobile);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(bottomPanel, gbc);

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
        lblPrezzo.setFont(font);
        gbc.gridy = 3;
        infoPanel.add(lblPrezzo, gbc);

        infoPanel.setBackground(new Color(60, 87, 121));
        return infoPanel;
    }

    private JPanel createDatePanel(double prezzo, boolean isAutomobile) {
        JPanel datePanel = new JPanel(new GridLayout(2, 3, 10, 10));
        datePanel.setBackground(new Color(60, 87, 121));
        datePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, " Prenotazione ", TitledBorder.LEFT, TitledBorder.CENTER, null, Color.white),
            BorderFactory.createEmptyBorder(5, 20, 10, 20)
        ));
        datePanel.setBorder(new LineBorder(new Color(216, 195, 182), 3, true));
        datePickerInizio = createDatePicker();
        datePickerFine = createDatePicker();

        comboOraInizio = createHourComboBox();
        comboOraFine = createHourComboBox();
        comboOraFine.setSelectedIndex(0);

        JLabel dataInizioLbl = new JLabel("DATA INIZIO:");
        dataInizioLbl.setFont(new Font("Arial", Font.BOLD, 18));
        dataInizioLbl.setForeground(Color.white);
        datePanel.add(dataInizioLbl);
        datePanel.add(datePickerInizio);
        datePanel.add(comboOraInizio);

        JLabel dataFineLbl = new JLabel("DATA FINE:");
        dataFineLbl.setFont(new Font("Arial", Font.BOLD, 18));
        dataFineLbl.setForeground(Color.white);
        datePanel.add(dataFineLbl);
        datePanel.add(datePickerFine);
        datePanel.add(comboOraFine);

        // Listener per aggiornare il prezzo totale in diretta
        ActionListener updatePriceListener = e -> calcolaPrezzo(prezzo, isAutomobile);
        ChangeListener startDateListener = e -> {
            if (isSettingDate) return;
            isSettingDate = true;
            calcolaPrezzo(prezzo, isAutomobile);
            // Assicurati che la data di fine non sia prima della data di inizio
            Date startDate = (Date) datePickerInizio.getModel().getValue();
            Date endDate = (Date) datePickerFine.getModel().getValue();
            if (endDate != null && endDate.before(startDate)) {
                setDateValue(datePickerFine, startDate);
            }
            isSettingDate = false;
        };
        datePickerInizio.getModel().addChangeListener(startDateListener);
        datePickerFine.getModel().addChangeListener(e -> {
            if (isSettingDate) return;
            isSettingDate = true;
            calcolaPrezzo(prezzo, isAutomobile);
            isSettingDate = false;
        });
        comboOraInizio.addActionListener(updatePriceListener);
        comboOraFine.addActionListener(updatePriceListener);

        return datePanel;
    }

    private JPanel createBottomPanel(double prezzo, boolean isAutomobile) {
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.setBackground(new Color(60, 87, 121));
        bottomPanel.setBorder(new LineBorder(new Color(216, 195, 182), 3, true));
        // Aggiungi lblDisponibile centrato
        lblDisponibile.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        bottomPanel.add(lblDisponibile, gbc);

        // Aggiungi lblPrezzoTotale centrato
        lblPrezzoTotale = new JLabel("Prezzo totale: €0");
        lblPrezzoTotale.setForeground(Color.WHITE);
        lblPrezzoTotale.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 1;
        bottomPanel.add(lblPrezzoTotale, gbc);

        // Aggiungi btnNoleggia centrato
        btnNoleggia = new JButton("Noleggia");
        btnNoleggia.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 2;
        bottomPanel.add(btnNoleggia, gbc);

        return bottomPanel;
    }

    private JDatePickerImpl createDatePicker() {
        Properties properties = new Properties();
        properties.put("text.today", "Oggi");
        properties.put("text.month", "Mese");
        properties.put("text.year", "Anno");

        UtilDateModel model = new UtilDateModel();
        model.setValue(new Date());
        model.setSelected(true);

        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        datePicker.getModel().addChangeListener(e -> {
            if (isSettingDate) return;
            isSettingDate = true;
            Date selectedDate = (Date) datePicker.getModel().getValue();
            if (selectedDate.before(new Date())) {
                setDateValue(datePicker, new Date());
            }
            isSettingDate = false;
        });

        return datePicker;
    }

    private void setDateValue(JDatePickerImpl datePicker, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        UtilDateModel model = (UtilDateModel) datePicker.getModel();
        model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
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
            Date dataInizio = (Date) datePickerInizio.getModel().getValue();
            Date dataFine = (Date) datePickerFine.getModel().getValue();

            String oraInizio = (String) comboOraInizio.getSelectedItem();
            String oraFine = (String) comboOraFine.getSelectedItem();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date inizio = sdf.parse(new SimpleDateFormat("dd-MM-yyyy").format(dataInizio) + " " + oraInizio);
            Date fine = sdf.parse(new SimpleDateFormat("dd-MM-yyyy").format(dataFine) + " " + oraFine);

            disponibile = Disponibilita.verificaDisponibilita(veicolo, inizio, fine);

            lblDisponibile.setText(disponibile ? "Disponibile" : "Non Disponibile");
            lblDisponibile.setForeground(disponibile ? Color.GREEN : Color.RED);
            lblDisponibile.setFont(new Font("Arial", Font.BOLD, 22));

            if (fine.before(inizio)) {
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
            Date dataInizioDate = (Date) datePickerInizio.getModel().getValue();
            String dataInizio = null;
            String dataFine = null;

            if (dataInizioDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                dataInizio = sdf.format(dataInizioDate);
            } else {
                System.out.println("Nessuna data selezionata nel date picker.");
            }

            Date dataFineDate = (Date) datePickerFine.getModel().getValue();

            if (dataFineDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                dataFine = sdf.format(dataFineDate);
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
        
            if (dataFineD.before(dataInizioD)) {
                JOptionPane.showMessageDialog(this, "Errore: Data di fine non valida");
                return;
            }
            Prenotazione prenotazione = new Prenotazione(inizio, fine, utente, veicolo, prezzoTotale);
            Prenota prenota = new Prenota();
            prenota.aggiungiPrenotazione(prenotazione);

            JOptionPane.showMessageDialog(this, "Prenotazione effettuata con successo!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage()+"Errore nella creazione della prenotazione.");
		}
	}
}