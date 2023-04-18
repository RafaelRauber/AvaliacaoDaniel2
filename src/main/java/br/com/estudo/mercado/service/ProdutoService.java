package br.com.estudo.mercado.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.estudo.mercado.model.Produto;
import br.com.estudo.mercado.repository.ProdutoRepository;
import br.com.estudo.mercado.util.Transacional;

public class ProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository repository;
	
	
	
	//Métodos
	@Transacional
	public void salvar(Produto produto) {
		repository.cadastrar(produto);
	}
	
	@Transacional
	public void remover(Produto produto) {
		repository.apagar(produto);
	}
	
}
