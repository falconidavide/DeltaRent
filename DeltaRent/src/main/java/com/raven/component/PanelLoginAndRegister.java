package com.raven.component;

import db.Registration;
import gui.HomePage;
import gui.SearchPage;
import my.components.Button;
import my.components.MyPasswordField;
import my.components.MyTextField;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import utente.Utente;
import util.Validatori;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {
    private static final long serialVersionUID = 1L;
    private MyTextField txtNomeAzienda = new MyTextField();
    private MyTextField txtPartitaIVA = new MyTextField();
    private MyTextField txtEmailAzienda = new MyTextField();
    private MyPasswordField txtPasswordAzienda = new MyPasswordField();
    private MyTextField txtNome = new MyTextField();
    private MyTextField txtCognome = new MyTextField();
    private MyTextField txtDataNascita = new MyTextField();
    private MyTextField txtEmailPrivato = new MyTextField();
    private MyPasswordField txtPasswordPrivato = new MyPasswordField();
    private Button cmdAzienda = new Button();
    private Button cmdPrivato = new Button();
    private MyTextField txtEmailLogin = new MyTextField(); // Campo email nel pannello di login
    private MyPasswordField txtPasswordLogin = new MyPasswordField(); // Campo password nel pannello di login

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);

        DocumentListener aziendaListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateAziendaButton();
            }

            public void removeUpdate(DocumentEvent e) {
                updateAziendaButton();
            }

            public void changedUpdate(DocumentEvent e) {
                updateAziendaButton();
            }

            public void updateAziendaButton() {
                if (areAllFieldsFilledForAzienda()) {
                    cmdAzienda.setEnabled(true);
                } else {
                    cmdAzienda.setEnabled(false);
                }
            }
        };

        DocumentListener privatoListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updatePrivatoButton();
            }

            public void removeUpdate(DocumentEvent e) {
                updatePrivatoButton();
            }

            public void changedUpdate(DocumentEvent e) {
                updatePrivatoButton();
            }

            public void updatePrivatoButton() {
                if (areAllFieldsFilledForPrivato()) {
                    cmdPrivato.setEnabled(true);
                } else {
                    cmdPrivato.setEnabled(false);
                }
            }
        };

        txtNomeAzienda.getDocument().addDocumentListener(aziendaListener);
        txtPartitaIVA.getDocument().addDocumentListener(aziendaListener);
        txtEmailAzienda.getDocument().addDocumentListener(aziendaListener);
        txtPasswordAzienda.getDocument().addDocumentListener(aziendaListener);

        txtNome.getDocument().addDocumentListener(privatoListener);
        txtCognome.getDocument().addDocumentListener(privatoListener);
        txtDataNascita.getDocument().addDocumentListener(privatoListener);
        txtEmailPrivato.getDocument().addDocumentListener(privatoListener);
        txtPasswordPrivato.getDocument().addDocumentListener(privatoListener);
    }

    // Metodo per verificare se tutti i campi sono riempiti
    private boolean areAllFieldsFilledForAzienda() {
        return !txtNomeAzienda.getText().trim().isEmpty() && Validatori.isValidPIVA(txtPartitaIVA.getText().trim())
                && Validatori.isValidEmail(txtEmailAzienda.getText().trim()) && Validatori.isValidPassword(new String(txtPasswordAzienda.getPassword()).trim());
    }

    private boolean areAllFieldsFilledForPrivato() {
        return !txtNome.getText().trim().isEmpty() && !txtCognome.getText().trim().isEmpty()
                && Validatori.isValidDate(txtDataNascita.getText().trim()) && Validatori.isValidEmail(txtEmailPrivato.getText().trim())
                && Validatori.isValidPassword(new String(txtPasswordPrivato.getPassword()).trim());
    }

    // Metodo per impostare l'email nel campo di login
    public void setEmailLogin(String email) {
        txtEmailLogin.setText(email);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]push"));
        register.setBackground(new Color(250, 250, 250));
        JLabel label = new JLabel("Crea un Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(45, 64, 98));
        register.add(label);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(45, 64, 98));
        tabbedPane.addTab("Azienda", createAziendaPanel());
        tabbedPane.addTab("Privato", createPrivatoPanel());
        tabbedPane.setBackgroundAt(0, new Color(200, 200, 200));
        tabbedPane.setBackgroundAt(1, new Color(200, 200, 200));

        register.add(tabbedPane, "w 80%, h 80%");
    }

    private JPanel createAziendaPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]push"));

        txtNomeAzienda.setHint("Nome Azienda");
        txtNomeAzienda.setBackground(new Color(135, 143, 170));
        txtNomeAzienda.setForeground(new Color(250, 250, 250));
        panel.add(txtNomeAzienda, "w 60%");

        txtPartitaIVA.setHint("Partita IVA");
        txtPartitaIVA.setBackground(new Color(135, 143, 170));
        txtPartitaIVA.setForeground(new Color(250, 250, 250));
        panel.add(txtPartitaIVA, "w 60%");

        txtEmailAzienda.setHint("Email");
        txtEmailAzienda.setBackground(new Color(135, 143, 170));
        txtEmailAzienda.setForeground(new Color(250, 250, 250));
        panel.add(txtEmailAzienda, "w 60%");

        txtPasswordAzienda.setHint("Password");
        txtPasswordAzienda.setBackground(new Color(135, 143, 170));
        txtPasswordAzienda.setForeground(new Color(250, 250, 250));
        panel.add(txtPasswordAzienda, "w 60%");
        txtPasswordAzienda.setToolTipText("<html>- Almeno 8 caratteri<br>- Almeno 1 maiuscolo ed 1 minuscolo<br>- Almeno un numero<br>- Almeno un carattere speciale<html>");

        cmdAzienda.setBackground(new Color(45, 64, 98));
        cmdAzienda.setForeground(new Color(250, 250, 250));
        cmdAzienda.setText("ISCRIVITI");
        cmdAzienda.setEnabled(false);
        panel.add(cmdAzienda, "w 40%, h 40");

        ActionListener lAzienda = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmailAzienda.getText();
                String password = new String(txtPasswordAzienda.getPassword());
                String nomeAzienda = txtNomeAzienda.getText();
                String partitaIva = txtPartitaIVA.getText();

                if (Registration.registerUser(email, password, null, null, null, nomeAzienda, partitaIva)) {
                    JOptionPane.showMessageDialog(PanelLoginAndRegister.this, "Registrazione completata.", "Registrazione", JOptionPane.INFORMATION_MESSAGE);
                    txtEmailAzienda.setText("");
                    txtPasswordAzienda.setText("");
                    txtNomeAzienda.setText("");
                    txtPartitaIVA.setText("");

                    // Passa al pannello di login e imposta l'email
                    setEmailLogin(email);
                    firePropertyChange("switchToLogin", false, true);
                } else {
                    JOptionPane.showMessageDialog(PanelLoginAndRegister.this, "Errore durante la registrazione.", "Errore di registrazione", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        cmdAzienda.addActionListener(lAzienda);

        return panel;
    }

    private JPanel createPrivatoPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]10[]push"));

        txtNome.setBackground(new Color(135, 143, 170));
        txtNome.setForeground(new Color(250, 250, 250));
        txtNome.setHint("Nome");
        panel.add(txtNome, "w 60%");

        txtCognome.setBackground(new Color(135, 143, 170));
        txtCognome.setForeground(new Color(250, 250, 250));
        txtCognome.setHint("Cognome");
        panel.add(txtCognome, "w 60%");

        txtDataNascita.setBackground(new Color(135, 143, 170));
        txtDataNascita.setForeground(new Color(250, 250, 250));
        txtDataNascita.setHint("Data di Nascita");
        panel.add(txtDataNascita, "w 60%");
        txtDataNascita.setToolTipText("Formato: GG/MM/AAAA");

        txtEmailPrivato.setBackground(new Color(135, 143, 170));
        txtEmailPrivato.setForeground(new Color(250, 250, 250));
        txtEmailPrivato.setHint("Email");
        panel.add(txtEmailPrivato, "w 60%");

        txtPasswordPrivato.setBackground(new Color(135, 143, 170));
        txtPasswordPrivato.setForeground(new Color(250, 250, 250));
        txtPasswordPrivato.setHint("Password");
        panel.add(txtPasswordPrivato, "w 60%");
        txtPasswordPrivato.setToolTipText("<html>- Almeno 8 caratteri<br>- Almeno 1 maiuscolo ed 1 minuscolo<br>- Almeno un numero<br>- Almeno un carattere speciale<html>");

        cmdPrivato.setBackground(new Color(45, 64, 98));
        cmdPrivato.setForeground(new Color(250, 250, 250));
        cmdPrivato.setText("ISCRIVITI");
        cmdPrivato.setEnabled(false);
        panel.add(cmdPrivato, "w 40%, h 40");

        ActionListener lPrivato = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmailPrivato.getText();
                String password = new String(txtPasswordPrivato.getPassword());
                String nome = txtNome.getText();
                String cognome = txtCognome.getText();
                String dataNascita = txtDataNascita.getText();

                if (Registration.registerUser(email, password, nome, cognome, dataNascita, null, null)) {
                    JOptionPane.showMessageDialog(PanelLoginAndRegister.this, "Registrazione completata.", "Registrazione", JOptionPane.INFORMATION_MESSAGE);
                    txtEmailPrivato.setText("");
                    txtPasswordPrivato.setText("");
                    txtNome.setText("");
                    txtCognome.setText("");
                    txtDataNascita.setText("");

                    // Passa al pannello di login e imposta l'email
                    setEmailLogin(email);
                    firePropertyChange("switchToLogin", false, true);
                } else {
                    JOptionPane.showMessageDialog(PanelLoginAndRegister.this, "Errore durante la registrazione.", "Errore di registrazione", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        cmdPrivato.addActionListener(lPrivato);

        return panel;
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Accedi");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(45, 64, 98));
        login.add(label);

        txtEmailLogin.setHint("Email");
        txtEmailLogin.setBackground(new Color(135, 143, 170));
        txtEmailLogin.setForeground(new Color(250, 250, 250));
        login.add(txtEmailLogin, "w 60%");

        txtPasswordLogin.setHint("Password");
        txtPasswordLogin.setBackground(new Color(135, 143, 170));
        txtPasswordLogin.setForeground(new Color(250, 250, 250));
        login.add(txtPasswordLogin, "w 60%");

        Button cmd = new Button();
        cmd.setBackground(new Color(45, 64, 98));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("ACCEDI");
        login.add(cmd, "w 40%, h 40");

        cmd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmailLogin.getText();
                String password = new String(txtPasswordLogin.getPassword());
                Utente user = Utente.getUserByEmailAndPassword(email, password);
                if (user != null) {
                    txtEmailLogin.setText("");
                    txtPasswordLogin.setText("");
                    HomePage.loggedUser = user;
                    HomePage.logged = true;
                    HomePage.btnLogout.setVisible(true);
                    SearchPage.mostraVeicoli();
                    HomePage.aggiornaMessaggioBenvenuto();
                    HomePage.cardLayout.show(HomePage.mainContentPanel, "home");
                } else {
                    JOptionPane.showMessageDialog(PanelLoginAndRegister.this, "Credenziali errate.", "Errore di accesso", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}