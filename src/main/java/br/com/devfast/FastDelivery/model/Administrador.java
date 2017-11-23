package br.com.devfast.FastDelivery.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Administrador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdministrador;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "login")
	private Login login;

	public Long getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(Long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
