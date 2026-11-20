package fundamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
