package GUI;

import DB.GestoreVeicoli;

public class Main {
    public static void main(String[] args) {
        new HomePage().setVisible(true);
        GestoreVeicoli g = new GestoreVeicoli();
        System.out.println("-----------AUTOMOBILI----------");
        g.getListaAutomobili();
        System.out.println("------------FURGONI------------");
        g.getListaFurgoni();
    }
}
