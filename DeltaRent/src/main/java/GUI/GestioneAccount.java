package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import DB.DatabaseConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GestioneAccount extends JPanel {

    private JTextField txtNewEmail, txtCurrentEmail;
    private JPasswordField txtOldPassword, txtNewPassword;
    private JButton btnUpdateEmail, btnChangePassword;
    private Connection connection;

    public GestioneAccount() {
        setLayout(new BorderLayout());
        setBackground(new Color(32, 52, 85));

        // Pannello di intestazione
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(44, 62, 80));
        JLabel lblHeader = new JLabel("Gestione Account");
        lblHeader.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
        lblHeader.setForeground(new Color(236, 240, 241));
        headerPanel.add(lblHeader);
        add(headerPanel, BorderLayout.NORTH);

        // Pannello di visualizzazione dei dati utente
        JPanel userPanel = createUserPanel();
        if (userPanel != null) {
            add(userPanel, BorderLayout.SOUTH);
        }

        // Pannello di modifica dell'email
        JPanel emailPanel = createEmailPanel();
        // Pannello di modifica della password
        JPanel passwordPanel = createPasswordPanel();

        // Aggiunta pannelli alla parte centrale
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(32, 52, 85));
        centerPanel.add(emailPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(passwordPanel);

        JScrollPane scrollPanel = new JScrollPane(centerPanel);
        add(scrollPanel, BorderLayout.CENTER);

        this.connection = DatabaseConnection.getConnection();
    }

    // Crea la sezione di visualizzazione dei dati utente
    private JPanel createUserPanel() {
        if (HomePage.loggedUser == null) {
            System.out.println("Errore: loggedUser è null. L'utente deve essere loggato per visualizzare il pannello di gestione account.");
            return null;
        }

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(0, 1));
        userPanel.setBackground(new Color(44, 62, 80));
        userPanel.setBorder(new LineBorder(new Color(236, 240, 241), 2, true));
     
        Boolean isPrivato = HomePage.loggedUser.getIsPrivato();
        if (isPrivato) {
            addUserInfo(userPanel, "Nome", HomePage.loggedUser.getNome());
            addUserInfo(userPanel, "Cognome", HomePage.loggedUser.getCognome());
            addUserInfo(userPanel, "Data di Nascita", HomePage.loggedUser.getDataDiNascita());
        } else {
            addUserInfo(userPanel, "Nome Azienda", HomePage.loggedUser.getNomeAzienda());
            addUserInfo(userPanel, "Partita IVA", HomePage.loggedUser.getPartitaIVA());
        }

        return userPanel;
    }

    // Metodo di utilità per aggiungere informazioni utente al pannello
    private void addUserInfo(JPanel panel, String label, String value) {
        JLabel lbl = new JLabel(label + ": " + value);
        lbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lbl.setForeground(new Color(236, 240, 241));
        panel.add(lbl);
    }

    // Crea la sezione di modifica email
    private JPanel createEmailPanel() {
        JPanel emailPanel = new JPanel(new GridBagLayout());
        emailPanel.setBackground(new Color(44, 62, 80));
        emailPanel.setBorder(new LineBorder(new Color(236, 240, 241), 2, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblEmail = new JLabel("Email Corrente:");
        lblEmail.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblEmail.setForeground(new Color(236, 240, 241));
        gbc.gridx = 0;
        gbc.gridy = 0;
        emailPanel.add(lblEmail, gbc);

        txtCurrentEmail = new JTextField();
        txtCurrentEmail.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        txtCurrentEmail.setBackground(new Color(245, 239, 231));
        txtCurrentEmail.setBorder(new RoundedBorder(10));
        gbc.gridx = 1;
        gbc.gridy = 0;
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
        gbc.gridx = 1;
        gbc.gridy = 1;
        emailPanel.add(txtNewEmail, gbc);

        btnUpdateEmail = new JButton("Aggiorna Email");
        btnUpdateEmail.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        btnUpdateEmail.setBackground(new Color(52, 152, 219));
        btnUpdateEmail.setForeground(new Color(255, 255, 255));
        btnUpdateEmail.setFocusPainted(false);
        btnUpdateEmail.setBorder(new RoundedBorder(10));
        btnUpdateEmail.addActionListener(e -> updateEmail());
        gbc.gridx = 1;
        gbc.gridy = 2;
        emailPanel.add(btnUpdateEmail, gbc);

        return emailPanel;
    }

    // Crea la sezione di modifica password
    private JPanel createPasswordPanel() {
        JPanel passwordPanel = new JPanel(new GridBagLayout());
        passwordPanel.setBackground(new Color(44, 62, 80));
        passwordPanel.setBorder(new LineBorder(new Color(236, 240, 241), 2, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblOldPassword = new JLabel("Password Attuale:");
        lblOldPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        lblOldPassword.setForeground(new Color(236, 240, 241));
        gbc.gridx = 0;
        gbc.gridy = 0;
        passwordPanel.add(lblOldPassword, gbc);

        txtOldPassword = new JPasswordField();
        txtOldPassword.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        txtOldPassword.setBackground(new Color(245, 239, 231));
        txtOldPassword.setBorder(new RoundedBorder(10));
        gbc.gridx = 1;
        gbc.gridy = 0;
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
        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordPanel.add(txtNewPassword, gbc);

        btnChangePassword = new JButton("Cambia Password");
        btnChangePassword.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        btnChangePassword.setBackground(new Color(52, 152, 219));
        btnChangePassword.setForeground(new Color(255, 255, 255));
        btnChangePassword.setFocusPainted(false);
        btnChangePassword.setBorder(new RoundedBorder(10));
        btnChangePassword.addActionListener(e -> changePassword());
        gbc.gridx = 1;
        gbc.gridy = 2;
        passwordPanel.add(btnChangePassword, gbc);

        return passwordPanel;
    }

    // Metodo per aggiornare email
    private void updateEmail() {
        String newEmail = txtNewEmail.getText();
        String currentEmail = txtCurrentEmail.getText();

        if (newEmail.isEmpty() || currentEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Per favore, compila tutti i campi.", "Errore",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE Utente SET email = ? WHERE email = ?")) {
                ps.setString(1, newEmail);
                ps.setString(2, currentEmail);
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Email aggiornata con successo!", "Successo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Errore durante l'aggiornamento dell'email.", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Errore nel database.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Metodo per cambiare la password
    private void changePassword() {
        String oldPassword = new String(txtOldPassword.getPassword());
        String newPassword = new String(txtNewPassword.getPassword());

        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Per favore, compila tutti i campi.", "Errore",
                    JOptionPane.ERROR_MESSAGE);
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
                            JOptionPane.showMessageDialog(this, "La vecchia password non è corretta.", "Errore",
                                    JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(this, "Password aggiornata con successo!", "Successo",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Errore durante il cambio password.", "Errore",
                                JOptionPane.ERROR_MESSAGE);
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
}