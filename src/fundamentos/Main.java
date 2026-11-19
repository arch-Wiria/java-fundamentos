package fundamentos;

import java.math.BigDecimal;

public class Main {

	public static void main(String[] args) {
		Produto produto = new Produto("Teclado", new BigDecimal("250.00"), 10);

		System.out.println("\n=== TESTES ===");

		produto.exibirInformacoes();

		produto.vender(3);

		produto.reporEstoque(5);

		produto.reporEstoque(1);

		produto.vender(1);

		produto.exibirInformacoes();

		testarVenda(produto, 0);

		testarVenda(produto, -2);

		testarVenda(produto, 100);

		testarReposicao(produto, 0);

		testarReposicao(produto, -5);

		testarCadastro("", new BigDecimal(100), 10);

		testarCadastro("Mouse", new BigDecimal(-100), 10);

		testarCadastro("Mouse", new BigDecimal(100), -10);

		testarCadastro("Mouse", null, 10);

		produto.exibirInformacoes();

		System.out.println("\n=== CATÁLOGO ===");

		CatalogoProdutos catalogo = new CatalogoProdutos();

		Produto teclado = new Produto("Teclado", new BigDecimal("250.00"), 10);

		Produto mouse = new Produto("Mouse", new BigDecimal("100.00"), 20);

		Produto monitor = new Produto("Monitor", new BigDecimal("900.00"), 5);

		catalogo.adicionar(teclado);

		catalogo.adicionar(mouse);

		catalogo.adicionar(monitor);

		catalogo.listarTodos();

		System.out.println("Quantidade de produtos: " + catalogo.quantidadeDeProdutos());

		Produto encontrado = catalogo.buscarPorNome("MOUSE");

		if (encontrado != null) {
			System.out.println("Produto Encontrado:");
			encontrado.exibirInformacoes();
		} else {
			System.out.println("Produto não encontrado.");
		}

		try {

			catalogo.adicionar(new Produto("TECLADO", new BigDecimal("300.00"), 5));

		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}

		try {
			catalogo.adicionar(null);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}

		Produto inexistente = catalogo.buscarPorNome("Notebook");

		if (inexistente == null) {
			System.out.println("Notebook não encontrado.");
		}

		Produto encontradoComEspacos = catalogo.buscarPorNome("    Mouse   ");

		if (encontradoComEspacos != null) {
			System.out.println("Busca com espaços funcionou.");
		}

		CatalogoProdutos catalogoVazio = new CatalogoProdutos();
		catalogoVazio.listarTodos();

		Produto cabo = new Produto("Cabo", new BigDecimal("0.10"), 3);

		System.out.println("Valor do estoque de cabos: R$ " + cabo.calcularValorEmEstoque().toPlainString());

		catalogo.adicionar(cabo);

		System.out.println("Valor total do catálogo: R$ " + catalogo.calcularValorTotalCatalogo().toPlainString());

		System.out.println("\n=== PEDIDO ===");

		Produto tecladoPedido = new Produto("Teclado Gamer", new BigDecimal("250.00"), 10);

		Produto mousePedido = new Produto("Mouse Gamer", new BigDecimal("100.00"), 20);

		Pedido pedido = new Pedido();

		pedido.adicionarItem(tecladoPedido, 2);

		pedido.adicionarItem(mousePedido, 3);

		pedido.listarItens();

		System.out.println("Quantidade de itens: " + pedido.quantidadeDeItens());

		System.out.println("Quantidade de unidades: " + pedido.quantidadeTotalDeUnidades());

		System.out.println("Total do Pedido: R$ " + pedido.calcularTotal().toPlainString());

		System.out.println("\n=== ESTADO FINAL DO PEDIDO ===");

		pedido.listarItens();

		System.out.println("Quantidade de itens: " + pedido.quantidadeDeItens());

		System.out.println("Quantidade de unidades: " + pedido.quantidadeTotalDeUnidades());

		System.out.println("Total: R$ " + pedido.calcularTotal().toPlainString());

		System.out.println("\n=== ESTOQUES FINAIS ===");

		tecladoPedido.exibirInformacoes();
		mousePedido.exibirInformacoes();

		try {
			pedido.adicionarItem(null, 1);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
		try {
			pedido.adicionarItem(tecladoPedido, 0);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
		try {
			pedido.adicionarItem(tecladoPedido, -1);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
		try {
			pedido.adicionarItem(tecladoPedido, 100);
		} catch (IllegalStateException exception) {
			System.out.println(exception.getMessage());
		}

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

	private static void testarCadastro(String nome, BigDecimal preco, int estoque) {
		try {
			Produto produto = new Produto(nome, preco, estoque);
			System.out.println("Produto Criado com sucesso!");
			produto.exibirInformacoes();

		} catch (IllegalArgumentException exception) {
			System.out.println("Erro ao cadastrar o produto: " + exception.getMessage());
		}
	}
}
