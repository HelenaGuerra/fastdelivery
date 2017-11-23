package br.com.devfast.FastDelivery.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 8510073616405133766L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEndereco;

	@Column(name = "cidade", nullable = false, length = 50)
	private String cidade;
	@Column(name = "estado", nullable = false, length = 2)
	private String estado;
	@Column(name = "numero", nullable = false, length = 20)
	private String numero;
	@Column(name = "bairro", nullable = false, length = 50)
	private String bairro;
	@Column(name = "logradouro", nullable = false, length = 250)
	private String logradouro;
	@Column(name = "complemento", nullable = false, length = 80)
	private String complemento;
	@Column(name = "cep", nullable = false, length = 9)
	private String cep;

	@Column(name = "latitude", length = 15)
	private Double latitude;

	@Column(name = "longitude", length = 15)
	private Double longitude;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
