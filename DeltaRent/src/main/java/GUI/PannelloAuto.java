package GUI;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;

public class PannelloAuto extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public PannelloAuto() {

        setBackground(new Color(60, 87, 121));
        setSize(500, 300);
        setLayout(null);

        JLabel Modello = new JLabel("Modello");
        Modello.setBounds(6, 6, 488, 58);
        add(Modello);

        JLabel Anno = new JLabel("Anno");
        Anno.setBounds(297, 102, 197, 33);
        add(Anno);

        JLabel Potenza = new JLabel("Potenza");
        Potenza.setBounds(297, 147, 197, 33);
        add(Potenza);

        JLabel Alimentazione = new JLabel("Alimentazione");
        Alimentazione.setBounds(297, 192, 197, 33);
        add(Alimentazione);

        JLabel PrezzoAuto = new JLabel("Prezzo");
        PrezzoAuto.setBounds(297, 237, 197, 33);
        add(PrezzoAuto);

        JLabel immagineAuto = new JLabel("");
        immagineAuto.setBounds(6, 76, 279, 218);

        // Carica l'immagine
        immagineAuto.setIcon(new ImageIcon("audi_a7.jpg"));
        add(immagineAuto);
        ImageIcon originalIcon = new ImageIcon("audi_a7.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(279, 218, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        immagineAuto.setIcon(scaledIcon);

    }
}
