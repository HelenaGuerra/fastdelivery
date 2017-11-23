package br.com.devfast.FastDelivery.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.devfast.FastDelivery.model.Login;
import br.com.devfast.FastDelivery.model.Motoboy;
import br.com.devfast.FastDelivery.util.geraToken;
import br.com.devfast.FastDelivery.util.hash;

@Repository
public class LoginRepositorio {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Login login) {

		login.setToken(geraToken.geraTokenNovo(login.getEmail(), login.getSenha()));

		this.manager.persist(login);

	}

	public Login buscar(Long idLogin) {
		return this.manager.find(Login.class, idLogin);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Login logar(Login login) {

		login.setSenha(hash.embaralhar(login.getSenha()));

		System.out.println("teste, senha hashada " + login.getSenha());

		try {

			TypedQuery<Login> query = (TypedQuery<Login>) manager
					.createQuery("FROM Login login where email = :email and senha = :senha");

			query.setParameter("email", login.getEmail());
			query.setParameter("senha", login.getSenha());

			login = query.getSingleResult();

			return login;

		} catch (NullPointerException e) {
			new RuntimeException(e.getMessage());

		}

		return null;

	}

	@SuppressWarnings("unchecked")
	@Transactional

	public Motoboy bucarCPF(String cpf) {

		Motoboy motoboy = new Motoboy();

		String sql = "from Motoboy motoboy where cpf = " + cpf;

		TypedQuery<Motoboy> query = (TypedQuery<Motoboy>) manager.createQuery(sql);

		motoboy = query.getSingleResult();

		return motoboy;

	}

	@Transactional
	public void alterar(Login login) {
		this.manager.merge(login);
	}

}
