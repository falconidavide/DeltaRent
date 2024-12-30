package Delta_Rent.Delta_Rent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchCarList extends JFrame {
    public SearchCarList(String brand, String model, String pickupDay , String pickupMonth, String pickupYear, String returnDay, String returnMonth,  String returnYear) {
        // Impostazioni della finestra 
        setTitle("DeltaRent - Auto Disponibili");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1300, 950);
        setLocationRelativeTo(null);

        // Layout principale
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        getContentPane().add(mainPanel);

        // Colonna sinistra (Invariata)
        JPanel leftColumn = new JPanel();
        leftColumn.setBackground(new Color(62, 88, 121));
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        leftColumn.setBorder(BorderFactory.createLineBorder(new Color(216, 195, 182), 3));

        JLabel avatarLabel = new JLabel(new ImageIcon("path/to/avatar/image")); // Sostituisci con il percorso immagine
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnBack = createButton("Indietro");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchPage().setVisible(true);
                dispose(); // Chiude la finestra corrente
            }
        }); // Chiude questa finestra
        leftColumn.add(avatarLabel);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20))); // Spaziatura
        leftColumn.add(btnBack);

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

        JLabel lblPickupDate = new JLabel("Data di ritiro: " + pickupDay+"/"+pickupMonth+"/"+pickupYear);
        lblPickupDate.setForeground(new Color(216, 195, 182));
        lblPickupDate.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblReturnDate = new JLabel("Data di restituzione: " + returnDay+"/"+returnMonth+"/"+returnYear);
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

        // Aggiunta dei pannelli al layout principale
        mainPanel.add(leftColumn, BorderLayout.WEST);
        mainPanel.add(rightColumn, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(150, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	SearchCarList availableCarsPage = new SearchCarList(SearchPage.getMarca(), SearchPage.getModello(),SearchPage.getpickupDay(),SearchPage.getpickupMonth(), SearchPage.getpickupYear(), SearchPage.getreturnDay(),SearchPage.getreturnMonth(),SearchPage.getreturnYear());
            availableCarsPage.setVisible(true);
        });
    }
}
