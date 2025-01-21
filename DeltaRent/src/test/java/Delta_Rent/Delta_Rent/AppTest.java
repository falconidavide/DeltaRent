package Delta_Rent.Delta_Rent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.raven.component.PanelLoginAndRegister;

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
		assertTrue(PanelLoginAndRegister.isValidEmail("d.falconi2@studenti.unibg.it"));
		assertTrue(PanelLoginAndRegister.isValidEmail("davide-falconi@gmail.com"));
		assertTrue(PanelLoginAndRegister.isValidEmail("1234567890@gmail.com"));
		assertFalse(PanelLoginAndRegister.isValidEmail("indirizzo"));
		assertFalse(PanelLoginAndRegister.isValidEmail("@gmail.com"));
		assertFalse(PanelLoginAndRegister.isValidEmail("davide falconi@gmail.com"));
		assertFalse(PanelLoginAndRegister.isValidEmail("d.falconi@gmail"));
	}
	
	@Test
	public void testPasswords() {
		assertTrue(PanelLoginAndRegister.isValidPassword("ProvaProva1!"));
		assertTrue(PanelLoginAndRegister.isValidPassword("1!Abc@23"));
		assertFalse(PanelLoginAndRegister.isValidPassword("ciao"));
		assertFalse(PanelLoginAndRegister.isValidPassword("provaprova123!"));
		assertFalse(PanelLoginAndRegister.isValidPassword("Provaprova123"));
	}
	
	@Test
	public void testPartitaIVA() {
		assertTrue(PanelLoginAndRegister.isValidPIVA("57546750662"));
		assertTrue(PanelLoginAndRegister.isValidPIVA("74164650405"));
		assertFalse(PanelLoginAndRegister.isValidPIVA("prova"));
		assertFalse(PanelLoginAndRegister.isValidPIVA("1896115351"));
	}
	
	@Test
	public void testDataNascita() {
		assertTrue(PanelLoginAndRegister.isValidDate("01/01/2025"));
		assertTrue(PanelLoginAndRegister.isValidDate("31/01/2025"));
		assertFalse(PanelLoginAndRegister.isValidDate("50/01/2025"));
		assertFalse(PanelLoginAndRegister.isValidDate("10/13/2025"));
	}
}
