package fundamentos;

public class Main {

	public static void main(String[] args) {
		Produto produto = new Produto("Teclado", 250.00, 10);

		produto.exibirInformacoes();

		produto.vender(3);

		produto.reporEstoque(5);

		produto.reporEstoque(1);

		produto.vender(1);

		produto.exibirInformacoes();

		// Testes

		testarVenda(produto, 0);

		testarVenda(produto, -2);

		testarVenda(produto, 100);

		testarReposicao(produto, 0);

		testarReposicao(produto, -5);

		testarCadastro("", 100, 10);

		testarCadastro("Mouse", -100, 10);

		testarCadastro("Mouse", 100, -10);

		produto.exibirInformacoes();

	}

	private static void testarReposicao(Produto produto, int quantidade) {
		try {
			produto.reporEstoque(quantidade);
			System.out.println("Reposição de estoque efetuada com sucesso!");
		} catch (IllegalArgumentException exception) {
			System.out.println("Erro ao repor estoque do produto: " + exception.getMessage());
		}
	}

	private static void testarVenda(Produto produto, int quantidade) {
		try {
			produto.vender(quantidade);
			System.out.println("Venda cadastrada com sucesso!");
		} catch (IllegalArgumentException | IllegalStateException exception) {
			System.out.println("Erro ao vender o produto: " + exception.getMessage());
		}
	}

	private static void testarCadastro(String nome, double preco, int estoque) {
		try {
			Produto produto = new Produto(nome, preco, estoque);
			System.out.println("Produto Criado com sucesso!");
			produto.exibirInformacoes();

		} catch (IllegalArgumentException exception) {
			System.out.println("Erro ao cadastrar o produto: " + exception.getMessage());
		}
	}
}
