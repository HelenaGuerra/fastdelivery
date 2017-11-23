package br.com.devfast.FastDelivery.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.devfast.FastDelivery.dao.MotoboyRepositorio;
import br.com.devfast.FastDelivery.model.Motoboy;
import br.com.devfast.FastDelivery.util.hash;

@Controller
@RequestMapping("/motoboy")
public class MotoboyBackController {

	@Autowired
	private MotoboyRepositorio repository;

	@RequestMapping(value = "/listaMotoboy", method = RequestMethod.GET)
	public String listaMotoboy(Model model) {

		List<Motoboy> mot = repository.listarAtivados();
		model.addAttribute("mot", mot);
		return "motoboysAdm";

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> buscarUsuario(@PathVariable("id") Long id) {
		return new ResponseEntity<Object>(repository.buscar(id), HttpStatus.OK);

	}

	@RequestMapping(value = "/atualizaMotoboy", method = RequestMethod.POST)
	public void atualizarMotoboy(@RequestBody Motoboy motoboy) {

		System.out.println(motoboy.getIdMotoboy() + "\n" + motoboy.getStatus());

		if (motoboy.getLogin() != null) {
			motoboy.getLogin().setSenha(hash.embaralhar(motoboy.getLogin().getSenha()));
		}

		repository.alterar(motoboy);

	}
}