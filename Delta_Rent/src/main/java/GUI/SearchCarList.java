package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchCarList extends JPanel {
    public SearchCarList(String brand, String model, String pickupDay, String pickupMonth, String pickupYear, String returnDay, String returnMonth, String returnYear) {
        // Layout principale
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        // Colonna destra
        JPanel rightColumn = new JPanel();
        rightColumn.setBackground(new Color(32, 52, 85));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        // Pannello superiore con i dettagli della ricerca
        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(new Color(62, 88, 121));
        detailsPanel.setBorder(BorderFactory.createLineBorder(new Color(216, 195, 182), 3));
        detailsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        detailsPanel.setPreferredSize(new Dimension(1000, 100));
        detailsPanel.setMaximumSize(new Dimension(1000, 100));

        JLabel lblBrand = new JLabel("Marca: " + brand);
        lblBrand.setForeground(new Color(216, 195, 182));
        lblBrand.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblModel = new JLabel("Modello: " + model);
        lblModel.setForeground(new Color(216, 195, 182));
        lblModel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblPickupDate = new JLabel("Data di ritiro: " + pickupDay + "/" + pickupMonth + "/" + pickupYear);
        lblPickupDate.setForeground(new Color(216, 195, 182));
        lblPickupDate.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblReturnDate = new JLabel("Data di restituzione: " + returnDay + "/" + returnMonth + "/" + returnYear);
        lblReturnDate.setForeground(new Color(216, 195, 182));
        lblReturnDate.setFont(new Font("Arial", Font.PLAIN, 20));

        detailsPanel.add(lblBrand);
        detailsPanel.add(lblModel);
        detailsPanel.add(lblPickupDate);
        detailsPanel.add(lblReturnDate);

        rightColumn.add(detailsPanel);

        // Tabella per le auto disponibili
        String[] columnNames = {"Marca", "Modello", "Prezzo giornaliero (€)", "Disponibilità"};
        List<String[]> carData = getAvailableCars(brand, model);

        String[][] tableData = carData.toArray(new String[0][0]);
        JTable carTable = new JTable(tableData, columnNames);
        carTable.setFont(new Font("Arial", Font.PLAIN, 18));
        carTable.setRowHeight(30);
        carTable.setForeground(Color.BLACK);
        carTable.setBackground(Color.LIGHT_GRAY);

        JScrollPane tableScrollPane = new JScrollPane(carTable);
        tableScrollPane.setPreferredSize(new Dimension(1000, 600));
        rightColumn.add(tableScrollPane);

        // Aggiunta della colonna destra al layout principale
        add(rightColumn, BorderLayout.CENTER);
    }

    private List<String[]> getAvailableCars(String brand, String model) {
        // Simula un database con dati di esempio
        List<String[]> cars = new ArrayList<>();

        if (brand.equals("Audi") && model.equals("A3")) {
            cars.add(new String[]{"Audi", "A3", "80", "Disponibile"});
            cars.add(new String[]{"Audi", "A3", "85", "Disponibile"});
        } else if (brand.equals("BMW") && model.equals("X1")) {
            cars.add(new String[]{"BMW", "X1", "100", "Disponibile"});
            cars.add(new String[]{"BMW", "X1", "105", "Non disponibile"});
        } else {
            cars.add(new String[]{"Generico", "Generico", "50", "Disponibile"});
        }

        return cars;
    }
}
