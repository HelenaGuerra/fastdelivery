package br.com.devfast.FastDelivery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.devfast.FastDelivery.dao.LoginRepositorio;
import br.com.devfast.FastDelivery.dao.MotoboyRepositorio;
import br.com.devfast.FastDelivery.dao.RestauranteRepositorio;
import br.com.devfast.FastDelivery.model.Login;
import br.com.devfast.FastDelivery.model.Motoboy;
import br.com.devfast.FastDelivery.model.Restaurante;
import br.com.devfast.FastDelivery.util.hash;

@Controller
@RequestMapping("/")
public class LoginBackController {

	@Autowired
	private LoginRepositorio loginRepository;

	@Autowired
	private RestauranteRepositorio repositoryR;

	@Autowired
	private MotoboyRepositorio motoboyR;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String telaLogin(Model model) {

		Login login = new Login();

		Restaurante restaurante = new Restaurante();

		model.addAttribute("login", login);
		model.addAttribute("restaurante", restaurante);

		return "login";
	}

	@RequestMapping(value = "criarLogin", method = RequestMethod.POST)
	public String criaAdm(@RequestBody Login login) {

		login.setSenha(hash.embaralhar(login.getSenha()));

		loginRepository.inserir(login);

		return "login";
	}

	@RequestMapping(value = "logar", method = RequestMethod.POST)
	public String LogarRestaurante(Login login, HttpSession session) {

		Restaurante restaurante = new Restaurante();
		login = loginRepository.logar(login);

		restaurante = repositoryR.loginRestaurante(login);

		if (restaurante.getIdRestaurante() == null) {

			Motoboy motoboy = new Motoboy();
			motoboy = motoboyR.loginMotoboy(restaurante.getLogin());

			if (motoboy.getIdMotoboy() == null) {

				session.setAttribute("adm", login);

				return "redirect:administrador/cadastroRestaurante";

			}

			return "redirect:../FastDelivery";

		} else {

			session.setAttribute("restaurante", restaurante);

			return "redirect:restaurante/entrega";

		}
	}

	@RequestMapping(value = "esqueceuASenha", method = RequestMethod.POST)
	public String esqueceuASenha(Restaurante restaurante) {

		Login login = new Login();

		login = restaurante.getLogin();

		restaurante = repositoryR.buscarCnpj(restaurante);

		login.setSenha(hash.embaralhar(login.getSenha()));

		restaurante.getLogin().setSenha(login.getSenha());

		repositoryR.alterar(restaurante);

		return "redirect:../FastDelivery";
	}

	@RequestMapping(value = "sair", method = RequestMethod.GET)
	public String sair(HttpSession session) {

		System.out.println("Entrou no sair");

		session.removeAttribute("restaurante");

		session.removeAttribute("adm");

		return "redirect:../FastDelivery";

	}

}
