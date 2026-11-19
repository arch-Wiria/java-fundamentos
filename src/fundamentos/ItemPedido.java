package fundamentos;

import java.math.BigDecimal;

public class ItemPedido {

	private final Produto produto;
	private final int quantidade;
	private final BigDecimal precoUnitario;

	public ItemPedido(Produto produto, int quantidade) {

		if (produto == null) {
			throw new IllegalArgumentException("Produto Inválido.");
		}
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Produto " + produto.getNome() + " precisa ter quantidade maior que 0.");
		}

		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = produto.getPreco();
	}

	public BigDecimal calcularSubtotal() {
		return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void exibirInformacoes() {
		System.out.println("Produto: " + produto.getNome());
		System.out.println("Quantidade: " + quantidade);
		System.out.println("Preço unitário: R$ " + precoUnitario.toPlainString());
		System.out.println("Subtotal: R$ " + calcularSubtotal().toPlainString());
	}

}
