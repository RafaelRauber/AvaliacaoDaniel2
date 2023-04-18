package br.com.estudo.mercado.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.estudo.mercado.exception.CarrinhoException;
import br.com.estudo.mercado.exception.ClienteNuloException;
import br.com.estudo.mercado.model.Cliente;
import br.com.estudo.mercado.model.Produto;
import br.com.estudo.mercado.model.Venda;
import br.com.estudo.mercado.repository.ClienteRepository;
import br.com.estudo.mercado.repository.ProdutoRepository;
import br.com.estudo.mercado.repository.VendaRepository;
import br.com.estudo.mercado.service.VendaService;
import br.com.estudo.mercado.util.Mensagem;

@Named
@ViewScoped
public class VendaController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private VendaRepository vendaRepository;
	
	@Inject
	private ProdutoRepository produtoRepository;
	
	@Inject
	private ClienteRepository clienteRepository;
	
	@Inject
	private VendaService vendaService;
	
	@Inject
	private Mensagem msg;
	
	private List<Cliente> listaDeClientes;
	
	private List<Produto> listaDeProdutos;
	
	private List<Produto> carrinho = new ArrayList<Produto>();
	
	private Venda venda = new Venda();
	
	private Produto produto;
	
	private Cliente cliente;
	
	private BigDecimal total = new BigDecimal("0");
	
	private Long produtoIdSelecionado;
	
	private Long clienteIdSelecionado;
	
	
	
	
	//Métodos
	@PostConstruct
	public void init() {
		listaDeClientes = clienteRepository.listarClientes();
		listaDeProdutos = produtoRepository.listarProdutos();
	}
	
	
	public void selecionarCliente() {
		Cliente clienteSelecionado = clienteRepository.buscarPorId(clienteIdSelecionado);
		venda.setCliente(clienteSelecionado);
	}
	
	
	public void selecionarProduto() {
		Produto produtoSelecionado = produtoRepository.buscarPorId(produtoIdSelecionado);
		setCarrinho(produtoSelecionado);
		calcularTotal(produtoSelecionado.getPreco());
		
		if (carrinho.size() == 0) {
			msg.info("Nenhum produto Selecionado");
		}
	}


	public void calcularTotal(BigDecimal valor) {
		setTotal(total.add(valor));
	}
	
	
	public void removerDoCarrinho(Produto produto) {
		setTotal(total.subtract(produto.getPreco()));
		carrinho.remove(produto);
		msg.info("Produto removido");
	}
	
	
	public String finalizarVenda() {
		try {
			venda.setProdutos(carrinho);
			
			if (venda.getCliente() == null) {
				throw new ClienteNuloException("O cliente é obrigatório");
			}
			
			if (venda.getProdutos().size() == 0) {
				throw new CarrinhoException("O carrinho não pode estar vazio");
			}
			
			msg.info("Venda concluida!");
			vendaService.salvar(venda);
			limparVenda();
			
			//Thread.sleep(5000);
			
			return "resumo?faces-redirect=true";
			
		} catch (ClienteNuloException e) {
			msg.erro("O cliente é obrigatório");
			return "";
		} catch(CarrinhoException e) {
			msg.erro("O carrinho não pode estar vazio");
			return "";
		} catch (IllegalArgumentException e) {
			msg.erro("Erro ao finalizar a venda");
			return "";
		} catch (Exception e) {
			msg.erro("Erro ao finalizar a venda");
			return "";
		}
	}
	
	
	public void limparCarrinho() {
		limparVenda();
		msg.info("Carrinho esvaziado");
	}
	
	
	public Venda getUltimaVenda() {
		return vendaRepository.ultimaVenda();
	}
	
	
	public BigDecimal getTotalUltimaVenda() {
		Venda venda = vendaRepository.ultimaVenda();
		BigDecimal resumoTotal = new BigDecimal("0");
		
		List<Produto> produtos = venda.getProdutos();
		
		for (Produto produto : produtos) {
			resumoTotal = resumoTotal.add(produto.getPreco());
		}
		
		
		return resumoTotal;
	}
	
	
	public void limparVenda() {
		carrinho = new ArrayList<Produto>();
		venda = new Venda();
		total = new BigDecimal("0");
	}
	
	
	
	
	
	//GETs e SETs
	public List<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}


	public void setListaDeClientes(List<Cliente> listaDeClientes) {
		this.listaDeClientes = listaDeClientes;
	}


	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}


	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}


	public List<Produto> getCarrinho() {
		return carrinho;
	}


	public void setCarrinho(Produto produto) {
		this.carrinho.add(produto);
	}


	public Venda getVenda() {
		return venda;
	}


	public void setVenda(Venda venda) {
		this.venda = venda;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public Long getProdutoIdSelecionado() {
		return produtoIdSelecionado;
	}


	public void setProdutoIdSelecionado(Long produtoIdSelecionado) {
		this.produtoIdSelecionado = produtoIdSelecionado;
	}


	public Long getClienteIdSelecionado() {
		return clienteIdSelecionado;
	}


	public void setClienteIdSelecionado(Long clienteIdSelecionado) {
		this.clienteIdSelecionado = clienteIdSelecionado;
	}
	
	
}
