package br.com.devfast.FastDelivery.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.devfast.FastDelivery.model.Moto;
import br.com.devfast.FastDelivery.model.Motoboy;

@Repository
public class MotoRepositorio {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Moto moto) {
		this.manager.persist(moto);
	}

	@Transactional
	public void alterar(Moto moto) {
		this.manager.merge(moto);
	}

	public Moto buscar(Long idMoto) {
		return this.manager.find(Moto.class, idMoto);
	}

	@Transactional
	public Moto alterarMoto(Moto moto) {

		System.out.println(moto.getIdMoto());

		Motoboy motoboy = new Motoboy();

		motoboy.setMoto(moto);

		String sql = "update Moto set "
				+ "marca=:marca, modelo =:modelo, ano=:ano, cor=:cor,placa = :placa where moto.idMoto = "
				+ moto.getIdMoto();

		@SuppressWarnings("unchecked")
		TypedQuery<Motoboy> query = (TypedQuery<Motoboy>) manager.createQuery(sql);
		motoboy = query.getSingleResult();
		return moto;
	}
}
