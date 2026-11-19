package fundamentos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private final List<ItemPedido> itens;

	public Pedido() {
		this.itens = new ArrayList<>();
	}

	public void adicionarItem(Produto produto, int quantidade) {

		ItemPedido item = new ItemPedido(produto, quantidade);

		produto.vender(quantidade);

		itens.add(item);

	}

	public BigDecimal calcularTotal() {

		BigDecimal total = BigDecimal.ZERO;

		for (ItemPedido item : itens) {

			total = total.add(item.calcularSubtotal());

		}
		return total;
	}

	public int quantidadeTotalDeUnidades() {
		int total = 0;

		for (ItemPedido item : itens) {
			total += item.getQuantidade();
		}
		return total;
	}

	public int quantidadeDeItens() {
		return itens.size();
	}

	public void listarItens() {
		if (itens.isEmpty()) {
			System.out.println("O pedido não possui Itens.");
			return;
		}

		for (ItemPedido item : itens) {
			item.exibirInformacoes();
			System.out.println("--------------------");
		}
	}

}
