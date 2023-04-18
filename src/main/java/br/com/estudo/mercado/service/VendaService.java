package br.com.estudo.mercado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.estudo.mercado.model.Venda;
import br.com.estudo.mercado.repository.VendaRepository;
import br.com.estudo.mercado.util.Transacional;

public class VendaService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private VendaRepository repository;
	
	
	//Métodos
	@Transacional
	public void salvar(Venda venda) {
		repository.cadastrar(venda);
	}
	
}
