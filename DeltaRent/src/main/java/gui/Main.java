package gui;

public class Main {
    public static void main(String[] args) {
        PaginaCaricamento paginaCaricamento = new PaginaCaricamento();
        paginaCaricamento.setVisible(true);
        new HomePage().setVisible(true);
        
        // Chiudi PaginaCaricamento e apri HomePage
        paginaCaricamento.dispose();
    
    }
}
