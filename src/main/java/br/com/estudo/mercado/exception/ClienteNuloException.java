package br.com.estudo.mercado.exception;

public class ClienteNuloException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ClienteNuloException(String msg){
		super(msg);
	}
	
}
