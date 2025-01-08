package DB;

import java.sql.*;

public class Registration {

    public static boolean registerUser(String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Utente (email, password) VALUES (?, ?)")) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Errore durante la registrazione dell'utente: " + e.getMessage());
            return false;
        }
    }
}
