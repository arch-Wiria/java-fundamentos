package fundamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PedidoTest {

	private Produto produto;
	private Pedido pedido;

	@BeforeEach
	void prepara() {
		produto = new Produto("Headset", new BigDecimal("200.00"), 10);

		pedido = new Pedido();

	}

	@Test
	void deveIniciarComStatusAberto() {
		assertEquals(StatusPedido.ABERTO, pedido.getStatus());
	}

	@Test
	void deveAdicionarItemEReduzirEstoque() {

		pedido.adicionarItem(produto, 3);

		assertEquals(1, pedido.quantidadeDeItens());
		assertEquals(3, pedido.quantidadeTotalDeUnidades());
		assertEquals(7, produto.getEstoque());
		assertEquals(new BigDecimal("600.00"), pedido.calcularTotal());
	}

	@Test
	void deveFinalizarPedidoComItens() {
		pedido.adicionarItem(produto, 2);

		pedido.finalizar();

		assertEquals(StatusPedido.FINALIZADO, pedido.getStatus());

	}

	@Test
	void deveCancelarPedidoEReporEstoque() {
		pedido.adicionarItem(produto, 3);

		pedido.cancelar();

		assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
		assertEquals(10, produto.getEstoque());
	}

	@Test
	void naoDeveFinalizarPedidoVazio() {
		assertThrows(IllegalStateException.class, () -> pedido.finalizar());

		assertEquals(StatusPedido.ABERTO, pedido.getStatus());
	}

	@Test
	void naoDeveAdicionarItemDepoisDeFinalizar() {
		pedido.adicionarItem(produto, 2);
		pedido.finalizar();

		assertThrows(IllegalStateException.class, () -> pedido.adicionarItem(produto, 1));

		assertEquals(1, pedido.quantidadeDeItens());
		assertEquals(8, produto.getEstoque());
	}

	@Test
	void naoDeveReporEstoqueDuasVezesAoCancelar() {
		pedido.adicionarItem(produto, 3);
		pedido.cancelar();

		assertThrows(IllegalStateException.class, () -> pedido.cancelar());

		assertEquals(10, produto.getEstoque());
	}

	@Test
	void naoDeveAdicionarItemQuandoEstoqueForInsuficiente() {
		EstoqueInsuficienteException exception = assertThrows(EstoqueInsuficienteException.class,
				() -> pedido.adicionarItem(produto, 11));

		assertEquals("Estoque insuficiente. Solicitado: 11, disponível: 10", exception.getMessage());

		assertEquals(0, pedido.quantidadeDeItens());
		assertEquals(0, pedido.quantidadeTotalDeUnidades());
		assertEquals(BigDecimal.ZERO, pedido.calcularTotal());
		assertEquals(10, produto.getEstoque());
		assertEquals(StatusPedido.ABERTO, pedido.getStatus());

	}

	@Test
	void deveRetornarCopiaImutavelDosItens() {

		pedido.adicionarItem(produto, 2);

		List<ItemPedido> itensDoPedido = pedido.getItens();

		assertEquals(1, itensDoPedido.size());

		assertThrows(UnsupportedOperationException.class, () -> itensDoPedido.clear());

		assertEquals(1, pedido.quantidadeDeItens());
		assertEquals(2, pedido.quantidadeTotalDeUnidades());
		assertEquals(8, produto.getEstoque());

	}

	@ParameterizedTest
	@ValueSource(ints = { 0, -1, -10 })
	void naoDeveAdicionarItemComQuantidadeInvalida(int quantidade) {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> pedido.adicionarItem(produto, quantidade));

		assertEquals("A quantidade do item deve ser maior que zero: " + quantidade, exception.getMessage());

		assertEquals(0, pedido.quantidadeDeItens());
		assertEquals(0, pedido.quantidadeTotalDeUnidades());
		assertEquals(BigDecimal.ZERO, pedido.calcularTotal());
		assertEquals(10, produto.getEstoque());
		assertEquals(StatusPedido.ABERTO, pedido.getStatus());

	}

}
