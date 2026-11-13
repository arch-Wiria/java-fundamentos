package fundamentos;

public class Produto {

	private String nome;
	private double preco;
	private int estoque;

	public Produto(String nome, double preco, int estoque) {

		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("O nome precisa ser um valor existente.");
		}
		if (preco <= 0) {
			throw new IllegalArgumentException("O preço precisa ser maior que 0.");
		}
		if (estoque < 0) {
			throw new IllegalArgumentException("O estoque precisa ser maior que 0.");
		}

		this.nome = nome;
		this.preco = preco;
		this.estoque = estoque;

	}

	public void vender(int quantidade) {

		// Valida Quantidade
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Venda - A Quantidade precisa ser maior que 0 | " + quantidade);
		}

		if (quantidade > estoque) {
			throw new IllegalStateException(
					"Venda - A Quantidade: " + quantidade + " é menor que o estoque: " + estoque);
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

	public double calcularValorEmEstoque() {

		return preco * estoque;

	}

	public void exibirInformacoes() {

		System.out.println("Produto: " + nome);
		System.out.println("Preço: R$" + preco);
		System.out.println("Estoque: " + estoque);
		System.out.println("Valor total em estoque: R$" + calcularValorEmEstoque());
	}

}