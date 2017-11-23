package br.com.devfast.FastDelivery.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.devfast.FastDelivery.model.Login;
import br.com.devfast.FastDelivery.model.Motoboy;
import br.com.devfast.FastDelivery.model.Restaurante;
import br.com.devfast.FastDelivery.util.geraToken;
import br.com.devfast.FastDelivery.util.hash;

@Repository
public class RestauranteRepositorio {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Restaurante restaurante) {

		restaurante.getLogin().setToken(
				geraToken.geraTokenNovo(restaurante.getLogin().getEmail(), restaurante.getLogin().getSenha()));

		restaurante.getLogin().setSenha(hash.embaralhar(restaurante.getLogin().getSenha()));

		this.manager.persist(restaurante);
	}

	@Transactional
	public void alterar(Restaurante restaurante) {
		this.manager.merge(restaurante);
	}

	@Transactional
	public Restaurante buscar(Long idRestaurante) {
		return this.manager.find(Restaurante.class, idRestaurante);
	}

	@Transactional
	public void excluir(Long idRestaurante) {
		this.manager.remove(this.manager.find(Restaurante.class, idRestaurante));
	}

	public List<Restaurante> listar() {
		TypedQuery<Restaurante> query = this.manager.createQuery("select r from Restaurante r", Restaurante.class);
		return query.getResultList();
	}

	public List<Restaurante> listarAtivados() {
		TypedQuery<Restaurante> query = this.manager.createQuery("select r from Restaurante r where status != 1",
				Restaurante.class);
		return query.getResultList();
	}

	public List<Restaurante> listarDesativados() {
		TypedQuery<Restaurante> query = this.manager.createQuery("select r from Restaurante r where status = 1",
				Restaurante.class);
		return query.getResultList();
	}

	@Transactional
	public Restaurante loginRestaurante(Login login) {

		Restaurante restaurante = new Restaurante();

		restaurante.setLogin(login);

		try {

			String sql = "from Restaurante restaurante where login = " + login.getIdLogin();

			@SuppressWarnings("unchecked")
			TypedQuery<Restaurante> query = (TypedQuery<Restaurante>) manager.createQuery(sql);

			restaurante = query.getSingleResult();

			return restaurante;

		} catch (NoResultException e) {

			Restaurante restaurant = new Restaurante();
			restaurant.setLogin(login);
			return restaurant;

		}

	}

	public Restaurante buscarCnpj(Restaurante restaurante) {
		TypedQuery<Restaurante> query = this.manager
				.createQuery("select r from Restaurante r where cnpj = " + restaurante.getCnpj(), Restaurante.class);
		return query.getSingleResult();
	}

}
