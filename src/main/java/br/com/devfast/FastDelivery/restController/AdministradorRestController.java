package br.com.devfast.FastDelivery.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.devfast.FastDelivery.dao.RestauranteRepositorio;

@RestController
@RequestMapping("/restRestaurante")
public class AdministradorRestController {

	@Autowired
	private RestauranteRepositorio repository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> buscarRestaurante(@PathVariable("id") Long id) {
		return new ResponseEntity<Object>(repository.buscar(id), HttpStatus.OK);

	}

}
