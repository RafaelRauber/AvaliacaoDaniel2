package br.com.estudo.mercado.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.estudo.mercado.model.Cliente;

public class ClienteRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public ClienteRepository() {}
	
	public ClienteRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	
	
	//Métodos
	public List<Cliente> listarClientes(){
		return manager.createQuery("From Cliente", Cliente.class).getResultList();
	}
	
	
	public Cliente cadastrar(Cliente cliente) {
		return manager.merge(cliente);
	}
	
	
	public Cliente buscarPorId(Long id) {
		return manager.find(Cliente.class, id);
	}
	
	
	public void apagar(Cliente cliente) {
		cliente = buscarPorId(cliente.getId());
		manager.remove(cliente);
	}
	
}
