package fundamentos;

import java.util.UUID;

public class Cliente {

	private final UUID id;
	private final String nome;
	private final Email email;

	public Cliente(String nome, String email) {
		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("O nome do cliente não pode ser nulo ou vazio.");
		}

		if (email == null || email.isBlank() || !email.contains("@")) {
			throw new IllegalArgumentException("O e-mail do cliente é inválido.");
		}

		this.id = UUID.randomUUID();
		this.nome = nome.trim();
		this.email = new Email(email);

	}

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Email getEmail() {
		return email;
	}

}
