package fundamentos;

import java.math.BigDecimal;

public class Produto {

	private final String nome;
	private final BigDecimal preco;
	private int estoque;

	public Produto(String nome, BigDecimal preco, int estoque) {

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

	public void vender(int quantidade) {

		// Valida Quantidade
		if (quantidade <= 0) {
			throw new IllegalArgumentException("A quantidade da venda precisa ser maior que zero: " + quantidade);
		}

		if (quantidade > estoque) {
			throw new IllegalStateException(
					"Estoque insuficiente. Solicitado: " + quantidade + ", disponível: " + estoque);
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

}