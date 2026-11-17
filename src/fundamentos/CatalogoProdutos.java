package fundamentos;

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

		for (Produto produtoCadastrado : produtos) {
			if (produtoCadastrado.getNome().equalsIgnoreCase(nome)) {
				return produtoCadastrado;
			}
		}
		return null;
	}

	public void listarTodos() {

		if (produtos.isEmpty()) {
			throw new IllegalArgumentException("O catálogo está vazio.");
		}
		for (Produto produtoCadastrado : produtos) {
			produtoCadastrado.exibirInformacoes();
		}
	}

	public int quantidadeDeProdutos() {

		return produtos.size();

	}

}
