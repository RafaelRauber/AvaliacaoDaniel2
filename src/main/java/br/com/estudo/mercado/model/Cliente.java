package br.com.estudo.mercado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Código é obrigatório")
	private String codigo;
	
	@Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@Size(max = 100, min = 0, message = "Endereço deve ter no máximo 100 caracteres")
	private String endereco;
	
	@Pattern(regexp="^$|^\\d{9}$|^(\\d{2})\\d{9}$", message = "Telefone inválido")
	private String telefone;
	
	@Email(message = "E-mail inválido")
	@Column(nullable = true)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
