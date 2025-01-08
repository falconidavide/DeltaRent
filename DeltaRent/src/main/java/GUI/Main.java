package GUI;

import java.util.List;

import DB.GestoreVeicoli;
import Veicolo.Automobile;

public class Main {
    public static void main(String[] args) {
        new HomePage().setVisible(true);
        GestoreVeicoli g = new GestoreVeicoli();

        g.getListaAutomobili();

		for (Automobile a : g.automobili) {
			System.out.println("Targa: " + a.getTarga());
		}
    }
}
