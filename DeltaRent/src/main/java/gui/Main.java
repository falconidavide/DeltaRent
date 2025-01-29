package gui;

public class Main {
	public static void main(String[] args) {
		final PaginaCaricamento paginaCaricamento = PaginaCaricamento.getInstance();
		paginaCaricamento.setVisible(true);
		final HomePage homePage = new HomePage();
		homePage.setVisible(true);
		paginaCaricamento.dispose();
	}
}
