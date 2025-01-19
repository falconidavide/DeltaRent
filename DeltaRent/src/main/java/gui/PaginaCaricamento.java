package gui;


import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class PaginaCaricamento extends JFrame {

    public PaginaCaricamento() {

        setTitle("DeltaRent - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 950);
        setMinimumSize(new Dimension(900, 700));
        setLocationRelativeTo(null);
        ImageIcon appIcon = new ImageIcon("img/appIcon.png"); // Sostituisci con il percorso corretto
        setIconImage(appIcon.getImage());

        // Pannello di caricamento
        JPanel pan1 = new JPanel();
        pan1.setBackground(getForeground());
        getContentPane().setBackground(new Color(220, 241, 255));
        getContentPane().setLayout(new BorderLayout(0, 0));

        // Etichetta con immagine
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("/Users/andreabirolini/Documents/GitHub/DeltaRent/DeltaRent/img/appIcon2.png"));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNewLabel, BorderLayout.CENTER);

        // Barra di caricamento
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setEnabled(false);
        progressBar.setForeground(new Color(20, 68, 136));
        progressBar.setPreferredSize(new Dimension(150, 20));
        progressBar.setBorderPainted(false);
        progressBar.setBackground(new Color(220, 241, 255));

        // Pannello per centrare la barra sotto l'immagine
        JPanel loadingPanel = new JPanel();
        loadingPanel.setBackground(new Color(220, 241, 255));
        loadingPanel.add(progressBar);

        getContentPane().add(loadingPanel, BorderLayout.SOUTH);

        // Timer per aggiornare la barra di progresso
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int progress = 0;

            @Override
            public void run() {
                if (progress <= 100) {
                    progressBar.setValue(progress);
                    progress += 3; // Incremento progressivo
                } else {
                    timer.cancel();
                }
            }
        }, 0, 70); // Aggiornamenti ogni 70 millisecondi per completare in 0,7 secondi
    }
}
