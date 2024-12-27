package Delta_Rent.Delta_Rent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class LogIn extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

    public LogIn() {
        // Impostazioni della finestra principale
        setTitle("DeltaRent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 950);
        setLocationRelativeTo(null);

        // Layout principale
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        getContentPane().add(mainPanel);

        // Colonna sinistra
        JPanel leftColumn = new JPanel();
        leftColumn.setBackground(new Color(62, 88, 121));
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        leftColumn.setBorder(new LineBorder(new Color(216, 195, 182), 3));

        JLabel avatarLabel = new JLabel(new ImageIcon("path/to/avatar/image")); // Sostituisci con il percorso immagine
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnFavorites = createButton("Preferiti");
        JButton btnSearch = createButton("Ricerca");
        JButton btnOther = createButton("Button");

        leftColumn.add(avatarLabel);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20))); // Spaziatura
        leftColumn.add(btnFavorites);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
        leftColumn.add(btnSearch);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 20)));
        leftColumn.add(btnOther);

        // Colonna destra
        JPanel rightColumn = new JPanel();
        rightColumn.setBackground(new Color(32, 52, 85));
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
        rightColumn.setBorder(null);

        JLabel lblTitle = new JLabel("LOG-IN TO DELTARENT");
        lblTitle.setForeground(new Color(216, 195, 182));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 50));

        JTextField emailField = new JTextField();
        emailField.setBackground(new Color(245, 239, 231));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setMaximumSize(new Dimension(300, 30));
        emailField.setBorder(null);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(new Color(245, 239, 231));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setBorder(null);

        JButton btnForgotPassword = new JButton("Password Dimenticata?");
        btnForgotPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        btnForgotPassword.setBackground(new Color(32, 52, 85));
        btnForgotPassword.setForeground(new Color(216, 195, 182));
        btnForgotPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnForgotPassword.setBorderPainted(false);
        btnForgotPassword.setContentAreaFilled(false);

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
        btnRegistrati_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
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
        
        JLabel lblPecRegistrata = new JLabel("PEC Registrata");
        lblPecRegistrata.setForeground(new Color(216, 195, 182));
        lblPecRegistrata.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPecRegistrata.setAlignmentX(0.5f);
        lblPecRegistrata.setBounds(88, 83, 113, 18);
        panel_1.add(lblPecRegistrata);
        
        JLabel lblPartitaIva = new JLabel("Partita IVA");
        lblPartitaIva.setForeground(new Color(216, 195, 182));
        lblPartitaIva.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPartitaIva.setAlignmentX(0.5f);
        lblPartitaIva.setBounds(88, 151, 113, 18);
        panel_1.add(lblPartitaIva);
        
        JLabel lblPassword_1 = new JLabel("Password");
        lblPassword_1.setForeground(new Color(216, 195, 182));
        lblPassword_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPassword_1.setAlignmentX(0.5f);
        lblPassword_1.setBounds(88, 212, 113, 18);
        panel_1.add(lblPassword_1);
        
        textField_1 = new JTextField();
        textField_1.setMaximumSize(new Dimension(300, 30));
        textField_1.setBorder(null);
        textField_1.setBackground(new Color(245, 239, 231));
        textField_1.setAlignmentX(0.5f);
        textField_1.setBounds(88, 41, 300, 30);
        panel_1.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setMaximumSize(new Dimension(300, 30));
        textField_2.setBorder(null);
        textField_2.setBackground(new Color(245, 239, 231));
        textField_2.setAlignmentX(0.5f);
        textField_2.setBounds(88, 101, 300, 30);
        panel_1.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setMaximumSize(new Dimension(300, 30));
        textField_3.setBorder(null);
        textField_3.setBackground(new Color(245, 239, 231));
        textField_3.setAlignmentX(0.5f);
        textField_3.setBounds(88, 170, 300, 30);
        panel_1.add(textField_3);
        
        textField_4 = new JTextField();
        textField_4.setMaximumSize(new Dimension(300, 30));
        textField_4.setBorder(null);
        textField_4.setBackground(new Color(245, 239, 231));
        textField_4.setAlignmentX(0.5f);
        textField_4.setBounds(88, 232, 300, 30);
        panel_1.add(textField_4);
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
        rightColumn.add(btnForgotPassword);
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
        panel.setLayout(null);
        
        textField = new JTextField();
        textField.setBackground(new Color(245, 239, 231));
        textField.setMaximumSize(new Dimension(300, 30));
        textField.setBorder(null);
        textField.setAlignmentX(0.5f);
        textField.setBounds(99, 24, 300, 31);
        panel.add(textField);
        
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
        
        JLabel lblDataDiNascita = new JLabel("Data di Nascita");
        lblDataDiNascita.setForeground(new Color(216, 195, 182));
        lblDataDiNascita.setFont(new Font("Arial", Font.PLAIN, 15));
        lblDataDiNascita.setAlignmentX(0.5f);
        lblDataDiNascita.setBounds(99, 119, 116, 18);
        panel.add(lblDataDiNascita);
        
        JLabel lblEmail_1 = new JLabel("E-mail");
        lblEmail_1.setForeground(new Color(216, 195, 182));
        lblEmail_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblEmail_1.setAlignmentX(0.5f);
        lblEmail_1.setBounds(99, 180, 116, 18);
        panel.add(lblEmail_1);
        
        JLabel lblEmail_1_1 = new JLabel("Password");
        lblEmail_1_1.setForeground(new Color(216, 195, 182));
        lblEmail_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblEmail_1_1.setAlignmentX(0.5f);
        lblEmail_1_1.setBounds(99, 241, 116, 18);
        panel.add(lblEmail_1_1);
        
        textField_5 = new JTextField();
        textField_5.setMaximumSize(new Dimension(300, 30));
        textField_5.setBorder(null);
        textField_5.setBackground(new Color(245, 239, 231));
        textField_5.setAlignmentX(0.5f);
        textField_5.setBounds(99, 80, 300, 31);
        panel.add(textField_5);
        
        textField_6 = new JTextField();
        textField_6.setMaximumSize(new Dimension(300, 30));
        textField_6.setBorder(null);
        textField_6.setBackground(new Color(245, 239, 231));
        textField_6.setAlignmentX(0.5f);
        textField_6.setBounds(99, 137, 300, 31);
        panel.add(textField_6);
        
        textField_7 = new JTextField();
        textField_7.setMaximumSize(new Dimension(300, 30));
        textField_7.setBorder(null);
        textField_7.setBackground(new Color(245, 239, 231));
        textField_7.setAlignmentX(0.5f);
        textField_7.setBounds(99, 198, 300, 31);
        panel.add(textField_7);
        
        textField_8 = new JTextField();
        textField_8.setMaximumSize(new Dimension(300, 30));
        textField_8.setBorder(null);
        textField_8.setBackground(new Color(245, 239, 231));
        textField_8.setAlignmentX(0.5f);
        textField_8.setBounds(99, 259, 300, 31);
        panel.add(textField_8);

        // Aggiunta dei pannelli al layout principale
        mainPanel.add(leftColumn, BorderLayout.WEST);
        mainPanel.add(rightColumn, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(150, 30));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	LogIn loginPage = new LogIn();
            loginPage.setVisible(true);
        });
    }
}
