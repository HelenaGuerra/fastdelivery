package br.com.devfast.FastDelivery.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Restaurante implements Serializable {

	private static final long serialVersionUID = -2933016883430570245L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRestaurante;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "telefone", length = 13, nullable = false)
	private String telefone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "login")
	private Login login;

	@Column(name = "cnpj", nullable = false, length = 18)
	private String cnpj;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco")
	private Endereco endereco;

	private StatusRestaurante status;

	public StatusRestaurante getStatus() {
		return status;
	}

	public void setStatus(StatusRestaurante status) {
		this.status = status;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Restaurante [idRestaurante=" + idRestaurante + ", nome=" + nome + ", telefone=" + telefone + ", login="
				+ login + ", cnpj=" + cnpj + ", endereco=" + endereco + "]";
	}

}
