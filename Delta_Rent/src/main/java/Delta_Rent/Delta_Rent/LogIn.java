package Delta_Rent.Delta_Rent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class LogIn extends JFrame {
	private JPasswordField passwordField_1;
	private JTextField textField;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField_3;
	private JPasswordField passwordField_4;
	private JPasswordField passwordField_5;
	private JPasswordField passwordField_6;
	private JPasswordField passwordField_7;
	private JPasswordField passwordField_8;

    public LogIn() {
        // Impostazioni della finestra principale
        setTitle("DeltaRent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        // Layout principale
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        getContentPane().add(mainPanel);

        // Colonna sinistra
        JPanel leftColumn = new JPanel();
        leftColumn.setBackground(new Color(245, 239, 231));
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        leftColumn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
        rightColumn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("LOG-IN TO DELTARENT");
        lblTitle.setForeground(new Color(216, 195, 182));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 50));

        JTextField emailField = new JTextField();
        emailField.setBackground(new Color(245, 239, 231));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setMaximumSize(new Dimension(300, 30));
        emailField.setBorder(BorderFactory.createTitledBorder("E-mail"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(new Color(245, 239, 231));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

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
        panel_1.setBorder(new LineBorder(new Color(216, 195, 182), 2, true));
        panel_1.setBackground(new Color(62, 88, 121));
        tabbedPane.add("Azienda", panel_1);
        tabbedPane.setBackgroundAt(0, new Color(216, 195, 182));
        tabbedPane.setForegroundAt(0, new Color(32, 52, 85));
        panel_1.setLayout(null);
        
        passwordField_5 = new JPasswordField();
        passwordField_5.setBackground(new Color(245, 239, 231));
        passwordField_5.setForeground(new Color(32, 52, 85));
        passwordField_5.setMaximumSize(new Dimension(300, 30));
        passwordField_5.setBorder(BorderFactory.createTitledBorder("Nome Azienda"));
        passwordField_5.setAlignmentX(0.5f);
        passwordField_5.setBounds(88, 32, 300, 31);
        panel_1.add(passwordField_5);
        
        passwordField_6 = new JPasswordField();
        passwordField_6.setBackground(new Color(245, 239, 231));
        passwordField_6.setForeground(new Color(32, 52, 85));
        passwordField_6.setMaximumSize(new Dimension(300, 30));
        passwordField_6.setBorder(BorderFactory.createTitledBorder("PEC Registrata"));
        passwordField_6.setAlignmentX(0.5f);
        passwordField_6.setBounds(88, 87, 300, 31);
        panel_1.add(passwordField_6);
        
        passwordField_7 = new JPasswordField();
        passwordField_7.setBackground(new Color(245, 239, 231));
        passwordField_7.setForeground(new Color(32, 52, 85));
        passwordField_7.setMaximumSize(new Dimension(300, 30));
        passwordField_7.setBorder(BorderFactory.createTitledBorder("Partita Iva"));
        passwordField_7.setAlignmentX(0.5f);
        passwordField_7.setBounds(88, 143, 300, 31);
        panel_1.add(passwordField_7);
        
        passwordField_8 = new JPasswordField();
        passwordField_8.setBackground(new Color(245, 239, 231));
        passwordField_8.setForeground(new Color(32, 52, 85));
        passwordField_8.setMaximumSize(new Dimension(300, 30));
        passwordField_8.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField_8.setAlignmentX(0.5f);
        passwordField_8.setBounds(88, 196, 300, 31);
        panel_1.add(passwordField_8);
        
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
        btnRegistrati_1.setBounds(150, 245, 188, 29);
        panel_1.add(btnRegistrati_1);
        tabbedPane.setMaximumSize(new Dimension(500, 340));
        
        Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 100));
        rightColumn.add(rigidArea_1);

        rightColumn.add(lblTitle);
        rightColumn.add(Box.createRigidArea(new Dimension(0, 45)));
        rightColumn.add(emailField);
        rightColumn.add(Box.createRigidArea(new Dimension(0, 30)));
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
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setBackground(new Color(245, 239, 231));
        passwordField_1.setMaximumSize(new Dimension(300, 30));
        passwordField_1.setBorder(BorderFactory.createTitledBorder("Cognome"));
        passwordField_1.setAlignmentX(0.5f);
        passwordField_1.setBounds(99, 67, 300, 31);
        panel.add(passwordField_1);
        
        textField = new JTextField();
        textField.setBackground(new Color(245, 239, 231));
        textField.setMaximumSize(new Dimension(300, 30));
        textField.setBorder(BorderFactory.createTitledBorder("Nome"));
        textField.setAlignmentX(0.5f);
        textField.setBounds(99, 24, 300, 31);
        panel.add(textField);
        
        passwordField_2 = new JPasswordField();
        passwordField_2.setBackground(new Color(245, 239, 231));
        passwordField_2.setMaximumSize(new Dimension(300, 30));
        passwordField_2.setBorder(BorderFactory.createTitledBorder("E-mail"));
        passwordField_2.setAlignmentX(0.5f);
        passwordField_2.setBounds(99, 157, 300, 31);
        panel.add(passwordField_2);
        
        passwordField_3 = new JPasswordField();
        passwordField_3.setBackground(new Color(245, 239, 231));
        passwordField_3.setMaximumSize(new Dimension(300, 30));
        passwordField_3.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField_3.setAlignmentX(0.5f);
        passwordField_3.setBounds(99, 200, 300, 31);
        panel.add(passwordField_3);
        
        passwordField_4 = new JPasswordField();
        passwordField_4.setBackground(new Color(245, 239, 231));
        passwordField_4.setMaximumSize(new Dimension(300, 30));
        passwordField_4.setBorder(BorderFactory.createTitledBorder("Data Di Nascita"));
        passwordField_4.setAlignmentX(0.5f);
        passwordField_4.setBounds(99, 114, 300, 31);
        panel.add(passwordField_4);
        
        JButton btnRegistrati = new JButton("Registrati");
        btnRegistrati.setBackground(new Color(245, 239, 231));
        btnRegistrati.setForeground(new Color(62, 88, 121));
        btnRegistrati.setContentAreaFilled(true);
        btnRegistrati.setBorderPainted(true);
        btnRegistrati.setAlignmentX(0.5f);
        btnRegistrati.setBounds(150, 245, 188, 29);
        panel.add(btnRegistrati);

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
