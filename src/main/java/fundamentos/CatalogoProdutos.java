package fundamentos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CatalogoProdutos {

	private final List<Produto> produtos;

	public CatalogoProdutos() {
		this.produtos = new ArrayList<>();
	}

	public void adicionar(Produto produto) {

		if (produto == null) {
			throw new IllegalArgumentException("O produto não pode ser nulo");
		}

		for (Produto produtoCadastrado : produtos) {

			if (produtoCadastrado.getNome().equalsIgnoreCase(produto.getNome())) {
				throw new IllegalArgumentException(
						"Já existe o nome " + produto.getNome() + " cadastrado como produto.");
			}

		}
		produtos.add(produto);
	}

	public Produto buscarPorNome(String nome) {

		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("O nome do Produto não pode ser Nulo ou vazio.");
		}

		String nomeTratado = nome.trim();

		for (Produto produtoCadastrado : produtos) {
			if (produtoCadastrado.getNome().equalsIgnoreCase(nomeTratado)) {
				return produtoCadastrado;
			}
		}
		return null;
	}

	public void listarTodos() {

		if (produtos.isEmpty()) {
			System.out.println("O catálogo está vazio.");
		}
		for (Produto produtoCadastrado : produtos) {
			produtoCadastrado.exibirInformacoes();
		}
	}

	public int quantidadeDeProdutos() {

		return produtos.size();

	}

	public BigDecimal calcularValorTotalCatalogo() {
		BigDecimal total = BigDecimal.ZERO;

		for (Produto produtoCadastrado : produtos) {
			total = total.add(produtoCadastrado.calcularValorEmEstoque());
		}

		return total;
	}

}
