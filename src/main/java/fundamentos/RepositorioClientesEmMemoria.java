package fundamentos;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class RepositorioClientesEmMemoria implements RepositorioClientes {

	private final Map<UUID, Cliente> clientes = new LinkedHashMap<>();

	@Override
	public void salvar(Cliente cliente) {
		if (cliente == null) {
			throw new IllegalArgumentException("O cliente não pode ser nulo.");
		}

		if (clientes.containsKey(cliente.getId())) {
			throw new IllegalArgumentException("Já existe um cliente com o identificador informado.");
		}

		boolean emailJaCadastrado = clientes.values().stream()
				.anyMatch(clienteCadastrado -> clienteCadastrado.getEmail().equals(cliente.getEmail()));

		if (emailJaCadastrado) {
			throw new IllegalArgumentException("Já existe um cliente com o e-mail informado");
		}

		clientes.put(cliente.getId(), cliente);

	}

	@Override
	public Optional<Cliente> buscarPorId(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("O identificador do cliente não pode ser nulo.");
		}

		return Optional.ofNullable(clientes.get(id));

	}

	@Override
	public Optional<Cliente> buscarPorEmail(Email email) {
		if (email == null) {
			throw new IllegalArgumentException("O email não pode ser Nulo.");
		}

		return clientes.values().stream().filter(cliente -> cliente.getEmail().equals(email)).findFirst();
	}

	@Override
	public List<Cliente> listarTodos() {
		return List.copyOf(clientes.values());
	}

	@Override
	public int quantidade() {
		return clientes.size();
	}

}
