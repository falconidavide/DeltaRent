package Utente;

import java.sql.*;

import DB.DatabaseConnection;

public class Utente {

    private int ID;
    private String email;
    private String password;

    public Utente(int id, String email, String password) {
        this.ID = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static Utente getUserByEmailAndPassword(String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Utente WHERE email = ? AND password = ?")) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utente(rs.getInt("ID"), rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println("Errore durante la ricerca dell'utente: " + e.getMessage());
        }
        return null;
    }
}
