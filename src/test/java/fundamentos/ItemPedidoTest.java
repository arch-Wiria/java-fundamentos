package fundamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ItemPedidoTest {

	private Produto produto;

	@BeforeEach
	void preparar() {
		produto = new Produto("Teclado", new BigDecimal("250.00"), 10);

	}

	@Test
	void deveCriarItemValido() {
		ItemPedido item = new ItemPedido(produto, 2);

		assertSame(produto, item.getProduto());
		assertEquals(2, item.getQuantidade());

	}

	@Test
	void deveCalcularSubtotal() {

		ItemPedido item = new ItemPedido(produto, 3);

		assertEquals(new BigDecimal("750.00"), item.calcularSubtotal());

	}

	@Test
	void naoDeveCriarItemComProdutoNulo() {

		assertThrows(IllegalArgumentException.class, () -> new ItemPedido(null, 1));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, -1 })
	void naoDeveCriarItemComQuantidadeInvalida(int quantidade) {
		assertThrows(IllegalArgumentException.class, () -> new ItemPedido(produto, quantidade));
	}

}