package GUI;

import javax.swing.*;
import java.awt.*;

public class Prenotazione extends JFrame {
    public Prenotazione() {
        setTitle("DeltaRent - Prenotazione");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Pagina Prenotazione", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);

        getContentPane().add(panel);
    }
}
