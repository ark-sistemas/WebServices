package br.com.senaigo.fatesg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ambientinformatica.util.UtilHash;
import br.com.ambientinformatica.util.UtilHash.Algoritimo;
import br.com.senaigo.fatesg.entity.RegistroPonto;
import br.com.senaigo.fatesg.entity.Usuario;
import br.com.senaigo.fatesg.repositories.UsuarioRepository;
import br.com.senaigo.fatesg.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	RegistroPonto registro = new RegistroPonto();

	Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	public UsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public Usuario post(Usuario entity) {
		return null;
	}

	@Override
	public Usuario get(Long id) {

		return null;

	}

	@Override
	@Transactional
	public void put(String email) {
		try {
			Random ran = new Random();
			Usuario userAux = new Usuario();
			String senha = null;
			logger.debug("\tMétodo PUT executado.");
			logger.debug("\tMétodo PUT invocado");
			logger.debug(String.format("\tValor recebido: %s", email));

			for (Usuario usuario : usuarioRepository.findAll()) {
				if (usuario.getLogin().equals(email)) {
					for (int i = 0; i < 9; i++) {
						senha = String.valueOf(ran.nextInt(9));
					}
					userAux = usuario;
				}
			}
			userAux.setSenha(UtilHash.gerarStringHash(senha, Algoritimo.MD5));

			usuarioRepository.save(userAux);

			logger.info(String.format("\tValor alterado: %s", userAux.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar registro. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(Usuario entity) {
		try {
			logger.debug("\tMétodo DELETE executado.");
			logger.debug("\tMétodo DELETE invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));

			usuarioRepository.delete(entity);

			logger.info("\tValor entidade deletada:");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar. \nMensagem:%s", e.getMessage()));

		}
	}

	@Override
	@Transactional
	public void patch(Usuario entity) {
		try {
			logger.debug("\tMétodo PATCH executado.");
			logger.debug("\tMétodo PATCH invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));

			usuarioRepository.save(entity);

			logger.info(String.format("\tValor alterado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public List<Usuario> post(List<Usuario> entities) {
		try {
			List<Usuario> list = new ArrayList<Usuario>();
			logger.debug("\tMétodo POST LIST executado.");
			logger.debug("\tMétodo POST LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));

			list = usuarioRepository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
			return list;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir a lista. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	@Transactional
	public void put(List<Usuario> entities) {
		try {

			logger.debug("\tMétodo PUT LIST executado.");
			logger.debug("\tMétodo PUT LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));

			usuarioRepository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(List<Usuario> entities) {
		try {
			logger.debug("\tMétodo DELETE LIST executado.");
			logger.debug("\tMétodo DELETE LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));

			usuarioRepository.deleteAll(entities);
			logger.info("\tValor lista deletada ");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar a lista. \nMensagem:%s", e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void patch(List<Usuario> entities) {
		try {
			logger.debug("\tMétodo PATCH LIST executado.");
			logger.debug("\tMétodo PATCH LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));

			usuarioRepository.saveAll(entities);
			logger.info(String.format("\t lista alterada: %s", entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	public List<Usuario> get() {
		return null;
	}

	@Override
	public void put(Usuario entity) {
		// TODO Auto-generated method stub
		
	}

}
