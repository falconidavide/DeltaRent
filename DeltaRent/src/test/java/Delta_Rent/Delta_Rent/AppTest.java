package Delta_Rent.Delta_Rent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import db.Registration;
import business.util.Validatori;

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
		assertTrue(Validatori.isValidEmail("d.falconi2@studenti.unibg.it"));
		assertTrue(Validatori.isValidEmail("davide-falconi@gmail.com"));
		assertTrue(Validatori.isValidEmail("1234567890@gmail.com"));
		assertFalse(Validatori.isValidEmail("indirizzo"));
		assertFalse(Validatori.isValidEmail("@gmail.com"));
		assertFalse(Validatori.isValidEmail("davide falconi@gmail.com"));
		assertFalse(Validatori.isValidEmail("d.falconi@gmail"));
	}
	
	@Test
	public void testPasswords() {
		assertTrue(Validatori.isValidPassword("ProvaProva1!"));
		assertTrue(Validatori.isValidPassword("1!Abc@23"));
		assertFalse(Validatori.isValidPassword("ciao"));
		assertFalse(Validatori.isValidPassword("provaprova123!"));
		assertFalse(Validatori.isValidPassword("Provaprova123"));
	}
	
	@Test
	public void testPartitaIVA() {
		assertTrue(Validatori.isValidPIVA("57546750662"));
		assertTrue(Validatori.isValidPIVA("74164650405"));
		assertFalse(Validatori.isValidPIVA("prova"));
		assertFalse(Validatori.isValidPIVA("1896115351"));
	}
	
	@Test
	public void testDataNascita() {
		assertTrue(Validatori.isValidDate("01/01/2025"));
		assertTrue(Validatori.isValidDate("31/01/2025"));
		assertFalse(Validatori.isValidDate("50/01/2025"));
		assertFalse(Validatori.isValidDate("10/13/2025"));
	}
}
