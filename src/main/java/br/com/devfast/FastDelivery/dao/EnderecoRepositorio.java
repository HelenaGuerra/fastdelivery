package br.com.devfast.FastDelivery.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.devfast.FastDelivery.model.Endereco;

@Repository
public class EnderecoRepositorio {
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Endereco endereco) {
		this.manager.persist(endereco);
	}

	@Transactional
	public void alterar(Endereco endereco) {
		this.manager.merge(endereco);
	}
	
	public Endereco buscar(Long idEndereco) {
		return this.manager.find(Endereco.class, idEndereco);
	}

}
