package fundamentos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private final List<ItemPedido> itens;

	private StatusPedido status;

	public List<ItemPedido> getItens() {
		return List.copyOf(itens);
	}

	public Pedido() {
		this.itens = new ArrayList<>();
		this.status = StatusPedido.ABERTO;
	}

	public StatusPedido getStatus() {
		return status;
	}

	private void validarPedidoAberto() {
		if (status != StatusPedido.ABERTO) {
			throw new IllegalStateException("O pedido não pode ser alterado porque está: " + status);
		}
	}

	public void finalizar() {
		validarPedidoAberto();

		if (itens.isEmpty()) {
			throw new IllegalStateException("Não é possível finalizar um pedido vazio.");
		}

		status = StatusPedido.FINALIZADO;

	}

	public void cancelar() {

		validarPedidoAberto();

		for (ItemPedido item : itens) {
			item.getProduto().reporEstoque(item.getQuantidade());
		}

		status = StatusPedido.CANCELADO;

	}

	public void adicionarItem(Produto produto, int quantidade) {
		validarPedidoAberto();

		if (produto == null) {
			throw new IllegalArgumentException("O produto não pode ser nulo.");
		}

		if (quantidade <= 0) {
			throw new IllegalArgumentException("A quantidade do item deve ser maior que zero: " + quantidade);
		}

		produto.vender(quantidade);

		ItemPedido item = new ItemPedido(produto, quantidade);
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

	public void removerItem(Produto produto) {

		validarPedidoAberto();

		if (produto == null) {
			throw new IllegalArgumentException("O produto não pode ser nulo.");
		}

		ItemPedido itemParaRemover = itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst()
				.orElseThrow(
						() -> new IllegalArgumentException("O produto " + produto.getNome() + " não está no pedido."));

		produto.reporEstoque(itemParaRemover.getQuantidade());

		itens.remove(itemParaRemover);

	}

}
