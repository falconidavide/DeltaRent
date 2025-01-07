package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GestioneAccount extends JPanel {

    public GestioneAccount() {
        // Impostazioni del pannello
        setBackground(new Color(245, 239, 231));
        setLayout(new BorderLayout());

        // Etichetta del pannello
        JLabel lblAccount = new JLabel("Gestione Account");
        lblAccount.setFont(new Font("Arial", Font.BOLD, 36));
        lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
        lblAccount.setForeground(new Color(32, 52, 85));

        // Aggiungi ulteriori componenti per la gestione dell'account, come pulsanti o campi di testo

        // Aggiunta dell'etichetta al pannello
        add(lblAccount, BorderLayout.CENTER);
    }
}
