package br.com.devfast.FastDelivery.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.devfast.FastDelivery.model.Historico;

@Repository
public class HistoricoRepositorio {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Historico historico) {
		this.manager.persist(historico);
	}

	public Historico buscar(Long idHistorico) {
		return this.manager.find(Historico.class, idHistorico);
	}

	public List<Historico> listar() {
		TypedQuery<Historico> query = this.manager.createQuery("select h from Historico h", Historico.class);
		return query.getResultList();
	}

	public List<Historico> listarPorRestaurante(Long id) {

		// SELECT * FROM historico as h join entrega as e where e.restaurante = 3 and
		// h.entrega = e.idEntrega;
		TypedQuery<Historico> query = this.manager.createQuery(
				"select h from Historico h join Entrega e on e.restaurante = " + id + " and e.idEntrega = h.entrega",
				Historico.class);

		return query.getResultList();
	}

	public List<Historico> listarPorId(Long idHistorico, Long idRestaure) {

		// SELECT * FROM historico as h join entrega as e where e.restaurante = 1 and
		// h.entrega = e.idEntrega;
		TypedQuery<Historico> query = this.manager
				.createQuery("select h from Historico h join Entrega e on e.restaurante = " + idRestaure
						+ " and e.idEntrega = " + idHistorico, Historico.class);

		return query.getResultList();
	}

}