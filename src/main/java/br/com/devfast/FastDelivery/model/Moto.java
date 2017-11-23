package br.com.devfast.FastDelivery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Moto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMoto;
	@Column(name = "ano", nullable = false, length = 4)
	private String ano;
	@Column(name = "modelo", nullable = false, length = 20)
	private String modelo;
	@Column(name = "cor", nullable = false, length = 15)
	private String cor;
	@Column(name = "marca", nullable = false, length = 15)
	private String marca;
	@Column(name = "placa", nullable = false, length = 8)
	private String placa;

	public Long getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(Long idMoto) {
		this.idMoto = idMoto;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
