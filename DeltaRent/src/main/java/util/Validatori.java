package util;

public class Validatori {
	// Metodo per validare l'email
	public static boolean isValidEmail(String email) {
		return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
	}

	// Metodo per validare la password
	public static boolean isValidPassword(String password) {
		return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
	}

	// Metodo per validare la partita IVA
	public static boolean isValidPIVA(String PIVA) {
		return PIVA.matches("^[0-9]{11}$");
	}

	// Metodo per validare la data
	public static boolean isValidDate(String date) {
		return date.matches("(0[1-9]|[12][0-9]|3[01])[\\/](0[1-9]|1[012])[\\/](19|20)\\d\\d");
	}
}
