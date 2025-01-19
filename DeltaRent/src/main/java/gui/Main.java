package gui;

public class Main {
    public static void main(String[] args) {
        PaginaCaricamento paginaCaricamento = new PaginaCaricamento();
        paginaCaricamento.setVisible(true);
        new HomePage().setVisible(true);
        
        // Simula un'operazione di caricamento e poi apre HomePage
        try {
            Thread.sleep(1000); // Simula un caricamento 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Chiudi PaginaCaricamento e apri HomePage
        paginaCaricamento.dispose();
    
    }
}
