package br.com.estudo.mercado.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.estudo.mercado.model.Produto;

public class ProdutoRepository implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public ProdutoRepository() {}
	
	public ProdutoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	
	
	
	//Métodos
	public List<Produto> listarProdutos(){
		return manager.createQuery("From Produto", Produto.class).getResultList();
	}
	
	
	public Produto buscarPorId(Long id) {
		return manager.find(Produto.class, id);
	}
	
	
	public Produto cadastrar(Produto produto) {
		return manager.merge(produto);
	}
	
	
	public void apagar(Produto produto) {
		produto = buscarPorId(produto.getId());
		manager.remove(produto);
	}
	
}
