package fundamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatalogoProdutosTest {

	private CatalogoProdutos catalogo;
	private Produto teclado;
	private Produto mouse;

	@BeforeEach
	void preparar() {
		catalogo = new CatalogoProdutos();

		teclado = new Produto("Teclado", new BigDecimal("250.00"), 10);

		mouse = new Produto("Mouse", new BigDecimal("100.00"), 20);

	}

	@Test
	void deveAdicionarProduto() {
		catalogo.adicionar(teclado);

		assertEquals(1, catalogo.quantidadeDeProdutos());

	}

	@Test
	void naoDeveAdicionarProdutoNulo() {
		assertThrows(IllegalArgumentException.class, () -> catalogo.adicionar(null));

		assertEquals(0, catalogo.quantidadeDeProdutos());

	}

	@Test
	void naoDeveAdicionarProdutoComNomeDuplicadoIgnorandoMaiusculas() {
		catalogo.adicionar(teclado);

		Produto duplicado = new Produto("TECLADO", new BigDecimal("300.00"), 5);

		assertThrows(IllegalArgumentException.class, () -> catalogo.adicionar(duplicado));

		assertEquals(1, catalogo.quantidadeDeProdutos());

	}

	@Test
	void deveBuscarProdutoPorNomeIgnorandoMaiusculasEMinusculas() {
		catalogo.adicionar(mouse);
		
		Optional<Produto> encontrado = catalogo.buscarPorNome("MOUSE");

		assertTrue(encontrado.isPresent());
		assertSame(mouse, encontrado.orElseThrow());

	}

	@Test
	void deveRetornarOptionalVazioQuandoProdutoNaoExistir() {
		Optional<Produto> encontrado = catalogo.buscarPorNome("NoteBook");

		assertTrue(encontrado.isEmpty());

	}

	@Test
	void deveBuscarProdutoRemovendoEspacosDoNome() {
		catalogo.adicionar(mouse);
		Optional<Produto> encontrado = catalogo.buscarPorNome("   Mouse   ");

		assertTrue(encontrado.isPresent());
		assertSame(mouse, encontrado.orElseThrow());

	}

	@Test
	void naoDeveBuscarComNomeInvalido() {
		assertThrows(IllegalArgumentException.class, () -> catalogo.buscarPorNome(null));

		assertThrows(IllegalArgumentException.class, () -> catalogo.buscarPorNome("   "));
	}

	@Test
	void deveCalcularValorTotalDoCatalogo() {
		catalogo.adicionar(teclado);
		catalogo.adicionar(mouse);

		assertEquals(new BigDecimal("4500.00"), catalogo.calcularValorTotalCatalogo());
	}

}
