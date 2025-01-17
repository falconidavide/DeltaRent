package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import db.Registration;
import utente.Utente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField nomeFieldReg;
	private JTextField nomeAziendaFieldReg;
	private JTextField emailFieldReg;
	private JTextField partitaIvaFieldReg;
	private JTextField passwordFieldReg;
	private JTextField cognomeFieldReg;
	private JTextField dataNascitaFieldReg;
	private JTextField emailFieldRegUser;
	private JTextField passwordFieldRegUser;

	public LogIn() {
		setLayout(new BorderLayout());
		setBackground(new Color(32, 52, 85));

		JPanel rightColumn = new JPanel();
		rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
		rightColumn.setBackground(new Color(32, 52, 85));

		JLabel lblTitle = new JLabel("LOG-IN TO DELTARENT");
		lblTitle.setForeground(new Color(216, 195, 182));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 50));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JTextField emailField = new JTextField();
		emailField.setBackground(new Color(245, 239, 231));
		emailField.setMaximumSize(new Dimension(300, 30));
		emailField.setBorder(null);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBackground(new Color(245, 239, 231));
		passwordField.setMaximumSize(new Dimension(300, 30));
		passwordField.setBorder(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(245, 239, 231));
		separator.setMaximumSize(new Dimension(300, 10));

		JLabel lblRegister = new JLabel("Non ti sei mai registrato?");
		lblRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblRegister.setForeground(new Color(216, 195, 182));
		lblRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(216, 195, 182));
		panel_1.setBorder(new LineBorder(new Color(216, 195, 182), 4, true));
		panel_1.setBackground(new Color(62, 88, 121));
		tabbedPane.add("Azienda", panel_1);
		tabbedPane.setBackgroundAt(0, new Color(216, 195, 182));
		tabbedPane.setForegroundAt(0, new Color(32, 52, 85));
		panel_1.setLayout(null);

		JButton btnRegistrati_1 = new JButton("Registrati");
		btnRegistrati_1.setForeground(new Color(62, 88, 121));
		btnRegistrati_1.setBackground(new Color(245, 239, 231));
		btnRegistrati_1.setContentAreaFilled(true);
		btnRegistrati_1.setBorderPainted(true);
		btnRegistrati_1.setAlignmentX(0.5f);
		btnRegistrati_1.setBounds(150, 302, 188, 29);
		panel_1.add(btnRegistrati_1);

		JLabel lblNomeAzienda = new JLabel("Nome Azienda");
		lblNomeAzienda.setForeground(new Color(216, 195, 182));
		lblNomeAzienda.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNomeAzienda.setAlignmentX(0.5f);
		lblNomeAzienda.setBounds(88, 22, 113, 18);
		panel_1.add(lblNomeAzienda);

		JLabel lblPecRegistrata = new JLabel("E-mail");
		lblPecRegistrata.setForeground(new Color(216, 195, 182));
		lblPecRegistrata.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPecRegistrata.setAlignmentX(0.5f);
		lblPecRegistrata.setBounds(88, 147, 113, 18);
		panel_1.add(lblPecRegistrata);

		JLabel lblPartitaIva = new JLabel("Partita IVA");
		lblPartitaIva.setForeground(new Color(216, 195, 182));
		lblPartitaIva.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPartitaIva.setAlignmentX(0.5f);
		lblPartitaIva.setBounds(88, 82, 113, 18);
		panel_1.add(lblPartitaIva);

		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setForeground(new Color(216, 195, 182));
		lblPassword_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassword_1.setAlignmentX(0.5f);
		lblPassword_1.setBounds(88, 212, 113, 18);
		panel_1.add(lblPassword_1);

		nomeAziendaFieldReg = new JTextField();
		nomeAziendaFieldReg.setMaximumSize(new Dimension(300, 30));
		nomeAziendaFieldReg.setBorder(null);
		nomeAziendaFieldReg.setBackground(new Color(245, 239, 231));
		nomeAziendaFieldReg.setAlignmentX(0.5f);
		nomeAziendaFieldReg.setBounds(88, 41, 300, 30);
		panel_1.add(nomeAziendaFieldReg);

		emailFieldReg = new JTextField();
		emailFieldReg.setMaximumSize(new Dimension(300, 30));
		emailFieldReg.setBorder(null);
		emailFieldReg.setBackground(new Color(245, 239, 231));
		emailFieldReg.setAlignmentX(0.5f);
		emailFieldReg.setBounds(88, 171, 300, 30);
		panel_1.add(emailFieldReg);

		partitaIvaFieldReg = new JTextField();
		partitaIvaFieldReg.setMaximumSize(new Dimension(300, 30));
		partitaIvaFieldReg.setBorder(null);
		partitaIvaFieldReg.setBackground(new Color(245, 239, 231));
		partitaIvaFieldReg.setAlignmentX(0.5f);
		partitaIvaFieldReg.setBounds(88, 106, 300, 30);
		panel_1.add(partitaIvaFieldReg);

		passwordFieldReg = new JPasswordField();
		passwordFieldReg.setMaximumSize(new Dimension(300, 30));
		passwordFieldReg.setBorder(null);
		passwordFieldReg.setBackground(new Color(245, 239, 231));
		passwordFieldReg.setAlignmentX(0.5f);
		passwordFieldReg.setBounds(88, 232, 300, 30);
		panel_1.add(passwordFieldReg);
		tabbedPane.setMaximumSize(new Dimension(500, 400));

		Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 70));
		rightColumn.add(rigidArea_1);

		rightColumn.add(lblTitle);
		rightColumn.add(Box.createRigidArea(new Dimension(0, 45)));

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(new Color(216, 195, 182));
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEmail.setAlignmentX(0.5f);
		rightColumn.add(lblEmail);
		rightColumn.add(emailField);
		rightColumn.add(Box.createRigidArea(new Dimension(0, 30)));

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(216, 195, 182));
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassword.setAlignmentX(0.5f);
		rightColumn.add(lblPassword);
		rightColumn.add(passwordField);
		rightColumn.add(Box.createRigidArea(new Dimension(0, 30)));
		Component rigidArea = Box.createRigidArea(new Dimension(0, 15));
		rightColumn.add(rigidArea);

		JButton btnLogin = new JButton("LOG-IN");
		btnLogin.setForeground(new Color(62, 88, 121));
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnLogin.setContentAreaFilled(true);
		btnLogin.setBorderPainted(true);
		btnLogin.setBackground(new Color(245, 239, 231));
		btnLogin.setAlignmentX(0.5f);
		rightColumn.add(btnLogin);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(0, 20));
		rightColumn.add(rigidArea_2);
		rightColumn.add(separator);
		rightColumn.add(Box.createRigidArea(new Dimension(0, 20)));
		rightColumn.add(lblRegister);
		rightColumn.add(Box.createRigidArea(new Dimension(0, 20)));
		rightColumn.add(tabbedPane);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(216, 195, 182), 2, true));
		panel.setBackground(new Color(62, 88, 121));
		tabbedPane.add("Privato", panel);
		tabbedPane.setBackgroundAt(1, new Color(216, 195, 182));
		tabbedPane.setForegroundAt(1, new Color(32, 52, 85));
		panel.setLayout(null);

		nomeFieldReg = new JTextField();
		nomeFieldReg.setBackground(new Color(245, 239, 231));
		nomeFieldReg.setMaximumSize(new Dimension(300, 30));
		nomeFieldReg.setBorder(null);
		nomeFieldReg.setAlignmentX(0.5f);
		nomeFieldReg.setBounds(99, 24, 300, 31);
		panel.add(nomeFieldReg);

		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setBackground(new Color(245, 239, 231));
		btnRegistrati.setForeground(new Color(62, 88, 121));
		btnRegistrati.setContentAreaFilled(true);
		btnRegistrati.setBorderPainted(true);
		btnRegistrati.setAlignmentX(0.5f);
		btnRegistrati.setBounds(150, 302, 188, 29);
		panel.add(btnRegistrati);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(216, 195, 182));
		lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome.setAlignmentX(0.5f);
		lblNome.setBounds(98, 6, 66, 18);
		panel.add(lblNome);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setForeground(new Color(216, 195, 182));
		lblCognome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCognome.setAlignmentX(0.5f);
		lblCognome.setBounds(99, 61, 66, 18);
		panel.add(lblCognome);

		JLabel lblDataDiNascita = new JLabel("Data di Nascita (GG/MM/AAAA)");
		lblDataDiNascita.setForeground(new Color(216, 195, 182));
		lblDataDiNascita.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDataDiNascita.setAlignmentX(0.5f);
		lblDataDiNascita.setBounds(99, 119, 116, 18);
		panel.add(lblDataDiNascita);

		JLabel lblEmailRegUser = new JLabel("E-mail");
		lblEmailRegUser.setForeground(new Color(216, 195, 182));
		lblEmailRegUser.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEmailRegUser.setAlignmentX(0.5f);
		lblEmailRegUser.setBounds(99, 180, 116, 18);
		panel.add(lblEmailRegUser);

		JLabel lblPasswordRegUser = new JLabel("Password");
		lblPasswordRegUser.setForeground(new Color(216, 195, 182));
		lblPasswordRegUser.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPasswordRegUser.setAlignmentX(0.5f);
		lblPasswordRegUser.setBounds(99, 241, 116, 18);
		panel.add(lblPasswordRegUser);
		

		JLabel lblPasswordRequirementsRegUser = new JLabel("(almeno 8 caratteri, almeno 1 maiuscolo ed 1 minuscolo, almeno un numero, almeno un carattere speciale)");
		lblPasswordRequirementsRegUser.setForeground(new Color(216, 195, 182));
		lblPasswordRequirementsRegUser.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPasswordRequirementsRegUser.setAlignmentX(0.5f);
		lblPasswordRequirementsRegUser.setBounds(99, 259, 300, 18);
		panel.add(lblPasswordRequirementsRegUser);

		cognomeFieldReg = new JTextField();
		cognomeFieldReg.setMaximumSize(new Dimension(300, 30));
		cognomeFieldReg.setBorder(null);
		cognomeFieldReg.setBackground(new Color(245, 239, 231));
		cognomeFieldReg.setAlignmentX(0.5f);
		cognomeFieldReg.setBounds(99, 80, 300, 31);
		panel.add(cognomeFieldReg);

		dataNascitaFieldReg = new JTextField();
		dataNascitaFieldReg.setMaximumSize(new Dimension(300, 30));
		dataNascitaFieldReg.setBorder(null);
		dataNascitaFieldReg.setBackground(new Color(245, 239, 231));
		dataNascitaFieldReg.setAlignmentX(0.5f);
		dataNascitaFieldReg.setBounds(99, 137, 300, 31);
		panel.add(dataNascitaFieldReg);

		emailFieldRegUser = new JTextField();
		emailFieldRegUser.setMaximumSize(new Dimension(300, 30));
		emailFieldRegUser.setBorder(null);
		emailFieldRegUser.setBackground(new Color(245, 239, 231));
		emailFieldRegUser.setAlignmentX(0.5f);
		emailFieldRegUser.setBounds(99, 198, 300, 31);
		panel.add(emailFieldRegUser);

		passwordFieldRegUser = new JPasswordField();
		passwordFieldRegUser.setMaximumSize(new Dimension(300, 30));
		passwordFieldRegUser.setBorder(null);
		passwordFieldRegUser.setBackground(new Color(245, 239, 231));
		passwordFieldRegUser.setAlignmentX(0.5f);
		passwordFieldRegUser.setBounds(99, 277, 300, 31);
		panel.add(passwordFieldRegUser);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				String password = new String(passwordField.getPassword());
				Utente user = Utente.getUserByEmailAndPassword(email, password);
				if (user != null) {
					// Pulisci form di login
					emailField.setText("");
					passwordField.setText("");

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
					JOptionPane.showMessageDialog(LogIn.this, "Credenziali errate.", "Errore di accesso", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		ActionListener lAzienda = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email, password, nomeAzienda, partitaIva;
				email = emailFieldReg.getText();
				password = passwordFieldReg.getText();
				nomeAzienda = nomeAziendaFieldReg.getText();
				partitaIva = partitaIvaFieldReg.getText();

				if (Registration.registerUser(email, password, null, null, null, nomeAzienda, partitaIva)) {
					// Registrazione riuscita, mostra un messaggio di conferma
					JOptionPane.showMessageDialog(LogIn.this, "Registrazione completata.", "Registrazione", JOptionPane.INFORMATION_MESSAGE);
					emailFieldReg.setText("");
					passwordFieldReg.setText("");
					nomeAziendaFieldReg.setText("");
					partitaIvaFieldReg.setText("");
				} else {
					// Registrazione fallita, mostra un messaggio di errore
					JOptionPane.showMessageDialog(LogIn.this, "Errore durante la registrazione.", "Errore di registrazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		ActionListener lPrivato = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email, password, nome, cognome, dataNascita;

				email = emailFieldRegUser.getText();
				password = passwordFieldRegUser.getText();
				nome = nomeFieldReg.getText();
				cognome = cognomeFieldReg.getText();
				dataNascita = dataNascitaFieldReg.getText();

				if (Registration.registerUser(email, password, nome, cognome, dataNascita, null, null)) {
					// Registrazione riuscita, mostra un messaggio di conferma
					JOptionPane.showMessageDialog(LogIn.this, "Registrazione completata.", "Registrazione", JOptionPane.INFORMATION_MESSAGE);
					emailFieldRegUser.setText("");
					passwordFieldRegUser.setText("");
					nomeFieldReg.setText("");
					cognomeFieldReg.setText("");
					dataNascitaFieldReg.setText("");
				} else {
					// Registrazione fallita, mostra un messaggio di errore
					JOptionPane.showMessageDialog(LogIn.this, "Errore durante la registrazione.", "Errore di registrazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		btnRegistrati_1.addActionListener(lAzienda);
		btnRegistrati.addActionListener(lPrivato);

		// Aggiunta dei pannelli al layout principale
		this.add(rightColumn, BorderLayout.CENTER);

		btnRegistrati_1.setEnabled(false);
		btnRegistrati.setEnabled(false);

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
					btnRegistrati_1.setEnabled(true);
				} else {
					btnRegistrati_1.setEnabled(false);
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
					btnRegistrati.setEnabled(true);
				} else {
					btnRegistrati.setEnabled(false);
				}
			}
		};
		nomeAziendaFieldReg.getDocument().addDocumentListener(aziendaListener);
		partitaIvaFieldReg.getDocument().addDocumentListener(aziendaListener);
		emailFieldReg.getDocument().addDocumentListener(aziendaListener);
		passwordFieldReg.getDocument().addDocumentListener(aziendaListener);

		nomeFieldReg.getDocument().addDocumentListener(privatoListener);
		cognomeFieldReg.getDocument().addDocumentListener(privatoListener);
		dataNascitaFieldReg.getDocument().addDocumentListener(privatoListener);
		emailFieldRegUser.getDocument().addDocumentListener(privatoListener);
		passwordFieldRegUser.getDocument().addDocumentListener(privatoListener);
	}

	// Metodo per verificare se tutti i campi sono riempiti
	private boolean areAllFieldsFilledForAzienda() {
		return !nomeAziendaFieldReg.getText().trim().isEmpty() && isValidPIVA(partitaIvaFieldReg.getText().trim())
				&& isValidEmail(emailFieldReg.getText().trim()) && isValidPassword(passwordFieldReg.getText().trim());
	}

	private boolean areAllFieldsFilledForPrivato() {
		return !nomeFieldReg.getText().trim().isEmpty() && !cognomeFieldReg.getText().trim().isEmpty()
				&& isValidDate(dataNascitaFieldReg.getText().trim()) && isValidEmail(emailFieldRegUser.getText().trim())
				&& isValidPassword(passwordFieldRegUser.getText().trim());
	}

	// Metodo per validare l'email
	public static boolean isValidEmail(String email) {
		return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
	}
	
	public static boolean isValidPassword(String password) {
		return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
	}
	
	public static boolean isValidPIVA(String PIVA) {
		return PIVA.matches("^[0-9]{11}$");
	}
	
	public static boolean isValidDate(String date) {
		return date.matches("(0[1-9]|[12][0-9]|3[01])[\\/](0[1-9]|1[012])[\\/](19|20)\\d\\d");
	}
}
