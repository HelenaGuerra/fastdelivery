package br.com.devfast.FastDelivery.controller;

import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.devfast.FastDelivery.dao.AdministradorRepositorio;
import br.com.devfast.FastDelivery.dao.LoginRepositorio;
import br.com.devfast.FastDelivery.dao.MotoboyRepositorio;
import br.com.devfast.FastDelivery.dao.RestauranteRepositorio;
import br.com.devfast.FastDelivery.model.Administrador;
import br.com.devfast.FastDelivery.model.Endereco;
import br.com.devfast.FastDelivery.model.Login;
import br.com.devfast.FastDelivery.model.Moto;
import br.com.devfast.FastDelivery.model.Motoboy;
import br.com.devfast.FastDelivery.model.Restaurante;
import br.com.devfast.FastDelivery.model.StatusMotoboy;
import br.com.devfast.FastDelivery.model.StatusRestaurante;
import br.com.devfast.FastDelivery.util.Concerta;
import br.com.devfast.FastDelivery.util.geraToken;
import br.com.devfast.FastDelivery.util.hash;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private AdministradorRepositorio repository;

	@Autowired
	private LoginRepositorio loginRepository;

	@Autowired
	private MotoboyRepositorio motoboyRepositorio;

	@Autowired
	private RestauranteRepositorio restauranteRepositorio;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Administrador> novoAdm(@RequestBody Administrador adm) {
		adm.setLogin(loginRepository.buscar(adm.getLogin().getIdLogin()));
		repository.inserir(adm);
		return ResponseEntity.created(URI.create("/administrador/" + adm.getIdAdministrador())).body(adm);
	}

	// parte de acesso do administrador aos restaurantes

	@RequestMapping(value = "/editaRestaurante/{ide}", method = RequestMethod.GET)
	public String editaRestaurante(@PathVariable("ide") Long idModal, Model model, HttpSession session) {

		try {

			Login loginAdm = (Login) session.getAttribute("adm");
			loginAdm.getToken();

			Restaurante restaurante = new Restaurante();
			Login login = new Login();
			Endereco endereco = new Endereco();

			restaurante.setIdRestaurante(idModal);
			restaurante.setLogin(login);
			restaurante.setEndereco(endereco);

			restaurante = restauranteRepositorio.buscar(restaurante.getIdRestaurante());

			model.addAttribute("restaurante", restaurante);

			return "alterarestaurante";

		} catch (NullPointerException e) {
			return "redirect:../../";
		}

	}

	@RequestMapping(value = "/novoRestaurante", method = RequestMethod.POST)
	public String novoRestaurante(Restaurante restaurante) {

		if (restaurante.getIdRestaurante() != null) {

			restaurante.setStatus(StatusRestaurante.ATIVO);

			restaurante.getLogin().setSenha(hash.embaralhar(restaurante.getLogin().getSenha()));

			restaurante.getLogin().setToken(
					geraToken.geraTokenNovo(restaurante.getLogin().getEmail(), restaurante.getLogin().getSenha()));

			restauranteRepositorio.alterar(restaurante);

		} else {

			System.out.println("Entrou na praga do Else");

			restaurante.setStatus(restaurante.getStatus().ATIVO);

			restaurante.setNome(Concerta.concerta(restaurante.getNome()));

			restauranteRepositorio.inserir(restaurante);
		}

		return "redirect:admRestaurante";

	}

	@RequestMapping(value = "/restaureAdm", method = RequestMethod.GET)
	public String listaRestaurantes(Model model, HttpSession session) {

		try {

			Login login = (Login) session.getAttribute("adm");

			login.getToken();

			List<Restaurante> res = restauranteRepositorio.listar();

			model.addAttribute("res", res);

			return "restaurantesAdm";

		} catch (NullPointerException e) {
			return "redirect:../";
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Restaurante buscarUsuario(@PathVariable("id") Long id, Model model) {

		Restaurante restaurante = new Restaurante();
		restaurante = restauranteRepositorio.buscar(id);

		model.addAttribute("res", restaurante);

		return restaurante;
	}

	@RequestMapping(value = "/cadastroRestaurante", method = RequestMethod.GET)
	public String cadastroRestaurante(Model model, HttpSession session) {

		try {

			Login loginAdm = (Login) session.getAttribute("adm");

			loginAdm.getToken();

			Restaurante restaurante = new Restaurante();

			Login login = new Login();

			Endereco endereco = new Endereco();

			restaurante.setLogin(login);

			restaurante.setEndereco(endereco);

			model.addAttribute("restaurante", restaurante);

			return "cadastrorestaurante";

		} catch (NullPointerException e) {
			return "redirect:../";
		}

	}

	@RequestMapping(value = "/dash", method = RequestMethod.GET)
	public String dash(Model model, HttpSession session) {

		try {

			Login login = (Login) session.getAttribute("adm");
			login.getToken();

			Long[] dadosDash = new Long[5];

			dadosDash = repository.countDados();
			model.addAttribute("counts", dadosDash);

			return "dashboard";

		} catch (NullPointerException e) {

			return "redirect:../";

		}

	}

	@RequestMapping(value = "/dadosDash", method = RequestMethod.GET)
	public ResponseEntity<Object> buscarCounts() {

		return new ResponseEntity<Object>(repository.countDados(), HttpStatus.OK);

	}

	// parte de acesso do administrador aos motoboys

	@RequestMapping(value = "/cadastroMotoboy", method = RequestMethod.GET)
	public String cadastroMotoboy(Model model) {

		Motoboy motoboy = new Motoboy();
		Login login = new Login();
		Endereco endereco = new Endereco();
		Moto moto = new Moto();

		motoboy.setLogin(login);
		motoboy.setMoto(moto);
		motoboy.setEndereco(endereco);
		model.addAttribute("motoboy", motoboy);

		return "cadastromotoboy";

	}

	@RequestMapping(value = "/excluirMotoboy/{id}", method = RequestMethod.GET)
	public String excluirMotoboy(@PathVariable("id") Long idModal) {

		Motoboy motoboy = new Motoboy();

		motoboy = motoboyRepositorio.buscar(idModal);
		if (motoboy.getStatus() == motoboy.getStatus().DESATIVADO) {

			motoboy.setStatus(motoboy.getStatus().INATIVO);

		} else {
			motoboy.setStatus(motoboy.getStatus().DESATIVADO);

		}

		motoboyRepositorio.alterar(motoboy);

		return "redirect:../motoboys";
	}

	@RequestMapping(value = "/excluirRestaurante/{id}", method = RequestMethod.GET)
	public String excluirRestaurante(@PathVariable("id") Long idModal) {

		Restaurante restaurante = new Restaurante();

		restaurante = restauranteRepositorio.buscar(idModal);
		if (restaurante.getStatus() == StatusRestaurante.ATIVO
				|| restaurante.getStatus() == StatusRestaurante.PUBLICO) {

			restaurante.setStatus(restaurante.getStatus().INATIVO);

		} else {

			restaurante.setStatus(restaurante.getStatus().ATIVO);
		}

		restauranteRepositorio.alterar(restaurante);

		return "redirect:../admRestaurante";
	}

	@RequestMapping(value = "/editaMotoboy/{ide}", method = RequestMethod.GET)
	public String editaMotoboy(@PathVariable("ide") Long idModal, Model model, HttpSession session) {

		try {

			Login loginAdm = (Login) session.getAttribute("adm");
			loginAdm.getToken();

			Motoboy motoboy = new Motoboy();
			Login login = new Login();
			Endereco endereco = new Endereco();
			Moto moto = new Moto();

			motoboy.setIdMotoboy(idModal);

			motoboy.setLogin(login);
			motoboy.setMoto(moto);
			motoboy.setEndereco(endereco);

			motoboy = motoboyRepositorio.buscar(motoboy.getIdMotoboy());

			model.addAttribute("motoboy", motoboy);

			return "alteramotoboy";

		} catch (NullPointerException e) {
			return "redirect:../";
		}

	}

	@RequestMapping(value = "/novoMotoboy", method = RequestMethod.POST)
	public String novoMotoboy(Motoboy motoboyTela) {

		if (motoboyTela.getIdMotoboy() != null) {

			motoboyTela.setStatus(StatusMotoboy.INATIVO);

			motoboyTela.setNome(Concerta.concerta(motoboyTela.getNome()));

			motoboyTela.getLogin().setToken(
					geraToken.geraTokenNovo(motoboyTela.getLogin().getEmail(), motoboyTela.getLogin().getSenha()));

			motoboyTela.getLogin().setSenha(hash.embaralhar(motoboyTela.getLogin().getSenha()));

			motoboyRepositorio.alterar(motoboyTela);

		} else {

			motoboyTela.setStatus(motoboyTela.getStatus().INATIVO);

			motoboyTela.setNome(Concerta.concerta(motoboyTela.getNome()));

			motoboyRepositorio.inserir(motoboyTela);
		}

		return "redirect:cadastroMotoboy";
	}

	@RequestMapping(value = "/motoboys", method = RequestMethod.GET)
	public String motoboys(Model model, HttpSession session) {

		try {

			Login login = (Login) session.getAttribute("adm");
			login.getToken();

			List<Motoboy> motA = motoboyRepositorio.listarAtivados();
			List<Motoboy> motD = motoboyRepositorio.listarDesativados();

			model.addAttribute("motA", motA);
			model.addAttribute("motD", motD);

			return "motoboysAdm";

		} catch (NullPointerException e) {

			return "redirect:../";

		}

	}

	@RequestMapping(value = "/admRestaurante", method = RequestMethod.GET)
	public String restaurantes(Model model, HttpSession session) {

		System.out.println("Começou a palhacada");

		try {

			Login login = (Login) session.getAttribute("adm");

			login.getToken();

			List<Restaurante> restA = restauranteRepositorio.listarAtivados();

			System.out.println("tamanho do vetor " + restA.size());

			for (int i = 0; i < restA.size(); i++) {

				System.out.println(restA.get(i).getCnpj());

			}

			List<Restaurante> restD = restauranteRepositorio.listarDesativados();

			System.out.println("tamanho do vetor " + restD.size());

			model.addAttribute("restA", restA);
			model.addAttribute("restD", restD);

			return "restaurantesAdm";

		} catch (NullPointerException e) {

			return "redirect:../";

		}

	}

	@RequestMapping(value = "/400", method = RequestMethod.GET)
	public String erro400() {
		return "erro400";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String erro403() {
		return "erro403";
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String erro404() {
		return "erro404";
	}

	@RequestMapping(value = "/405", method = RequestMethod.GET)
	public String erro405() {
		return "erro405";
	}

	@RequestMapping(value = "/415", method = RequestMethod.GET)
	public String erro415() {
		return "erro415";
	}

	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public String erro500() {
		return "erro500";
	}
	
	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	public String teste() {
		return "teste";
	}
}
