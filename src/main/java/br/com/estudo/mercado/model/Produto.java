package br.com.estudo.mercado.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "Código é obrigatório")
	private String codigo;
	
	@Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
	private String descricao;
	
	@NotNull(message = "Preço é obrigatório")
	@Min(value = 0, message = "Preço deve ser positivo ou zero")
	@Digits(integer = 10, fraction = 2, message = "Preço deve ser um número com no máximo 10 dígitos antes da vírgula e 2 dígitos após a vírgula")
	private BigDecimal preco;

	
	// GETs e SETs
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
}
