package br.com.devfast.FastDelivery.dao;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.devfast.FastDelivery.model.Entrega;
import br.com.devfast.FastDelivery.model.Motoboy;

@Repository
public class EntregaRepositorio {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Entrega entrega) {
		this.manager.persist(entrega);
	}

	public Entrega buscar(Long idEntrega) {
		return this.manager.find(Entrega.class, idEntrega);
	}

	public List<Entrega> listarEntregas() {

		TypedQuery<Entrega> query = this.manager.createQuery(

				"SELECT e FROM Entrega e where e.statusEntrega = 1", Entrega.class);

		return query.getResultList();
	}

	public List<Entrega> listarEntregasAtuais(Motoboy motoboy) {

		TypedQuery<Entrega> query = this.manager.createQuery(

				// SELECT * FROM entrega AS e JOIN historico AS h ON e.statusEntrega = 0 AND
				// h.motoboy = 1 AND h.entrega = e.idEntrega AND h.fim IS NUll;
				"SELECT e FROM Entrega e inner join Historico h on e.statusEntrega = 0 and h.motoboy = "
						+ motoboy.getIdMotoboy() + " and h.entrega = e.idEntrega and h.fim is null",
				Entrega.class);

		return query.getResultList();
	}

	@Transactional
	public Entrega alterar(Entrega entrega) {
		return this.manager.merge(entrega);
	}

	public void serialize(Entrega arg0, OutputStream arg1) throws IOException {
		// TODO Auto-generated method stub

	}
}