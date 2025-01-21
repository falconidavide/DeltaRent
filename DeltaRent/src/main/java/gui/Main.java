package gui;

public class Main {
    public static void main(String[] args) {
        PaginaCaricamento paginaCaricamento = new PaginaCaricamento();
        paginaCaricamento.setVisible(true);
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
        paginaCaricamento.dispose();
    }
}
