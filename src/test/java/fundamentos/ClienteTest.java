package fundamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ClienteTest {

	@Test
	void deveCriarClienteValido() {
		Cliente cliente = new Cliente("  Ana Silva  ", "  ana@gmail.com  ");

		assertNotNull(cliente.getId());
		assertEquals("Ana Silva", cliente.getNome());
		assertEquals("ana@gmail.com", cliente.getEmail().valor());

	}

	@Test
	void naoDeveCriarClienteComNomeNulo() {
		assertThrows(IllegalArgumentException.class, () -> new Cliente(null, "cliente@email.com"));
	}

	@Test
	void naoDeveCriarClienteComNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Cliente("   ", "cliente@email.com"));
	}

	@Test
	void naoDeveCriarClienteComEmailNulo() {
		assertThrows(IllegalArgumentException.class, () -> new Cliente("Willian", null));
	}

	@Test
	void naoDeveCriarClienteComEmailInvalido() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Cliente("Ana", "anagmail"));

		assertEquals("O e-mail do cliente é inválido.", exception.getMessage());
	}

}
