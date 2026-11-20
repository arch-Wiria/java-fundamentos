package fundamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProdutoTest {

	@Test
	void deveCriarProdutoValido() {
		Produto produto = new Produto("Teclado", new BigDecimal("250.00"), 10);

		assertEquals("Teclado", produto.getNome());
		assertEquals(new BigDecimal("250.00"), produto.getPreco());
		assertEquals(10, produto.getEstoque());
	}

	@Test
	void deveRecusarNomeEmBranco() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Produto("   ", new BigDecimal("250.00"), 10));

		assertEquals("O nome precisa ser um valor existente.", exception.getMessage());
	}

	@Test
	void deveReduzirEstoqueAoVender() {
		Produto produto = new Produto("Mouse", new BigDecimal("100.00"), 10);

		produto.vender(3);

		assertEquals(7, produto.getEstoque());

	}

	@Test
	void naoDeveAlterarEstoqueQuandoVendaForMaiorQueDisponivel() {
		Produto produto = new Produto("Monitor", new BigDecimal("900.00"), 5);

		assertThrows(IllegalStateException.class, () -> produto.vender(6));

		assertEquals(5, produto.getEstoque());

	}

	@Test
	void deveCalcularValorTotalEmEstoque() {
		Produto produto = new Produto("Cabo", new BigDecimal("0.10"), 3);

		assertEquals(new BigDecimal("0.30"), produto.calcularValorEmEstoque());

	}

}