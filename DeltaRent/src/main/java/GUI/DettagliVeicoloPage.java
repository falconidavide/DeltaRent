package GUI;

import org.jdatepicker.impl.*;
import Veicolo.Automobile;
import Veicolo.Furgone;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
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

    public DettagliVeicoloPage(Automobile auto) {
        setupPanel();
        populatePanel(auto, true);
    }

    public DettagliVeicoloPage(Furgone furgone) {
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

        // Sezione superiore (immagine e info)
        JPanel infoPanel = createInfoPanel(veicolo, isAutomobile);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(infoPanel, gbc);

        // Sezione selezione date e ore
        JPanel datePanel = createDatePanel(prezzo, isAutomobile);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(datePanel, gbc);

        // Sezione inferiore (prezzo e pulsante)
        JPanel bottomPanel = createBottomPanel(prezzo, isAutomobile);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(bottomPanel, gbc);
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
        datePanel.setBorder(BorderFactory.createTitledBorder("Prenotazione"));

        datePickerInizio = createDatePicker();
        datePickerFine = createDatePicker();

        comboOraInizio = createHourComboBox();
        comboOraFine = createHourComboBox();

        datePanel.add(new JLabel("Data Inizio:"));
        datePanel.add(datePickerInizio);
        datePanel.add(comboOraInizio);

        datePanel.add(new JLabel("Data Fine:"));
        datePanel.add(datePickerFine);
        datePanel.add(comboOraFine);

        // Listener per aggiornare il prezzo totale in diretta
        ActionListener updatePriceListener = e -> updateTotalPrice(prezzo, isAutomobile);
        ChangeListener startDateListener = e -> {
            if (isSettingDate) return;
            isSettingDate = true;
            updateTotalPrice(prezzo, isAutomobile);
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
            updateTotalPrice(prezzo, isAutomobile);
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
                return;
            }

            long durata = fine.getTime() - inizio.getTime();
            double prezzoTotale = isAutomobile ? (durata / (1000 * 60 * 60.0)) * prezzo : (durata / (1000 * 60 * 60 * 24.0)) * prezzo;

            lblPrezzoTotale.setText(String.format("Prezzo totale: €%.2f", prezzoTotale));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore nel calcolo del prezzo.");
        }
    }

    private void updateTotalPrice(double prezzo, boolean isAutomobile) {
        calcolaPrezzo(prezzo, isAutomobile);
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
}