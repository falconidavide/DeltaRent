package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import DB.DatabaseConnection;

import java.sql.*;

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
		headerPanel.setBackground(new Color(62, 88, 121));
		JLabel lblHeader = new JLabel("Gestione Account");
		lblHeader.setFont(new Font("Arial", Font.BOLD, 36));
		lblHeader.setForeground(new Color(216, 195, 182));
		headerPanel.add(lblHeader);
		add(headerPanel, BorderLayout.NORTH);

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

	// Crea la sezione di modifica email
	private JPanel createEmailPanel() {
		JPanel emailPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		emailPanel.setBackground(new Color(62, 88, 121));
		emailPanel.setBorder(new LineBorder(new Color(216, 195, 182), 2, true));

		JLabel lblEmail = new JLabel("Email Corrente:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEmail.setForeground(new Color(216, 195, 182));
		emailPanel.add(lblEmail);

		txtCurrentEmail = new JTextField();
		txtCurrentEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCurrentEmail.setBackground(new Color(245, 239, 231));
		txtCurrentEmail.setBorder(null);
		emailPanel.add(txtCurrentEmail);

		JLabel lblNewEmail = new JLabel("Nuova Email:");
		lblNewEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewEmail.setForeground(new Color(216, 195, 182));
		emailPanel.add(lblNewEmail);

		txtNewEmail = new JTextField();
		txtNewEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNewEmail.setBackground(new Color(245, 239, 231));
		txtNewEmail.setBorder(null);
		emailPanel.add(txtNewEmail);

		btnUpdateEmail = new JButton("Aggiorna Email");
		btnUpdateEmail.setFont(new Font("Arial", Font.BOLD, 16));
		btnUpdateEmail.setBackground(new Color(245, 239, 231));
		btnUpdateEmail.setForeground(new Color(62, 88, 121));
		btnUpdateEmail.setFocusPainted(false);
		btnUpdateEmail.addActionListener(e -> updateEmail());
		emailPanel.add(btnUpdateEmail);

		return emailPanel;
	}

	// Crea la sezione di modifica password
	private JPanel createPasswordPanel() {
		JPanel passwordPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		passwordPanel.setBackground(new Color(62, 88, 121));
		passwordPanel.setBorder(new LineBorder(new Color(216, 195, 182), 2, true));

		JLabel lblOldPassword = new JLabel("Password Attuale:");
		lblOldPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOldPassword.setForeground(new Color(216, 195, 182));
		passwordPanel.add(lblOldPassword);

		txtOldPassword = new JPasswordField();
		txtOldPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		txtOldPassword.setBackground(new Color(245, 239, 231));
		txtOldPassword.setBorder(null);
		passwordPanel.add(txtOldPassword);

		JLabel lblNewPassword = new JLabel("Nuova Password:");
		lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewPassword.setForeground(new Color(216, 195, 182));
		passwordPanel.add(lblNewPassword);

		txtNewPassword = new JPasswordField();
		txtNewPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNewPassword.setBackground(new Color(245, 239, 231));
		txtNewPassword.setBorder(null);
		passwordPanel.add(txtNewPassword);

		btnChangePassword = new JButton("Cambia Password");
		btnChangePassword.setFont(new Font("Arial", Font.BOLD, 16));
		btnChangePassword.setBackground(new Color(245, 239, 231));
		btnChangePassword.setForeground(new Color(62, 88, 121));
		btnChangePassword.setFocusPainted(false);
		btnChangePassword.addActionListener(e -> changePassword());
		passwordPanel.add(btnChangePassword);

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
}