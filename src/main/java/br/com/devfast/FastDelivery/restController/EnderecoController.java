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
import br.com.devfast.FastDelivery.dao.EnderecoRepositorio;
import br.com.devfast.FastDelivery.model.Endereco;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepositorio repository;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Endereco> novoEndereco(@RequestBody Endereco endereco) {
		repository.inserir(endereco);
		return ResponseEntity.created(URI.create("/endereco/" + endereco.getIdEndereco())).body(endereco);
		
		  /*try { repository.inserir(endereco); return
		  ResponseEntity.created(URI.create("/endereco/" +
		  endereco.getIdEndereco())).body(endereco); } catch (Exception e) { ErroHttp
		  erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); return
		  ResponseEntity.status(erro.getStatusCode()).body(endereco); }
		 */
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Endereco> alterarEndereco(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
		//try {
			endereco.setIdEndereco(id);
			repository.alterar(endereco);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(URI.create("/endereco/" + endereco.getIdEndereco()));
			return new ResponseEntity<Endereco>(endereco, header, HttpStatus.OK);
			/*} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}*/
	}


}
