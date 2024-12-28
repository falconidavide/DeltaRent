package Delta_Rent.Delta_Rent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

public class SearchPage extends JFrame {
	private JTextField textField_2;
	private JTextField textField_3;
	static Veicolo[] veicoli;

    public SearchPage() {
        // Impostazioni della finestra principale
        setTitle("DeltaRent - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 950);
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

        JButton btnFavorites = createButton("Preferiti");
        JButton btnSearch = createButton("Ricerca");
        JButton btnProfile = createButton("Profilo");
        btnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogIn().setVisible(true);
                dispose(); // Chiude la finestra corrente
            }
        });

        leftColumn.add(avatarLabel);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20))); // Spaziatura
        leftColumn.add(btnFavorites);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
        leftColumn.add(btnSearch);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
        leftColumn.add(btnProfile);
       

        // Colonna destra
        JPanel rightColumn = new JPanel();
        rightColumn.setBackground(new Color(32, 52, 85));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        // Pannello di ricerca
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(32, 52, 85));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        rightColumn.add(searchPanel);
        searchPanel.setLayout(null);
        
        JLabel lblSubtitle = new JLabel("Scegli la tua prossima auto da noleggiare");
        lblSubtitle.setForeground(new Color(216, 195, 182));
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 50));
        lblSubtitle.setAlignmentX(0.5f);
        lblSubtitle.setBounds(162, 31, 922, 59);
        searchPanel.add(lblSubtitle);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(62, 88, 121));
        panel.setBorder(new LineBorder(new Color(216, 195, 182), 3));
        panel.setBounds(119, 176, 922, 100);
        panel.setSize(900, 100);
        searchPanel.add(panel);
        panel.setLayout(null);
        
        JButton btnNewButton = new JButton("Mostra Auto");
        btnNewButton.setBounds(750, 25, 117, 45);
        panel.add(btnNewButton);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(359, 35, 130, 35);
        panel.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(530, 35, 130, 35);
        panel.add(textField_3);
        
        JLabel lblNewLabel = new JLabel("Marca");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblNewLabel.setBounds(31, 20, 61, 16);
        panel.add(lblNewLabel);
        
        JLabel lblModello = new JLabel("Modello");
        lblModello.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblModello.setBounds(183, 20, 93, 16);
        panel.add(lblModello);
        
        JLabel lblDataDiRitiro = new JLabel("Data di ritiro");
        lblDataDiRitiro.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblDataDiRitiro.setBounds(360, 20, 110, 16);
        panel.add(lblDataDiRitiro);
        
        JLabel lblDataDiRestituzione = new JLabel("Data di restituzione");
        lblDataDiRestituzione.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblDataDiRestituzione.setBounds(530, 20, 171, 16);
        panel.add(lblDataDiRestituzione);
        
        Choice choice = new Choice();
        choice.setBounds(28, 35, 117, 27);
        panel.add(choice);
        choice.add("...");
        choice.add("Audi");
        choice.add("volskwagen");
        choice.add("Lancia");
        choice.add("Fiat");
        choice.add("Mercedes-Benz");
        choice.add("Seat");
        choice.add("Skoda");
        choice.add("BMW");
        //aggiungere il throws exception per leggere ogni volta che cambia la prima selezione 
        if (choice.getSelectedItem()=="BMW") {
        	Choice choice_1 = new Choice();
            choice_1.setBounds(178, 35, 117, 27);
            panel.add(choice_1);
            choice_1.add("...");
            choice_1.add("X1");
            choice_1.add("X2");
            choice_1.add("X3");
            choice_1.add("X4");
            choice_1.add("X5");
            choice_1.add("X6");
            choice_1.add("X7");
        }
      

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	SearchPage homePage = new SearchPage();
            homePage.setVisible(true);
        });
       Veicolo BMWX52020 = new Veicolo(2020, "BMW", "X5", true, "Automobile");
        veicoli[0]= BMWX52020;
        
    }
}
