package fundamentos;

import java.util.Locale;

public record Email(String valor) {

	public Email {
		if (valor == null) {
			throw new IllegalArgumentException("O e-mail não pode ser nulo.");
		}

		String valorNormalizado = valor.trim().toLowerCase(Locale.ROOT);

		int primeiraArroba = valorNormalizado.indexOf("@");
		int ultimaArroba = valorNormalizado.lastIndexOf("@");

		boolean formatoInvalido = valorNormalizado.isBlank() || primeiraArroba <= 0 || primeiraArroba != ultimaArroba
				|| primeiraArroba == valorNormalizado.length() - 1;

		if (formatoInvalido) {
			throw new IllegalArgumentException("O e-mail informado é inválido.");
		}

		valor = valorNormalizado;

	}
}