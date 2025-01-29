package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import db.DatabaseConnection;
import utente.Utente;
import util.TotaleNoleggi;
import util.Validatori;

public class GestioneAccount extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField txtNewEmail;
	private JTextField txtCurrentEmail;
    private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;
    private JButton btnUpdateEmail;
	private JButton btnChangePassword;
    private Connection connection;

    public GestioneAccount() {
        setLayout(new BorderLayout());
        setBackground(new Color(32, 52, 85));

        // Pannello di intestazione
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Pannello di visualizzazione dei dati utente
        JPanel userPanel = createUserPanel();
        if (userPanel != null) {
            add(userPanel, BorderLayout.CENTER);
        }

        // Pannello di modifica dell'email e della password
        JPanel centerPanel = createCenterPanel();
        JScrollPane scrollPanel = new JScrollPane(centerPanel);
        scrollPanel.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPanel, BorderLayout.SOUTH);

        this.connection = DatabaseConnection.getConnection();
    }

    // Crea il pannello di intestazione
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(32, 52, 85));
        JLabel lblHeader = new JLabel("Gestione Account");
        lblHeader.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
        lblHeader.setForeground(new Color(236, 240, 241));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Aggiungi margini per distanziamento
        headerPanel.add(lblHeader);
        return headerPanel;
    }

    // Crea la sezione di visualizzazione dei dati utente
    private JPanel createUserPanel() {
        if (HomePage.loggedUser == null) {
            return null;
        }

        JPanel userPanel = new JPanel(new GridBagLayout());
        userPanel.setBackground(new Color(32, 52, 85));
        //userPanel.setBorder(new LineBorder(new Color(236, 240, 241), 2, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Aumenta gli spazi interni
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        Boolean isPrivato = Utente.getIsPrivato();
        if (isPrivato) {
            addUserInfo(userPanel, "Nome", HomePage.loggedUser.getNome(), gbc);
            gbc.gridy++;
            addUserInfo(userPanel, "Cognome", HomePage.loggedUser.getCognome(), gbc);
            gbc.gridy++;
            addUserInfo(userPanel, "Data di Nascita", HomePage.loggedUser.getDataDiNascita(), gbc);
        } else {
            addUserInfo(userPanel, "Nome Azienda", HomePage.loggedUser.getNomeAzienda(), gbc);
            gbc.gridy++;
            addUserInfo(userPanel, "Partita IVA", HomePage.loggedUser.getPartitaIVA(), gbc);
        }

        gbc.gridy++;
        
        String utenza;
        if(Utente.getIsPrivato()) {
        	utenza="Privato";
        }else {
        	utenza="Aziendale";
        }
        
        addUserInfo(userPanel, "Tipo Utenza", utenza, gbc);
        gbc.gridy++;
        addUserInfo(userPanel, "Totale Veicoli Noleggiati", Integer.toString(TotaleNoleggi.getTotaleNoleggi(HomePage.loggedUser)), gbc);

        return userPanel;
    }

    // Metodo di utilità per aggiungere informazioni utente al pannello
    private void addUserInfo(JPanel panel, String label, String value, GridBagConstraints gbc) {
        JLabel lbl = new JLabel(label + ": " + value);
        lbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        lbl.setForeground(new Color(236, 240, 241));
        panel.add(lbl, gbc);
    }

    // Crea il pannello centrale che contiene la modifica dell'email e della password
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(32, 52, 85));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Aggiungi margini per distanziamento

        JPanel emailPanel = createEmailPanel();
        JPanel passwordPanel = createPasswordPanel();

        centerPanel.add(emailPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spazio tra i pannelli
        centerPanel.add(passwordPanel);

        return centerPanel;
    }

    // Crea la sezione di modifica email
    private JPanel createEmailPanel() {
        JPanel emailPanel = new JPanel(new GridBagLayout());
        emailPanel.setBackground(new Color(32, 52, 85));
        emailPanel.setBorder(new LineBorder(new Color(236, 240, 241), 2, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblEmail = new JLabel("Email Corrente:");
        lblEmail.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblEmail.setForeground(new Color(236, 240, 241));
        emailPanel.add(lblEmail, gbc);

        txtCurrentEmail = new JTextField(HomePage.loggedUser.getEmail());
        txtCurrentEmail.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        txtCurrentEmail.setBackground(new Color(245, 239, 231));
        txtCurrentEmail.setBorder(new RoundedBorder(10));
        txtCurrentEmail.setEditable(false);
        txtCurrentEmail.getDocument().addDocumentListener(emailListener);
        gbc.gridx = 1;
        emailPanel.add(txtCurrentEmail, gbc);

        JLabel lblNewEmail = new JLabel("Nuova Email:");
        lblNewEmail.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblNewEmail.setForeground(new Color(236, 240, 241));
        gbc.gridx = 0;
        gbc.gridy = 1;
        emailPanel.add(lblNewEmail, gbc);

        txtNewEmail = new JTextField();
        txtNewEmail.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        txtNewEmail.setBackground(new Color(245, 239, 231));
        txtNewEmail.setBorder(new RoundedBorder(10));
        txtNewEmail.getDocument().addDocumentListener(emailListener);
        gbc.gridx = 1;
        emailPanel.add(txtNewEmail, gbc);

        btnUpdateEmail = new JButton("Aggiorna Email");
        btnUpdateEmail.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        btnUpdateEmail.setBackground(new Color(52, 152, 219));
        btnUpdateEmail.setForeground(new Color(255, 255, 255));
        btnUpdateEmail.setFocusPainted(false);
        btnUpdateEmail.setBorder(new RoundedBorder(10));
        btnUpdateEmail.addActionListener(e -> updateEmail());
        btnUpdateEmail.setEnabled(false);
        btnUpdateEmail.setBorder(null);
        btnUpdateEmail.setPreferredSize(new Dimension(150, 25));
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        emailPanel.add(btnUpdateEmail, gbc);

        return emailPanel;
    }

    // Crea la sezione di modifica password
    private JPanel createPasswordPanel() {
        JPanel passwordPanel = new JPanel(new GridBagLayout());
        passwordPanel.setBackground(new Color(32, 52, 85));
        passwordPanel.setBorder(new LineBorder(new Color(236, 240, 241), 2, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblOldPassword = new JLabel("Password Attuale:");
        lblOldPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblOldPassword.setForeground(new Color(236, 240, 241));
        passwordPanel.add(lblOldPassword, gbc);

        txtOldPassword = new JPasswordField();
        txtOldPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        txtOldPassword.setBackground(new Color(245, 239, 231));
        txtOldPassword.setBorder(new RoundedBorder(10));
        txtOldPassword.getDocument().addDocumentListener(passwordListener);
        gbc.gridx = 1;
        passwordPanel.add(txtOldPassword, gbc);

        JLabel lblNewPassword = new JLabel("Nuova Password:");
        lblNewPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblNewPassword.setForeground(new Color(236, 240, 241));
        gbc.gridx = 0;
        gbc.gridy = 1;
        passwordPanel.add(lblNewPassword, gbc);

        txtNewPassword = new JPasswordField();
        txtNewPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        txtNewPassword.setBackground(new Color(245, 239, 231));
        txtNewPassword.setBorder(new RoundedBorder(10));
        txtNewPassword.getDocument().addDocumentListener(passwordListener);
        gbc.gridx = 1;
        passwordPanel.add(txtNewPassword, gbc);

        btnChangePassword = new JButton("Cambia Password");
        btnChangePassword.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        btnChangePassword.setBackground(new Color(52, 152, 219));
        btnChangePassword.setForeground(new Color(255, 255, 255));
        btnChangePassword.setFocusPainted(false);
        btnChangePassword.setBorder(new RoundedBorder(10));
        btnChangePassword.setEnabled(false);
        btnChangePassword.setBorder(null);
        btnChangePassword.setPreferredSize(new Dimension(150, 25));
        btnChangePassword.addActionListener(e -> changePassword());
        gbc.gridx = 1;
        gbc.gridy = 2;
        passwordPanel.add(btnChangePassword, gbc);
        
        /*
        txtNewPassword.addActionListener(e -> {
        	System.out.println(String.valueOf( txtNewPassword.getPassword()));
        	System.out.println(txtNewPassword.getPassword());
			if(Validatori.isValidPassword( String.valueOf( txtNewPassword.getPassword() ) )) {
				btnChangePassword.setEnabled(true);
			} else {
				btnChangePassword.setEnabled(false);
			}
		});
        txtOldPassword.addActionListener(e -> {
			if(Validatori.isValidPassword( String.valueOf( txtNewPassword.getPassword() ) )) {
				btnChangePassword.setEnabled(true);
			} else {
				btnChangePassword.setEnabled(false);
			}
		});
		*/

        return passwordPanel;
    }

    // Metodo per aggiornare email
    private void updateEmail() {
        String newEmail = txtNewEmail.getText();
        String currentEmail = txtCurrentEmail.getText();

        if (newEmail.isEmpty() || currentEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Per favore, compila tutti i campi.", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE Utente SET email = ? WHERE email = ?")) {
                ps.setString(1, newEmail);
                ps.setString(2, currentEmail);
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Email aggiornata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    HomePage.loggedUser.setEmail(newEmail); // Aggiorna l'email dell'utente loggato
                } else {
                    JOptionPane.showMessageDialog(this, "Errore durante l'aggiornamento dell'email.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } 
            catch (SQLException ex) {
            	if (ex.getMessage().contains("UNIQUE") || ex.getMessage().contains("PRIMARY KEY")) {
            		JOptionPane.showMessageDialog(this, "Email già registrata.", "Errore", JOptionPane.ERROR_MESSAGE);
                } else {
                	JOptionPane.showMessageDialog(this, "Errore nel database." + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Metodo per cambiare la password
    private void changePassword() {
        String oldPassword = new String(txtOldPassword.getPassword());
        String newPassword = new String(txtNewPassword.getPassword());

        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Per favore, compila tutti i campi.", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                // Verifica la vecchia password
                String query = "SELECT password FROM Utente WHERE email = ?";
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    ps.setString(1, HomePage.loggedUser.getEmail());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        String currentPassword = rs.getString("password");
                        if (!currentPassword.equals(oldPassword)) {
                            JOptionPane.showMessageDialog(this, "La vecchia password non è corretta.", "Errore", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Utente non trovato.", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Aggiorna la password
                String updateQuery = "UPDATE Utente SET password = ? WHERE email = ?";
                try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
                    ps.setString(1, newPassword);
                    ps.setString(2, HomePage.loggedUser.getEmail());
                    int rowsUpdated = ps.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Password aggiornata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Errore durante il cambio password.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Errore nel database.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Classe per bordi arrotondati
    private static class RoundedBorder extends LineBorder {
        private static final long serialVersionUID = 1L;
        private int radius;

        public RoundedBorder(int radius) {
            super(Color.WHITE, 2, true);
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(lineColor);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
    
	DocumentListener emailListener = new DocumentListener() {
        @Override
		public void insertUpdate(DocumentEvent e) {
        	updateEmailButton();
        }

        @Override
		public void removeUpdate(DocumentEvent e) {
        	updateEmailButton();
        }

        @Override
		public void changedUpdate(DocumentEvent e) {
        	updateEmailButton();
        }

        public void updateEmailButton() {
			if(Validatori.isValidEmail(txtNewEmail.getText()) && !"".equals(txtCurrentEmail.getText())) {
				btnUpdateEmail.setEnabled(true);
			} else {
				btnUpdateEmail.setEnabled(false);
			}
        }
    };
	DocumentListener passwordListener = new DocumentListener() {
        @Override
		public void insertUpdate(DocumentEvent e) {
            updatePasswordButton();
        }

        @Override
		public void removeUpdate(DocumentEvent e) {
        	updatePasswordButton();
        }

        @Override
		public void changedUpdate(DocumentEvent e) {
        	updatePasswordButton();
        }

        public void updatePasswordButton() {
			if(Validatori.isValidPassword( String.valueOf(txtNewPassword.getPassword())) && !"".equals(String.valueOf(txtOldPassword.getPassword()))) {
				btnChangePassword.setEnabled(true);
			} else {
				btnChangePassword.setEnabled(false);
			}
        }
    };
}