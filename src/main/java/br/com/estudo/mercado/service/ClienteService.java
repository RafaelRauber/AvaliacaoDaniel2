package br.com.estudo.mercado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.estudo.mercado.model.Cliente;
import br.com.estudo.mercado.repository.ClienteRepository;
import br.com.estudo.mercado.util.Transacional;

public class ClienteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteRepository repository;
	
	
	
	//Métodos
	@Transacional
	public void salvar(Cliente cliente) {
		repository.cadastrar(cliente);
	}

	@Transacional
	public void remover(Cliente cliente) {
		repository.apagar(cliente);
	}
	
}
