package fundamentos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

		EstoqueInsuficienteException exception = assertThrows(EstoqueInsuficienteException.class,
				() -> produto.vender(6));

		assertEquals("Estoque insuficiente. Solicitado: 6, disponível: 5", exception.getMessage());

		assertEquals(5, produto.getEstoque());

	}

	@Test
	void deveCalcularValorTotalEmEstoque() {
		Produto produto = new Produto("Cabo", new BigDecimal("0.10"), 3);

		assertEquals(new BigDecimal("0.30"), produto.calcularValorEmEstoque());

	}

	@Test
	void deveAceitarEstoqueInicialZero() {
		Produto produto = new Produto("Teclado", new BigDecimal("250.00"), 0);

		assertEquals(0, produto.getEstoque());
	}

	@Test
	void naoDeveCriarProdutoComNomeNulo() {
		assertThrows(IllegalArgumentException.class, () -> new Produto(null, new BigDecimal("250.00"), 10));
	}

	@Test
	void naoDeveCriarProdutoComPrecoNulo() {
		assertThrows(IllegalArgumentException.class, () -> new Produto("Teclado", null, 10));
	}

	@ParameterizedTest
	@ValueSource(strings = { "0", "-1.00" })
	void naoDeveCriarProdutoComPrecoInvalido(String valor) {
		BigDecimal preco = new BigDecimal(valor);

		assertThrows(IllegalArgumentException.class, () -> new Produto("Teclado", preco, 10));
	}

	@Test
	void naoDeveCriarProdutoComEstoqueNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Produto("Teclado", new BigDecimal("250.00"), -1));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, -1 })
	void naoDeveVenderQuantidadeInvalida(int quantidade) {
		Produto produto = new Produto("Teclado", new BigDecimal("250.00"), 10);

		assertThrows(IllegalArgumentException.class, () -> produto.vender(quantidade));

		assertEquals(10, produto.getEstoque());
	}

	@Test
	void deveReporEstoque() {
		Produto produto = new Produto("Teclado", new BigDecimal("250.00"), 10);

		produto.reporEstoque(5);

		assertEquals(15, produto.getEstoque());
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, -1 })
	void naoDeveReporQuantidadeInvalida(int quantidade) {
		Produto produto = new Produto("Teclado", new BigDecimal("250.00"), 10);

		assertThrows(IllegalArgumentException.class, () -> produto.reporEstoque(quantidade));

		assertEquals(10, produto.getEstoque());
	}

	@Test
	void deveGerarIdentificadorAoCriarProduto() {
		Produto produto = new Produto("Mouse", new BigDecimal("100.00"), 5);

		assertNotNull(produto.getId());

	}

	@Test
	void produtosComIdsDiferentesNaoDevemSerIguais() {

		Produto primeiro = new Produto("Mouse", new BigDecimal("100.00"), 5);

		Produto segundo = new Produto("Mouse", new BigDecimal("100.00"), 5);

		assertNotEquals(primeiro.getId(), segundo.getId());
		assertNotEquals(primeiro, segundo);

	}

	@Test
	void produtoComMesmoIdentificadorDevemSerIguais() {
		UUID id = UUID.randomUUID();

		Produto primeiro = new Produto(id, "Mouse", new BigDecimal("100.00"), 5);

		Produto segundo = new Produto(id, "Mouse Atualizado", new BigDecimal("150.00"), 8);

		assertEquals(primeiro, segundo);
		assertEquals(primeiro.hashCode(), segundo.hashCode());

	}

	@Test
	void produtoNaoDeveSerIgualANuloOuOutroTipo() {
		Produto produto = new Produto("Mouse", new BigDecimal("100.00"), 5);

		assertEquals(produto, produto);
		assertNotEquals(produto, null);
		assertNotEquals(produto, "Mouse");

	}

	@Test
	void naoDeveCriarProdutoComIdentificadorNulo() {
		assertThrows(IllegalArgumentException.class, () ->
		new Produto(null, "Mouse", new BigDecimal("100.00"), 5));
	}

}