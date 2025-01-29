package gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class PaginaCaricamento extends JFrame {
	private static final long serialVersionUID = 1L;

	public PaginaCaricamento() {
	    setTitle("DeltaRent - Home");
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    setSize(1200, 950);
	    setMinimumSize(new Dimension(900, 700));
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setLocationRelativeTo(null);
	    ImageIcon appIcon = new ImageIcon("img/appIcon.png");
	    setIconImage(appIcon.getImage());

	    getContentPane().setBackground(new Color(220, 241, 255));
	    getContentPane().setLayout(new GridBagLayout()); // Usa GridBagLayout per centrare gli elementi

	    // Configura il layout manager
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = GridBagConstraints.RELATIVE;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(10, 0, 20, 0);

	    JLabel lblImage = new JLabel("");
	    lblImage.setIcon(new ImageIcon("./img/appIcon2.png"));
	    getContentPane().add(lblImage, gbc);

	    JLabel lblLoading = new JLabel("Caricamento in corso.");
	    lblLoading.setFont(new Font("Arial", Font.PLAIN, 20));
	    lblLoading.setForeground(Color.BLACK);
	    getContentPane().add(lblLoading, gbc);
	    
	    
	    // Timer per aggiornare il testo
	    Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	        private int dotCount = 0;

	        @Override
	        public void run() {
	            SwingUtilities.invokeLater(() -> {
	                dotCount = (dotCount % 3) + 1; // Incrementa i punti ciclicamente da 1 a 3
	                String dots = ".".repeat(dotCount); // Crea una stringa con il numero appropriato di punti
	                lblLoading.setText("Caricamento in corso" + dots);
	            });
	        }
	    }, 0, 500); // Aggiorna ogni 500 millisecondi (mezzo secondo)
	}


}
