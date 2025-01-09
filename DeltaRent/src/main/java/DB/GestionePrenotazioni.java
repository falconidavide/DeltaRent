package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionePrenotazioni {

    // Metodo per ottenere tutte le prenotazioni passate per un utente
    public List<String> getPrenotazioniPassate(String emailUtente) {
        List<String> prenotazioni = new ArrayList<>();
        String query = "SELECT * FROM Prenotazione WHERE emailUtente = ?";
        
        System.out.println("DENTRO GET PRENOTAZIONI PASSATE");

        try {
        	System.out.println("dentro try");
        	Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            System.out.println("pre query");

            stmt.setString(1, emailUtente);
            ResultSet rs = stmt.executeQuery();

            System.out.println("post query");
            while (rs.next()) {
            	System.out.println("while");
                int id = rs.getInt("ID");
                String targa = rs.getString("targa");
                String dataPrenotazione = rs.getString("dataPrenotazione");
                String dataInizio = rs.getString("inizioPrenotazione");
                String dataFine = rs.getString("finePrenotazione");

                String prenotazione = "ID: " + id + ", Targa: " + targa + ", Data Prenotazione: " + dataPrenotazione
                        + ", Data Inizio: " + dataInizio + ", Data Fine: " + dataFine;
                System.out.println("ciao"+prenotazione);
                prenotazioni.add(prenotazione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prenotazioni;
    }

    // Metodo per aggiungere una nuova prenotazione
    public boolean aggiungiPrenotazione(int emailUtente, int targa, String inizioPrenotazione, String finePrenotazione) {
        String query = "INSERT INTO Prenotazione (emailUtente, targa, dataPrenotazione, inizioPrenotazione, finePrenotazione) VALUES (?, ?, DATE('now')), ?, ?)";

        try {Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, emailUtente);
            stmt.setInt(2, targa);
            stmt.setString(3, inizioPrenotazione);
            stmt.setString(4, finePrenotazione);
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo per cancellare una prenotazione
    public boolean cancellaPrenotazione(int ID) {
        String query = "DELETE FROM Prenotazione WHERE id = ?";

        try {Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, ID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}