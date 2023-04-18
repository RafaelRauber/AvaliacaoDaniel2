package br.com.estudo.mercado.exception;

public class CarrinhoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CarrinhoException(String mensagem) {
		super(mensagem);
	}
}
