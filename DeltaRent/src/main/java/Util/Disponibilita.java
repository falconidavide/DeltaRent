package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import Veicolo.Veicolo;
import DB.DatabaseConnection;

public class Disponibilita {

    public static boolean verificaDisponibilita(Veicolo veicolo, Date dataInizio, Date dataFine) {
        boolean disponibile = true; // Imposta disponibile a true per default
        
        String query = "SELECT * FROM Prenotazione WHERE targa = ? AND (inizioPrenotazione BETWEEN ? AND ? OR finePrenotazione BETWEEN ? AND ?)";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String dataInizioStr = sdf.format(dataInizio);
            String dataFineStr = sdf.format(dataFine);
            
            Connection conn = DatabaseConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, veicolo.getTarga());
            stmt.setString(2, dataInizioStr);
            stmt.setString(3, dataFineStr);
            stmt.setString(4, dataInizioStr);
            stmt.setString(5, dataFineStr);
            ResultSet rs = stmt.executeQuery();
            
            // Se esiste almeno una tupla, imposta disponibile a false
            if (rs.next()) {
                disponibile = false;
            }
            
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return disponibile;
    }
}