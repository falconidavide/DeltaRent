package GUI;

import javax.swing.*;
import java.awt.*;

public class GestioneAccount extends JFrame {
    public GestioneAccount() {
        setTitle("DeltaRent - Gestione Account");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Pagina Gestione Account", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);

        getContentPane().add(panel);
    }
}
