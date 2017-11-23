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
import br.com.devfast.FastDelivery.dao.EntregaRepositorio;
import br.com.devfast.FastDelivery.dao.HistoricoRepositorio;
import br.com.devfast.FastDelivery.dao.MotoboyRepositorio;
import br.com.devfast.FastDelivery.dao.RestauranteRepositorio;
import br.com.devfast.FastDelivery.model.Endereco;
import br.com.devfast.FastDelivery.model.Entrega;
import br.com.devfast.FastDelivery.model.EstadoEntrega;
import br.com.devfast.FastDelivery.model.Historico;
import br.com.devfast.FastDelivery.model.Motoboy;
import br.com.devfast.FastDelivery.model.Restaurante;
import br.com.devfast.FastDelivery.model.StatusMotoboy;
import br.com.devfast.FastDelivery.model.StatusRestaurante;
import br.com.devfast.FastDelivery.util.formatacaoEndereco;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {

	@Autowired
	private HistoricoRepositorio historicoRepositorio;

	@Autowired
	private MotoboyRepositorio motoboyRepositorio;

	@Autowired
	private EntregaRepositorio entregaRepositorio;

	@Autowired
	private RestauranteRepositorio restauranteRepositorio;

	/*
	 * método utilizado para enviar a entrega para o motoboy
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Entrega> enviaEntrega(@RequestBody Entrega entrega) {
		// recebe a entrega registrada
		// novaEntrega(entrega, "");
		Motoboy motoboy = new Motoboy();
		// se motoboy estiver ativo no sistema ou já efetuando uma entrega
		if (motoboy.getStatus() == StatusMotoboy.ATIVO || motoboy.getStatus() == StatusMotoboy.EMCURSO) {
			// to be continued...
		}
		return ResponseEntity.created(URI.create("/entrega/" + entrega.getIdEntrega())).body(entrega);
	}

	@RequestMapping(value = "/entrega", method = RequestMethod.GET)
	public String telaEntrega(Model model, HttpSession session) {

		try {

			Restaurante restaurante = new Restaurante();

			restaurante = (Restaurante) session.getAttribute("restaurante");

			restaurante.getNome();

			Entrega entrega = new Entrega();
			Endereco endereco = new Endereco();

			entrega.setEndereco(endereco);
			entrega.setRestaurante(restaurante);

			model.addAttribute("entrega", entrega);

			return "index";

		} catch (NullPointerException e) {

			return "redirect:../";
		}

	}

	/*
	 * método utilizado para realizar uma nova entrega, por ele o restaurante irá
	 * gerar uma entrega e enviar uma solicitação para o motoboy
	 * 
	 * MÉTODO EM REST DE RELAÇÃO ENTRE WEB E MOBILE
	 */
	@RequestMapping(value = "/novaEntrega", method = RequestMethod.POST)
	public String novaEntrega(Entrega entrega, HttpSession session) {

		// Cria o local onde vai ficar as partes do endereco
		String[] palavras = new String[7];

		palavras = formatacaoEndereco.separaEndereco(entrega.getEndereco().getLogradouro());

		entrega.getEndereco().setLogradouro(palavras[0]);
		entrega.getEndereco().setNumero(palavras[1]);
		entrega.getEndereco().setBairro(palavras[2]);
		entrega.getEndereco().setCidade(palavras[3]);
		entrega.getEndereco().setEstado(palavras[4]);
		entrega.getEndereco().setCep(palavras[5]);

		entrega.setStatusEntrega(EstadoEntrega.RECUSADO);

		// DEPOIS DEVOLVER VEM PARA CA
		entregaRepositorio.inserir(entrega);

		return "redirect:entrega";
	}

	@RequestMapping(value = "/novoMotoboy", method = RequestMethod.POST)
	public String novoMotoboy(Motoboy motoboy) {

		return "motoboys";
	}

	@RequestMapping(value = "/monitoramento", method = RequestMethod.GET)
	public String monitoramento(HttpSession session, Model model) {

		try {

			Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
			restaurante.getNome();

			List<Historico> historicos = motoboyRepositorio.listarMonitoramento(restaurante.getIdRestaurante());

			model.addAttribute("historico", historicos);
			model.addAttribute("idRestaurante", restaurante.getIdRestaurante());

			return "monitoramento";

		} catch (NullPointerException e) {
			return "redirect:../";
		}

	}

	@RequestMapping(value = "/motoboys", method = RequestMethod.GET)
	public String motoboys(Model model, HttpSession session) {

		try {

			Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
			restaurante.getNome();

			List<Motoboy> mot = motoboyRepositorio.listarAtivados();
			model.addAttribute("mot", mot);

			return "motoboys";

		} catch (NullPointerException e) {

			return "redirect:../";

		}

	}

	@RequestMapping(value = "/historicoEntrega", method = RequestMethod.GET)
	public String historicoEntrega(Model model, HttpSession session) {

		try {

			Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");

			restaurante.getNome();

			List<Historico> his = historicoRepositorio.listarPorRestaurante(restaurante.getIdRestaurante());
			model.addAttribute("his", his);
			return "historicos";

		} catch (NullPointerException e) {
			return "redirect:../";
		}

	}

	@RequestMapping(value = "/tornarPublico/{id}", method = RequestMethod.GET)
	public String excluirRestaurante(@PathVariable("id") Long idModal) {

		Restaurante restaurante = new Restaurante();

		restaurante = restauranteRepositorio.buscar(idModal);

		if (restaurante.getStatus() == StatusRestaurante.ATIVO) {

			restaurante.setStatus(StatusRestaurante.PUBLICO);

			restauranteRepositorio.alterar(restaurante);

		}

		return "redirect:../admRestaurante";
	}

	@RequestMapping(value = "/{idHistorico}", method = RequestMethod.GET)
	public ResponseEntity<Object> buscarUsuario(@PathVariable("idHistorico") Long idHistorico) {

		return new ResponseEntity<Object>(historicoRepositorio.buscar(idHistorico), HttpStatus.OK);

	}

}