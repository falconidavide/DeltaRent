package GUI;

import Prenotazione.Prenotazione;
import Utente.Utente;
import Veicolo.Automobile;
import Veicolo.Furgone;
import Veicolo.Veicolo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import org.jdatepicker.impl.*;

import DB.Prenota;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class DettagliVeicoloPage extends JPanel {
    private JLabel lblMarcaModello;
    private JLabel lblAlimentazione;
    private JLabel lblDisponibile;
    private JLabel lblPrezzo;
    private JLabel lblImg;
    private JLabel lblPrezzoTotale;
    private JButton btnNoleggia;
    private JDatePickerImpl datePickerInizio, datePickerFine;
    private JComboBox<String> comboOraInizio, comboOraFine;
    private boolean isSettingDate = false;
    private Veicolo veicolo;
    private Utente utente; // Supponiamo che l'utente sia già disponibile

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
        setBackground(new Color(60, 87, 121));
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
        String imgPath = isAutomobile ? ((Automobile) veicolo).getPathImg() : ((Furgone) veicolo).getPathImg();
        String marca = isAutomobile ? ((Automobile) veicolo).getMarca() : ((Furgone) veicolo).getMarca();
        String modello = isAutomobile ? ((Automobile) veicolo).getModello() : ((Furgone) veicolo).getModello();
        String alimentazione = isAutomobile ? ((Automobile) veicolo).getAlimentazione() : ((Furgone) veicolo).getAlimentazione();
        boolean disponibile = isAutomobile ? ((Automobile) veicolo).getDisponibile() : ((Furgone) veicolo).getDisponibile();
        double prezzo = isAutomobile ? ((Automobile) veicolo).getPrezzoOrario() : ((Furgone) veicolo).getPrezzoGiornaliero();

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblImg = new JLabel();
        lblImg.setIcon(resizeImageIcon(imgPath, 300, 225)); // Ingrandisci l'immagine
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.insets = new Insets(10, 10, 10, 10); // Spazio solo per l'immagine
        infoPanel.add(lblImg, gbc);

        Font font = new Font("Arial", Font.BOLD, 22); // Ingrandisci il font

        lblMarcaModello = new JLabel(marca + " " + modello);
        lblMarcaModello.setForeground(Color.WHITE);
        lblMarcaModello.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5); // Spazio ridotto tra gli altri componenti
        infoPanel.add(lblMarcaModello, gbc);

        lblAlimentazione = new JLabel(alimentazione);
        lblAlimentazione.setForeground(Color.WHITE);
        lblAlimentazione.setFont(font);
        gbc.gridy = 1;
        infoPanel.add(lblAlimentazione, gbc);

        lblDisponibile = new JLabel(disponibile ? "Disponibile" : "Non Disponibile");
        lblDisponibile.setForeground(disponibile ? Color.GREEN : Color.RED);
        lblDisponibile.setFont(font);
        gbc.gridy = 2;
        infoPanel.add(lblDisponibile, gbc);

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
            BorderFactory.createEmptyBorder(5, 20, 10, 20) // Padding di 15 px su tutti i lati
        ));
        
        datePickerInizio = createDatePicker();
        datePickerFine = createDatePicker();

        comboOraInizio = createHourComboBox();
        comboOraFine = createHourComboBox();
        comboOraFine.setSelectedIndex(0);

        JLabel dataInizioLbl = new JLabel("Data Inizio:");
        dataInizioLbl.setForeground(Color.white);
        datePanel.add(dataInizioLbl);
        datePanel.add(datePickerInizio);
        datePanel.add(comboOraInizio);

        JLabel dataFineLbl = new JLabel("Data Fine:");
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
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(60, 87, 121));

        lblPrezzoTotale = new JLabel("Prezzo totale: €0");
        lblPrezzoTotale.setForeground(Color.WHITE);
        lblPrezzoTotale.setFont(new Font("Arial", Font.BOLD, 18)); // Mantieni lo stesso font
        lblPrezzoTotale.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrezzoTotale.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Aggiungi un margine sotto
        bottomPanel.add(lblPrezzoTotale);

        btnNoleggia = new JButton("Noleggia");
        btnNoleggia.setFont(new Font("Arial", Font.BOLD, 18)); // Aumenta la dimensione del font per il bottone
        btnNoleggia.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(btnNoleggia);

        return bottomPanel;
    }

    private JDatePickerImpl createDatePicker() {
        Properties properties = new Properties();
        properties.put("text.today", "Oggi");
        properties.put("text.month", "Mese");
        properties.put("text.year", "Anno");

        UtilDateModel model = new UtilDateModel();
        model.setValue(new Date()); // Imposta la data corrente
        model.setSelected(true);

        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        // Non permettere di selezionare date già passate
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
        comboBox.setSelectedIndex(0); // Imposta all'ora corrente
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

            if (fine.before(inizio)) {
                lblPrezzoTotale.setText("Errore: Data di fine non valida");
                btnNoleggia.setEnabled(false);
                return;
            } else {
                btnNoleggia.setEnabled(true);
            }

            long durata = fine.getTime() - inizio.getTime();
            double prezzoTotale = isAutomobile ? (durata / (1000 * 60 * 60.0)) * prezzo : (durata / (1000 * 60 * 60 * 24.0)) * prezzo;

            lblPrezzoTotale.setText(String.format("Prezzo totale: €%.2f", prezzoTotale));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore nel calcolo del prezzo.");
        }
    }

    private ImageIcon resizeImageIcon(String path, int width, int height) {
        if (path == null || path.isEmpty()) {
            path = "img/default_car.jpg"; // Immagine predefinita
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

    private void creaPrenotazione() {
        if (!utente.isLoggato()) {
            JOptionPane.showMessageDialog(this, "Devi essere loggato per poter noleggiare un veicolo.");
            return;
        }

        try {
        	// Ottieni il valore dal date picker
        	 Date dataInizioDate = (Date) datePickerInizio.getModel().getValue();
        	 
        	 String dataInizio=null;
        	 String dataFine=null;

        	 // Verifica che il valore non sia null
        	 if (dataInizioDate != null) {
        	     // Converte il valore in una stringa formattata
        	     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        	     dataInizio = sdf.format(dataInizioDate);
        	 } else {
        	     System.out.println("Nessuna data selezionata nel date picker.");
        	 }
        	 
        	 Date dataFineDate = (Date) datePickerFine.getModel().getValue();

        	 // Verifica che il valore non sia null
        	 if (dataFineDate != null) {
        	     // Converte il valore in una stringa formattata
        	     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        	     dataFine = sdf.format(dataFineDate);
        	 } else {
        	     System.out.println("Nessuna data selezionata nel date picker.");
        	 }

        	String oraInizio = (String) comboOraInizio.getSelectedItem();
        	String oraFine = (String) comboOraFine.getSelectedItem();

        	// Combina data e ora in formato stringa
        	String inizio = dataInizio + " " + oraInizio;
        	String fine = dataFine + " " + oraFine;

        	// Validazione (opzionale): Verifica se il formato è corretto
        	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        	sdf.setLenient(false); // Per rendere più rigoroso il parsing
        	
        	Date dataInizioD = sdf.parse(inizio);
            Date dataFineD = sdf.parse(fine);
           

            if (dataInizioD.before(dataInizioD)) {
                JOptionPane.showMessageDialog(this, "Errore: Data di fine non valida");
                return;
            }

            Prenotazione prenotazione = new Prenotazione(inizio, fine, utente, veicolo);
            Prenota prenota = new Prenota();
            prenota.aggiungiPrenotazione(prenotazione);

            JOptionPane.showMessageDialog(this, "Prenotazione effettuata con successo!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage()+"Errore nella creazione della prenotazione.");
        }
    }
}