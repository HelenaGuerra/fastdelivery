package br.com.devfast.FastDelivery.restController;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.devfast.FastDelivery.dao.EntregaRepositorio;
import br.com.devfast.FastDelivery.dao.HistoricoRepositorio;
import br.com.devfast.FastDelivery.dao.MotoboyRepositorio;
import br.com.devfast.FastDelivery.model.Historico;
import br.com.devfast.FastDelivery.model.Restaurante;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

	@Autowired
	private HistoricoRepositorio repository;

	@Autowired
	private MotoboyRepositorio motoboyRepository;

	@Autowired
	private EntregaRepositorio entregaRepository;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Historico> novoHistorico(@RequestBody Historico historico) {
		historico.setMotoboy(motoboyRepository.buscar(historico.getMotoboy().getIdMotoboy()));
		historico.setEntrega(entregaRepository.buscar(historico.getEntrega().getIdEntrega()));
		repository.inserir(historico);
		return ResponseEntity.created(URI.create("/historico/" + historico.getIdHistorico())).body(historico);
	}

	@RequestMapping(value = "listaHistorico", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> listar(Restaurante restaurante) {
		return new ResponseEntity<Object>(repository.listar(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> buscarUsuario(@PathVariable("id") Long id) {
		return new ResponseEntity<Object>(repository.buscar(id), HttpStatus.OK);

	}

	@RequestMapping(value = "novoHistorico", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Historico adicionar(@RequestBody Historico historico) {

		LocalDateTime now = LocalDateTime.now();

		Timestamp inicio = Timestamp.valueOf(now);

		motoboyRepository.alterar(historico.getMotoboy());

		historico.setInicio(inicio);

		repository.inserir(historico);

		return historico;

	}

}
