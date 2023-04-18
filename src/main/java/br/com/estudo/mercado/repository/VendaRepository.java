package br.com.estudo.mercado.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.estudo.mercado.model.Venda;

public class VendaRepository implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public VendaRepository() {}
	
	public VendaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	
	
	//Métodos
		public List<Venda> listarVendas(){
			return manager.createQuery("From Venda", Venda.class).getResultList();
		}
		
		
		public Venda cadastrar(Venda venda) {
			return manager.merge(venda);
		}
		
		
		public Venda ultimaVenda() {
			String jpql = "SELECT v FROM Venda v ORDER BY v.id DESC";
			return manager.createQuery(jpql, Venda.class)
	                 .setMaxResults(1)
	                 .getSingleResult();
		}
}
