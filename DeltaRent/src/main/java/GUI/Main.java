package GUI;

import DB.GestoreVeicoli;
import Veicolo.Automobile;

public class Main {
    public static void main(String[] args) {
        new HomePage().setVisible(true);

        
        /*
        GestoreVeicoli.aggiornaListaAutomobili();
		for (Automobile a : GestoreVeicoli.automobili) {
			System.out.println("Targa: " + a.getTarga());
		}
		*/
		
		
    }
}
