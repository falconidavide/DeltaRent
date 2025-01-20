package Delta_Rent.Delta_Rent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import db.Registration;
import gui.LogIn;

public class AppTest {

	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}
	
	@Test
	public void testRegistrazione() {
		assertFalse(Registration.registerUser(null, null, null, null, null, null, null));
	}

	@Test
	public void testEmails() {
		assertTrue(LogIn.isValidEmail("d.falconi2@studenti.unibg.it"));
		assertTrue(LogIn.isValidEmail("davide-falconi@gmail.com"));
		assertTrue(LogIn.isValidEmail("1234567890@gmail.com"));
		assertFalse(LogIn.isValidEmail("indirizzo"));
		assertFalse(LogIn.isValidEmail("@gmail.com"));
		assertFalse(LogIn.isValidEmail("davide falconi@gmail.com"));
		assertFalse(LogIn.isValidEmail("d.falconi@gmail"));
	}
	
	@Test
	public void testPasswords() {
		assertTrue(LogIn.isValidPassword("Provaa1!"));
	}
	
	@Test
	public void testPartitaIVA() {
		assertTrue(LogIn.isValidPIVA("57546750662"));
		assertTrue(LogIn.isValidPIVA("74164650405"));
		assertFalse(LogIn.isValidPIVA("prova"));
		assertFalse(LogIn.isValidPIVA("1896115351"));
	}
	
	@Test
	public void testDataNascita() {
		assertTrue(LogIn.isValidDate("01/01/2025"));
		assertTrue(LogIn.isValidDate("31/01/2025"));
		assertFalse(LogIn.isValidDate("50/01/2025"));
		assertFalse(LogIn.isValidDate("10/13/2025"));
	}
}
