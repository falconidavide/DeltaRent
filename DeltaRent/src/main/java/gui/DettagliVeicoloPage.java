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
    private JLabel lblAlimentazione;
    private JLabel lblDisponibile= new JLabel();
    private JLabel lblPrezzo;
    private JLabel lblImg;
    private JLabel lblPrezzoTotale;
    private JButton btnNoleggia;
    private JDatePickerImpl datePickerInizio, datePickerFine;
    private JComboBox<String> comboOraInizio, comboOraFine;
    private boolean isSettingDate = false;
    private Veicolo veicolo;
    private Utente utente; // Supponiamo che l'utente sia già disponibile
	private boolean disponibile;

    public DettagliVeicoloPage(Automobile auto, Utente utente) {
        this.veicolo = auto;
        this.utente = utente;
        setupPanel();
        populatePanel(auto, true);
    }

    /**
     * @wbp.parser.constructor
     */
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
        String imgPath = isAutomobile ? ((Automobile) veicolo).getPathImg() : ((Furgone) veicolo).getPathImg();
        String marca = isAutomobile ? ((Automobile) veicolo).getMarca() : ((Furgone) veicolo).getMarca();
        String modello = isAutomobile ? ((Automobile) veicolo).getModello() : ((Furgone) veicolo).getModello();
        String alimentazione = isAutomobile ? ((Automobile) veicolo).getAlimentazione() : ((Furgone) veicolo).getAlimentazione();
        double prezzo = isAutomobile ? ((Automobile) veicolo).getPrezzoOrario() : ((Furgone) veicolo).getPrezzoGiornaliero();

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        infoPanel.setBorder(new LineBorder(new Color(216, 195, 182), 3, true));
        lblImg = new JLabel();
        lblImg.setIcon(SearchPage.resizeImageIcon(imgPath, 300, 225)); // Ingrandisci l'immagine
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
        JPanel bottomPanel = new JPanel(new GridBagLayout()); // Usa GridBagLayout
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
        lblPrezzoTotale.setFont(new Font("Arial", Font.BOLD, 18)); // Mantieni lo stesso font
        gbc.gridy = 1;
        bottomPanel.add(lblPrezzoTotale, gbc);

        // Aggiungi btnNoleggia centrato
        btnNoleggia = new JButton("Noleggia");
        btnNoleggia.setFont(new Font("Arial", Font.BOLD, 18)); // Aumenta la dimensione del font per il bottone
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
            
            disponibile=Disponibilita.verificaDisponibilita(veicolo, inizio, fine);
            
            lblDisponibile.setText(disponibile ? "Disponibile" : "Non Disponibile");
            lblDisponibile.setForeground(disponibile ? Color.GREEN : Color.RED);
            lblDisponibile.setFont(new Font("Arial", Font.BOLD, 22));
            

	            if (fine.before(inizio)) {
	                lblPrezzoTotale.setText("Errore: Data di fine non valida");
	                btnNoleggia.setEnabled(false);
	                return;
	            } else {
	            	if(disponibile)
	                btnNoleggia.setEnabled(true);
	            }
	          
	
	            long durata = fine.getTime() - inizio.getTime();
	            double prezzoTotale = isAutomobile ? (durata / (1000 * 60 * 60.0)) * prezzo : (durata / (1000 * 60 * 60 * 24.0)) * prezzo;
	
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
        
            if (dataFineD.before(dataInizioD)) {
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