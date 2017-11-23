package br.com.devfast.FastDelivery.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Historico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHistorico;

	@Type(type = "timestamp")
	@Column(name = "inicio", nullable = false)
	private Timestamp inicio;

	@Type(type = "timestamp")
	@Column(name = "fim")
	private Timestamp fim;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "estadoSolicitacao", nullable = false)
	private EstadoEntrega estado;

	@ManyToOne
	@JoinColumn(name = "entrega")
	private Entrega entrega;

	@OneToOne
	@JoinColumn(name = "motoboy")
	private Motoboy motoboy;

	public Long getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public Motoboy getMotoboy() {
		return motoboy;
	}

	public void setMotoboy(Motoboy motoboy) {
		this.motoboy = motoboy;
	}

	public Timestamp getInicio() {
		return inicio;
	}

	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}

	public Timestamp getFim() {
		return fim;
	}

	public void setFim(Timestamp fim) {
		this.fim = fim;
	}

	public EstadoEntrega getEstado() {
		return estado;
	}

	public void setEstado(EstadoEntrega estado) {
		this.estado = estado;
	}

}
