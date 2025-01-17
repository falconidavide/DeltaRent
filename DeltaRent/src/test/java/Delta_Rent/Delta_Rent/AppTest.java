package Delta_Rent.Delta_Rent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import db.Registration;
import gui.LogIn;

/**
 * Unit test for simple App.
 */
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
}
