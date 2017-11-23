package br.com.devfast.FastDelivery.restController;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.devfast.FastDelivery.dao.EnderecoRepositorio;
import br.com.devfast.FastDelivery.dao.EntregaRepositorio;
import br.com.devfast.FastDelivery.dao.RestauranteRepositorio;
import br.com.devfast.FastDelivery.model.Entrega;
import br.com.devfast.FastDelivery.model.EstadoEntrega;
import br.com.devfast.FastDelivery.model.Motoboy;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

	@Autowired
	private EntregaRepositorio repository;
	
	@Autowired
	private EnderecoRepositorio enderecoRepository;

	@Autowired
	private RestauranteRepositorio restauranteRepository;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Entrega> novaEntrega(@RequestBody Entrega entrega) {
		repository.inserir(entrega);
		return ResponseEntity.created(URI.create("/entrega/" + entrega.getIdEntrega())).body(entrega);
	}

	@RequestMapping(value = "listarEntrega", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> listarEntregas() throws Exception {

		return new ResponseEntity<Object>(repository.listarEntregas(), HttpStatus.OK);

	}

	@RequestMapping(value = "listarEntregasAtuais", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> listarEntregasAtuais(@RequestBody Motoboy motoboy) {

		return new ResponseEntity<Object>(repository.listarEntregasAtuais(motoboy), HttpStatus.OK);

	}

	@RequestMapping(value = "alterarEntrega", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void alterarEntregas(@RequestBody Entrega entrega) {

		entrega.setStatusEntrega(EstadoEntrega.ACEITO);

		repository.alterar(entrega);

	}

}
