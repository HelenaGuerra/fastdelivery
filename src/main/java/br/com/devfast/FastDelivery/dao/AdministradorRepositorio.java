package br.com.devfast.FastDelivery.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.devfast.FastDelivery.model.Administrador;

@Repository
public class AdministradorRepositorio {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Administrador administrador) {
		this.manager.persist(administrador);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public Long[] countDados() {

		Long[] total = new Long[5];

		TypedQuery<Long> motoboys = (TypedQuery<Long>) manager
				.createQuery("SELECT COUNT(m) from Motoboy m where statusMotoboy != 3", Long.class);

		total[0] = motoboys.getSingleResult();

		TypedQuery<Long> restaurantes = (TypedQuery<Long>) manager.createQuery("SELECT COUNT(r) from Restaurante r",
				Long.class);

		total[1] = restaurantes.getSingleResult();

		TypedQuery<Long> entregasRealizadas = (TypedQuery<Long>) manager
				.createQuery("SELECT COUNT(h) from Historico h where estadoSolicitacao = 0", Long.class);

		//

		total[2] = entregasRealizadas.getSingleResult();

		TypedQuery<Long> entregasRecusadas = (TypedQuery<Long>) manager
				.createQuery("SELECT COUNT(h) from Historico h where estadoSolicitacao = 1", Long.class);

		//

		total[3] = entregasRecusadas.getSingleResult();

		TypedQuery<Long> entregasTotal = (TypedQuery<Long>) manager.createQuery("SELECT COUNT(h) from Historico h",
				Long.class);

		total[4] = entregasTotal.getSingleResult();

		return total;
	}

}
