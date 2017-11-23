package br.com.devfast.FastDelivery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLogin;

	@Column(name = "email", unique = true, nullable = false, length = 120)
	private String email;

	@Column(name = "senha", length = 65, nullable = false)
	private String senha;

	@Column(name = "token", length = 25, nullable = true)
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Login [idLogin=" + idLogin + ", email=" + email + ", senha=" + senha + "]";
	}

	public Login(Login login) {

		this.email = login.getEmail();
		this.senha = login.getSenha();
		this.token = login.getToken();

	}

	public Login() {

	}

}
