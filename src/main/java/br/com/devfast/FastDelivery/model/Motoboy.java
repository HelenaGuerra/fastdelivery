package br.com.devfast.FastDelivery.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Entity
public class Motoboy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMotoboy;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "login")
	private Login login;

	@Lob
	private byte[] arquivo;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	@Column(name = "telefone", length = 13, nullable = false)
	private String telefone;

	@Column(name = "cnh", nullable = false, length = 11)
	private Long cnh;

	@Column(name = "cpf", nullable = false, length = 14)
	private String cpf;

	@Column(name = "statusMotoboy", length = 15)
	private StatusMotoboy status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "moto")
	private Moto moto;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco")
	private Endereco endereco;

	public Long getIdMotoboy() {
		return idMotoboy;
	}

	public void setIdMotoboy(Long idMotoboy) {
		this.idMotoboy = idMotoboy;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
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

	public Long getCnh() {
		return cnh;
	}

	public void setCnh(Long cnh) {
		this.cnh = cnh;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public StatusMotoboy getStatus() {
		return status;
	}

	public void setStatus(StatusMotoboy status) {
		this.status = status;
	}

	public String getArquivo64() {
		@SuppressWarnings("restriction")
		String f = Base64.encode(arquivo);
		return f;
	}
}
