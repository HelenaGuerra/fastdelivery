package br.com.devfast.FastDelivery.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.devfast.FastDelivery.model.Historico;
import br.com.devfast.FastDelivery.model.Login;
import br.com.devfast.FastDelivery.model.Motoboy;
import br.com.devfast.FastDelivery.model.Restaurante;
import br.com.devfast.FastDelivery.util.geraToken;
import br.com.devfast.FastDelivery.util.hash;

@Repository
public class MotoboyRepositorio {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Motoboy motoboy) {
		motoboy.getLogin()
				.setToken(geraToken.geraTokenNovo(motoboy.getLogin().getEmail(), motoboy.getLogin().getSenha()));
		motoboy.getLogin().setSenha(hash.embaralhar(motoboy.getLogin().getSenha()));
		this.manager.persist(motoboy);
	}

	@Transactional
	public void alterar(Motoboy motoboy) {
		this.manager.merge(motoboy);
	}

	@Transactional
	public void excluir(Long idMotoboy) {
		this.manager.remove(this.manager.find(Motoboy.class, idMotoboy));
	}

	public Motoboy buscar(Long idMotoboy) {
		return this.manager.find(Motoboy.class, idMotoboy);
	}

	public List<Motoboy> listarAtivados() {
		TypedQuery<Motoboy> query = this.manager.createQuery("select m from Motoboy m where statusMotoboy != 3",
				Motoboy.class);
		return query.getResultList();
	}

	public List<Motoboy> listarDesativados() {
		TypedQuery<Motoboy> query = this.manager.createQuery("select m from Motoboy m where statusMotoboy = 3",
				Motoboy.class);
		return query.getResultList();
	}

	public List<Historico> listarMonitoramento(Long idRestaurante) {

		// SELECT * FROM motoboy as m inner join historico as h on h.motoboy =
		// m.idMotoboy left join entrega as e on h.entrega = e.idEntrega left join
		// restaurante as r on e.restaurante = 1 and r.idRestaurante = 1;
		TypedQuery<Historico> query = this.manager.createQuery(
				"select h from Historico h inner Join Motoboy m on h.motoboy = m.idMotoboy inner join Entrega e on"
						+ " h.entrega = e.idEntrega" + " inner join Restaurante r on r.idRestaurante = "
						+ idRestaurante,
				Historico.class);
		return query.getResultList();
	}

	@Transactional
	public Motoboy loginMotoboy(Login login) {

		Motoboy motoboy = new Motoboy();

		motoboy.setLogin(login);

		try {

			String sql = "from Motoboy motoboy where login = " + login.getIdLogin();

			@SuppressWarnings("unchecked")
			TypedQuery<Motoboy> query = (TypedQuery<Motoboy>) manager.createQuery(sql);

			motoboy = query.getSingleResult();

			return motoboy;

		} catch (NoResultException e) {

			Motoboy foodBoy = new Motoboy();
			foodBoy.setLogin(login);
			return foodBoy;

		}

	}

}
