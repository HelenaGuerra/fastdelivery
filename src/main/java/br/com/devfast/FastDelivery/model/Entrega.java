package br.com.devfast.FastDelivery.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Entrega implements Serializable {

	private static final long serialVersionUID = -3328764395636951953L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEntrega;

	@Column(name = "descricao", nullable = true, length = 100)
	private String descricao;

	@Column(name = "valorTotal", nullable = false, length = 100)
	private double valorTotal;

	@Column(name = "telefoneCliente", nullable = false, length = 15)
	private String telefoneCliente;

	@Column(name = "nomeCliente", nullable = false, length = 100)
	private String nomeCliente;

	@Column(name = "tamanho", nullable = false)
	private Tamanho tamanho;

	@Column(name = "temperatura", nullable = false)
	private NivelTemperatura temperatura;

	@Column(name = "fragilidade", nullable = false)
	private NivelFragilidade fragilidade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurante")
	private Restaurante restaurante;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco")
	private Endereco endereco;

	@Column(name = "statusEntrega", nullable = false)
	private EstadoEntrega statusEntrega;

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getIdEntrega() {
		return idEntrega;
	}

	public Restaurante getEntrega() {
		return restaurante;
	}

	public void setEntrega(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public void setIdEntrega(Long idEntrega) {
		this.idEntrega = idEntrega;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public NivelFragilidade getFragilidade() {
		return fragilidade;
	}

	public void setFragilidade(NivelFragilidade fragilidade) {
		this.fragilidade = fragilidade;
	}

	public NivelTemperatura getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(NivelTemperatura temperatura) {
		this.temperatura = temperatura;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public EstadoEntrega getStatusEntrega() {
		return statusEntrega;
	}

	public void setStatusEntrega(EstadoEntrega statusEntrega) {
		this.statusEntrega = statusEntrega;
	}

}
