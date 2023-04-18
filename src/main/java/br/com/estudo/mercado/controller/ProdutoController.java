package br.com.estudo.mercado.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.estudo.mercado.model.Produto;
import br.com.estudo.mercado.repository.ProdutoRepository;
import br.com.estudo.mercado.service.ProdutoService;
import br.com.estudo.mercado.util.Mensagem;

@Named
@ViewScoped
public class ProdutoController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository repository;
	
	@Inject 
	private ProdutoService service;
	
	@Inject
	private Mensagem msg;
	
	private Produto produto;
	
	private List<Produto> listaDeProdutos;

	
	//Métodos
	@PostConstruct
	public void init() {
		listaDeProdutos = repository.listarProdutos();
		produto = new Produto();
	}
	
	public void cadastrar() {
		if (produto.getId() != null) {
			msg.info("Produto Atualizado com sucesso!");
		}else {
			msg.info("Produto Adicionado com sucesso!");

		}
		service.salvar(produto);
		atualizarListaDeProdutos();
		produto = new Produto();
	}
	
	
	public void atualizarListaDeProdutos() {
		listaDeProdutos = repository.listarProdutos();
	}
	
	
	public void excluir(Produto produto) {
		service.remover(produto);
		msg.info("Produto Removido com sucesso!");
		atualizarListaDeProdutos();
	}
	
	
	public void editar(Produto produto) {
		this.produto = produto;
	}
	
	
	
	
	//GETs e SETs
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
	
	
}
