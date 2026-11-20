package fundamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

	@Test
	void deveNormalizarEmail() {
		Email email = new Email("  TESTE@GMAIL.COM   ");

		assertEquals("teste@gmail.com", email.valor());
	}

	@Test
	void emailsNormalizadosDevemSerIguais() {
		Email primeiro = new Email("TESTE@GMAIL.COM");

		Email segundo = new Email("   teste@gmail.com   ");

		assertEquals(primeiro, segundo);
		assertEquals(primeiro.hashCode(), segundo.hashCode());
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "   ", "teste.com", "teste@", "teste@gmail@com" })
	void naoDeveCriarEmailInvalido(String valor) {
		assertThrows(IllegalArgumentException.class, () -> new Email(valor));
	}
}
