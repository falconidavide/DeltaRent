package GUI;

import java.util.Timer;
import java.util.TimerTask;

import DB.GestoreVeicoli;
import Veicolo.Automobile;
import Utente.Utente;

public class Main {
    public static void main(String[] args) {
        new HomePage().setVisible(true);

        
        /*
        GestoreVeicoli.aggiornaListaAutomobili();
		for (Automobile a : GestoreVeicoli.automobili) {
			System.out.println("Targa: " + a.getTarga());
		}
		*/
        
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                System.out.println("Loggato: " + Utente.isLoggato());
                System.out.println("Utente privato: " + Utente.getIsPrivato());
            }
        }, 0, 5000);
		
    }
}
