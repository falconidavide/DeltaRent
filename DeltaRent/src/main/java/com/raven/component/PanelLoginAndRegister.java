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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import utente.Utente;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {
	
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
	    return !txtNomeAzienda.getText().trim().isEmpty() && isValidPIVA(txtPartitaIVA.getText().trim())
	            && isValidEmail(txtEmailAzienda.getText().trim()) && isValidPassword(new String(txtPasswordAzienda.getPassword()).trim());
	}

	private boolean areAllFieldsFilledForPrivato() {
	    return !txtNome.getText().trim().isEmpty() && !txtCognome.getText().trim().isEmpty()
	            && isValidDate(txtDataNascita.getText().trim()) && isValidEmail(txtEmailPrivato.getText().trim())
	            && isValidPassword(new String(txtPasswordPrivato.getPassword()).trim());
	}

	// Metodo per validare l'email
	public static boolean isValidEmail(String email) {
	    return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
	}

	// Metodo per validare la password
	public static boolean isValidPassword(String password) {
	    return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
	}

	// Metodo per validare la partita IVA
	public static boolean isValidPIVA(String PIVA) {
	    return PIVA.matches("^[0-9]{11}$");
	}

	// Metodo per validare la data
	public static boolean isValidDate(String date) {
	    return date.matches("(0[1-9]|[12][0-9]|3[01])[\\/](0[1-9]|1[012])[\\/](19|20)\\d\\d");
	}

	private void initRegister() {
		register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]push"));
		register.setBackground(new Color(250,250,250));
		JLabel label = new JLabel("Crea un Account");
		label.setFont(new Font("sansserif", 1, 30));
		label.setForeground(new Color(45, 64, 98));
		register.add(label);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(new Color(45, 64, 98));
		tabbedPane.addTab("Azienda", createAziendaPanel());
		tabbedPane.addTab("Privato", createPrivatoPanel());

		register.add(tabbedPane, "w 80%, h 80%");
	}

	private JPanel createAziendaPanel() {
		
		JPanel panel = new JPanel(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]push"));
			
		txtNomeAzienda.setPrefixIcon(new ImageIcon("img/user.png"));
		txtNomeAzienda.setHint("Nome Azienda");
		txtNomeAzienda.setBackground(new Color(135, 143, 170));
		txtNomeAzienda.setForeground(new Color(250, 250, 250));
		panel.add(txtNomeAzienda, "w 60%");

		//txtPartitaIVA.setPrefixIcon(new ImageIcon("img/piva.png"));
		txtPartitaIVA.setHint("Partita IVA");
		txtPartitaIVA.setBackground(new Color(135, 143, 170));
		txtPartitaIVA.setForeground(new Color(250, 250, 250));
		panel.add(txtPartitaIVA, "w 60%");

		txtEmailAzienda.setPrefixIcon(new ImageIcon("img/mail.png"));
		txtEmailAzienda.setHint("Email");
		txtEmailAzienda.setBackground(new Color(135, 143, 170));
		txtEmailAzienda.setForeground(new Color(250, 250, 250));
		panel.add(txtEmailAzienda, "w 60%");

		txtPasswordAzienda.setPrefixIcon(new ImageIcon("img/pass.png"));
		txtPasswordAzienda.setHint("Password");
		txtPasswordAzienda.setBackground(new Color(135, 143, 170));
		txtPasswordAzienda.setForeground(new Color(250, 250, 250));
		panel.add(txtPasswordAzienda, "w 60%");
		txtPasswordAzienda.setToolTipText("<html>- Almeno 8 caratteri<br>- Almeno 1 maiuscolo ed 1 minuscolo<br>- Almeno un numero<br>- Almeno un carattere speciale<html>");

		cmdAzienda.setBackground(new Color(45, 64, 98));
		cmdAzienda.setForeground(new Color(250, 250, 250));
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
					// Registrazione riuscita, mostra un messaggio di conferma
					JOptionPane.showMessageDialog(PanelLoginAndRegister.this, "Registrazione completata.",
							"Registrazione", JOptionPane.INFORMATION_MESSAGE);
					txtEmailAzienda.setText("");
					txtPasswordAzienda.setText("");
					txtNomeAzienda.setText("");
					txtPartitaIVA.setText("");
				} else {
					// Registrazione fallita, mostra un messaggio di errore
					JOptionPane.showMessageDialog(PanelLoginAndRegister.this, "Errore durante la registrazione.",
							"Errore di registrazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		cmdAzienda.addActionListener(lAzienda);

		return panel;
	}

	private JPanel createPrivatoPanel() {
		
		JPanel panel = new JPanel(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]10[]push"));

		txtNome.setPrefixIcon(new ImageIcon("img/user.png"));
		txtNome.setBackground(new Color(135, 143, 170));
		txtNome.setForeground(new Color(250, 250, 250));
		txtNome.setHint("Nome");
		panel.add(txtNome, "w 60%");

		txtCognome.setPrefixIcon(new ImageIcon("img/user.png"));
		txtCognome.setBackground(new Color(135, 143, 170));
		txtCognome.setForeground(new Color(250, 250, 250));
		txtCognome.setHint("Cognome");
		panel.add(txtCognome, "w 60%");

		// txtDataNascita.setPrefixIcon(new
		// ImageIcon("img/date.png"));
		txtDataNascita.setBackground(new Color(135, 143, 170));
		txtDataNascita.setForeground(new Color(250, 250, 250));
		txtDataNascita.setHint("Data di Nascita");
		panel.add(txtDataNascita, "w 60%");
		txtDataNascita.setToolTipText("Formato: GG/MM/AAAA");

		txtEmailPrivato.setPrefixIcon(new ImageIcon("img/mail.png"));
		txtEmailPrivato.setBackground(new Color(135, 143, 170));
		txtEmailPrivato.setForeground(new Color(250, 250, 250));
		txtEmailPrivato.setHint("Email");
		panel.add(txtEmailPrivato, "w 60%");

		txtPasswordPrivato.setPrefixIcon(new ImageIcon("img/pass.png"));
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
					// Registrazione riuscita, mostra un messaggio di conferma
					JOptionPane.showMessageDialog(PanelLoginAndRegister.this, "Registrazione completata.",
							"Registrazione", JOptionPane.INFORMATION_MESSAGE);
					txtEmailPrivato.setText("");
					txtPasswordPrivato.setText("");
					txtNome.setText("");
					txtCognome.setText("");
					txtDataNascita.setText("");
				} else {
					// Registrazione fallita, mostra un messaggio di errore
					JOptionPane.showMessageDialog(PanelLoginAndRegister.this, "Errore durante la registrazione.",
							"Errore di registrazione", JOptionPane.ERROR_MESSAGE);
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

        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon("img/mail.png"));
        txtEmail.setHint("Email");
        txtEmail.setBackground(new Color(135, 143, 170));
        txtEmail.setForeground(new Color(250, 250, 250));
        login.add(txtEmail, "w 60%");

        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon("img/pass.png"));
        txtPass.setHint("Password");
        txtPass.setBackground(new Color(135, 143, 170));
        txtPass.setForeground(new Color(250, 250, 250));
        login.add(txtPass, "w 60%");

        JButton cmdForget = new JButton("Hai dimenticato la password?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);

        Button cmd = new Button();
        cmd.setBackground(new Color(45, 64, 98));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("ACCEDI");
        login.add(cmd, "w 40%, h 40");
        
        
        cmd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = new String(txtPass.getPassword());
                Utente user = Utente.getUserByEmailAndPassword(email, password);
                if (user != null) {
                    // Pulisci form di login
                	txtEmail.setText("");
                	txtPass.setText("");

                    // Salva l'utente loggato come singleton
                    HomePage.loggedUser = user;
                    // Login riuscito, apri la pagina principale
                    HomePage.logged = true;
                    HomePage.btnLogout.setVisible(true);
                    SearchPage.mostraVeicoli();
                    HomePage.aggiornaMessaggioBenvenuto();
                    HomePage.cardLayout.show(HomePage.mainContentPanel, "home");
                } else {
                    // Login fallito, mostra un messaggio di errore
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