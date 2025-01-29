package gui;

public class Main {
	public static void main(String[] args) {
		//HomePage homePage;
		final PaginaCaricamento paginaCaricamento = PaginaCaricamento.getInstance();
		paginaCaricamento.setVisible(true);
		new Thread(new Runnable() {
			public void run() {
				HomePage homePage = new HomePage();
				homePage.setVisible(true);
				paginaCaricamento.dispose();
			}
		}).start();
	}
}
