package br.com.devfast.FastDelivery.restController;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.devfast.FastDelivery.dao.LoginRepositorio;
import br.com.devfast.FastDelivery.dao.MotoboyRepositorio;
import br.com.devfast.FastDelivery.dao.RestauranteRepositorio;
import br.com.devfast.FastDelivery.model.Login;
import br.com.devfast.FastDelivery.model.Motoboy;
import br.com.devfast.FastDelivery.model.StatusMotoboy;
import br.com.devfast.FastDelivery.util.geraToken;
import br.com.devfast.FastDelivery.util.hash;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginRepositorio repository;

	@Autowired
	private MotoboyRepositorio repositoryM;

	@Autowired
	private RestauranteRepositorio repositoryR;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Login> cadastrar(@RequestBody Login login) {
		repository.inserir(login);
		return ResponseEntity.created(URI.create("/login/" + login.getIdLogin())).body(login);

	}

	@RequestMapping(value = "/logarMotoboy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Motoboy LogarMotoboy(@RequestBody Login login) {

		Motoboy motoboy = new Motoboy();

		login = repository.logar(login);

		motoboy = repositoryM.loginMotoboy(login);

		motoboy.setStatus(StatusMotoboy.INATIVO);

		repositoryM.alterar(motoboy);

		return motoboy;

	}

	@RequestMapping(value = "/buscarCPF", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Motoboy bucarCPF(@RequestBody Motoboy motoboy) {

		Motoboy motoboyN = new Motoboy();

		motoboyN = repository.bucarCPF(motoboy.getCpf());

		motoboyN.getLogin().setSenha(hash.embaralhar(motoboy.getLogin().getSenha()));

		motoboyN.getLogin()
				.setToken(geraToken.geraTokenNovo(motoboyN.getLogin().getEmail(), motoboyN.getLogin().getSenha()));

		repository.alterar(motoboyN.getLogin());

		return motoboyN;

	}

}
