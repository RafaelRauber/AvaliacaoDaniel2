package br.com.estudo.mercado.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.estudo.mercado.model.Cliente;
import br.com.estudo.mercado.repository.ClienteRepository;
import br.com.estudo.mercado.service.ClienteService;
import br.com.estudo.mercado.util.Mensagem;

@ViewScoped
@Named
public class ClienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteRepository repository;
	
	@Inject
	private ClienteService service;
	
	@Inject
	private Mensagem msg;
	
	private List<Cliente> listaDeClientes;
	
	private Cliente cliente;
	
	
	//Métodos
	@PostConstruct
	public void init() {
		this.listaDeClientes = repository.listarClientes();
		cliente = new Cliente();
	}
	
	
	public void cadastrar() {
		if (cliente.getId() != null) {
			msg.info("Cliente Atualizado com sucesso!");
		}else {
			msg.info("Cliente Adicionado com sucesso!");

		}
		service.salvar(cliente);
		atualizarListaDeClientes();
		cliente = new Cliente();
		
	}
	
	
	public void atualizarListaDeClientes() {
		listaDeClientes = repository.listarClientes();
	}
	
	
	public void excluir(Cliente cliente) {
		service.remover(cliente);
		msg.info("Cliente Removido com sucesso!");
		atualizarListaDeClientes();
	}
	
	
	public void editar(Cliente cliente) {
		this.cliente = cliente;
	}
	
	//GETs e SETs
	public List<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}
	
	public void setListaDeClientes(List<Cliente> listaDeClientes) {
		this.listaDeClientes = listaDeClientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
