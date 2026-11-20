package fundamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RepositorioClientesEmMemoriaTest {

	private RepositorioClientes repositorio;
	private Cliente cliente;

	@BeforeEach
	void preparar() {
		repositorio = new RepositorioClientesEmMemoria();
		cliente = new Cliente("Teste", "Teste@gmail.com");
	}

	@Test
	void deveSalvarCliente() {
		repositorio.salvar(cliente);

		assertEquals(1, repositorio.quantidade());

	}

	@Test
	void naoDeveSalvarClienteNulo() {
		assertThrows(IllegalArgumentException.class, () -> repositorio.salvar(null));

		assertEquals(0, repositorio.quantidade());
	}

	@Test
	void naoDeveSalvarMesmoClienteDuplicado() {
		repositorio.salvar(cliente);

		assertThrows(IllegalArgumentException.class, () -> repositorio.salvar(cliente));

		assertEquals(1, repositorio.quantidade());

	}

	@Test
	void naoDeveSalvarClienteComEmailDuplicado() {
		repositorio.salvar(cliente);

		Cliente outroCliente = new Cliente("Teste2", "Teste@gmail.com");

		assertThrows(IllegalArgumentException.class, () -> repositorio.salvar(outroCliente));

		assertEquals(1, repositorio.quantidade());
	}

	@Test
	void deveBuscarClientePorId() {

		repositorio.salvar(cliente);

		Optional<Cliente> encontrado = repositorio.buscarPorId(cliente.getId());

		assertTrue(encontrado.isPresent());
		assertSame(cliente, encontrado.orElseThrow());

	}

	@Test
	void deveRetornarOptionalVazioQuandoIdNaoExistir() {
		Optional<Cliente> naoEncontrado = repositorio.buscarPorId(java.util.UUID.randomUUID());

		assertTrue(naoEncontrado.isEmpty());

	}

	@Test
	void deveBuscarClientePorEmail() {
		repositorio.salvar(cliente);

		Optional<Cliente> encontrado = repositorio.buscarPorEmail(cliente.getEmail());

		assertTrue(encontrado.isPresent());
		assertSame(cliente, encontrado.orElseThrow());

	}

	@Test
	void deveListarClientesSemPermitirAlteracaoExterna() {
		repositorio.salvar(cliente);

		List<Cliente> clientes = repositorio.listarTodos();

		assertEquals(1, clientes.size());
		assertThrows(UnsupportedOperationException.class, () -> clientes.clear());
	}

}
