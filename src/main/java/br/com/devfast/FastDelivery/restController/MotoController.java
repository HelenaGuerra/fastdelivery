package br.com.devfast.FastDelivery.restController;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.devfast.FastDelivery.dao.MotoRepositorio;
import br.com.devfast.FastDelivery.model.Moto;

@RestController
@RequestMapping("/moto")
public class MotoController {
	@Autowired
	private MotoRepositorio repository;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Moto> novaMoto(@RequestBody Moto moto) {
		repository.inserir(moto);
		return ResponseEntity.created(URI.create("/moto/" + moto.getIdMoto())).body(moto);
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Moto> alterarMoto(@PathVariable("id") Long id, @RequestBody Moto moto) {
		// try {
		moto.setIdMoto(id);
		repository.alterar(moto);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(URI.create("/moto/" + moto.getIdMoto()));
		return new ResponseEntity<Moto>(moto, header, HttpStatus.OK);
		
		
	}
}
