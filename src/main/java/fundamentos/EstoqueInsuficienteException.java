package fundamentos;

public class EstoqueInsuficienteException extends IllegalStateException {

	public EstoqueInsuficienteException(int quantidadeSolicitada, int estoqueDisponivel) {

		super("Estoque insuficiente. Solicitado: " 
		+ quantidadeSolicitada 
		+ ", disponível: " 
		+ estoqueDisponivel);

	}

}
