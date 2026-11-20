package fundamentos;

import java.math.BigDecimal;
import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		Produto teclado = new Produto("Teclado", new BigDecimal("250.00"), 10);

		Produto mouse = new Produto("Mouse", new BigDecimal("100.00"), 20);

		CatalogoProdutos catalogo = new CatalogoProdutos();

		catalogo.adicionar(teclado);
		catalogo.adicionar(mouse);

		System.out.println("=== CATÁLOGO ===");
		catalogo.listarTodos();

		Optional<Produto> produtoEncontrado = catalogo.buscarPorNome("Mouse");

		produtoEncontrado.ifPresent(produto -> {
			System.out.println("=== PRODUTO ENCONTRADO ===");
			produto.exibirInformacoes();
		});

		Pedido pedido = new Pedido();

		pedido.adicionarItem(teclado, 2);
		pedido.adicionarItem(mouse, 3);
		pedido.finalizar();

		System.out.println("=== PEDIDO ===");
		pedido.listarItens();

		System.out.println("Total: R$ " + pedido.calcularTotal().toPlainString());

		System.out.println("Status: " + pedido.getStatus());
	}
}