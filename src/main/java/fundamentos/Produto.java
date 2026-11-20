package fundamentos;

import java.math.BigDecimal;
import java.util.UUID;

public class Produto {

	private final UUID id;
	private final String nome;
	private final BigDecimal preco;
	private int estoque;

	public Produto(String nome, BigDecimal preco, int estoque) {
		this(UUID.randomUUID(), nome, preco, estoque);
	}

	public Produto(UUID id, String nome, BigDecimal preco, int estoque) {

		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("O nome precisa ser um valor existente.");
		}
		if (preco == null) {
			throw new IllegalArgumentException("O preço não pode ser nulo.");
		}
		if (preco.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O preço precisa ser maior que 0.");
		}
		if (estoque < 0) {
			throw new IllegalArgumentException("O estoque não pode ser negativo " + estoque);
		}
		if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo.");
		}

		this.id = id;
		this.nome = nome.trim();
		this.preco = preco;
		this.estoque = estoque;

	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public UUID getId() {
		return id;
	}

	public void vender(int quantidade) {

		// Valida Quantidade
		if (quantidade <= 0) {
			throw new IllegalArgumentException("A quantidade da venda precisa ser maior que zero: " + quantidade);
		}

		if (quantidade > estoque) {
			throw new EstoqueInsuficienteException(quantidade, estoque);
		}
		estoque -= quantidade;

	}

	public void reporEstoque(int quantidade) {

		// Valida quantidade
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Estoque - A quantidade precisa ser um valor positivo - " + quantidade);
		}
		estoque += quantidade;

	}

	public BigDecimal calcularValorEmEstoque() {

		return preco.multiply(BigDecimal.valueOf(estoque));

	}

	public void exibirInformacoes() {

		System.out.println("Produto: " + nome);
		System.out.println("Preço: R$" + preco.toPlainString());
		System.out.println("Estoque: " + estoque);
		System.out.println("Valor total em estoque: R$" + calcularValorEmEstoque().toPlainString());
	}

	@Override
	public boolean equals(Object objeto) {

		if (this == objeto) {
			return true;
		}

		if (!(objeto instanceof Produto outroProduto)) {
			return false;
		}
		return id.equals(outroProduto.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}