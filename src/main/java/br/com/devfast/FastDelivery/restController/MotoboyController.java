package br.com.devfast.FastDelivery.restController;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.devfast.FastDelivery.dao.EnderecoRepositorio;
import br.com.devfast.FastDelivery.dao.MotoRepositorio;
import br.com.devfast.FastDelivery.dao.MotoboyRepositorio;
import br.com.devfast.FastDelivery.model.Motoboy;

@RestController
@RequestMapping("/motoboyRest")
public class MotoboyController {

	@Autowired
	private MotoRepositorio motoRepositorio;

	@Autowired
	private MotoboyRepositorio motoboyRepositorio;

	@Autowired
	EnderecoRepositorio enderecoRepositorio;

	@RequestMapping(value = "/atualizaMotoboy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Motoboy atualizarMototinha(@RequestBody Motoboy motoboyJson) {

		if (motoboyJson.getMoto() != null)
			motoRepositorio.alterar(motoboyJson.getMoto());

		if (motoboyJson.getEndereco() != null)
			enderecoRepositorio.alterar(motoboyJson.getEndereco());

		motoboyRepositorio.alterar(motoboyJson);

		return motoboyJson;

	}

	@RequestMapping(value = "/atualizaFoto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void atualizarMotoboy(@RequestBody Motoboy motoboy, MultipartFile arquivo) {

		if (!arquivo.isEmpty()) {
			try {
				motoboy.setArquivo(arquivo.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			motoboyRepositorio.alterar(motoboy);

		}
	}

}
