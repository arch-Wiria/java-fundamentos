package fundamentos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositorioClientes {

	void salvar(Cliente cliente);

	Optional<Cliente> buscarPorId(UUID id);

	Optional<Cliente> buscarPorEmail(Email email);

	List<Cliente> listarTodos();

	int quantidade();

}
