package gui;

import javax.swing.*;
import java.awt.*;

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
	
	//pannello di caricamento
	
	JPanel pan1=new JPanel();
	pan1.setBackground(getForeground());
	getContentPane().setBackground(new Color(220, 241, 255));
	getContentPane().setLayout(new BorderLayout(0, 0));
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(new ImageIcon("/Users/andreabirolini/Documents/GitHub/DeltaRent/DeltaRent/img/appIcon2.png"));
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	getContentPane().add(lblNewLabel, BorderLayout.CENTER);
	}
	
}
