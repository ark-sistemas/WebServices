package br.com.senaigo.fatesg.service.impl;

import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ambientinformatica.util.UtilHash;
import br.com.ambientinformatica.util.UtilHash.Algoritimo;
import br.com.senaigo.fatesg.entity.Usuario;
import br.com.senaigo.fatesg.repositories.UsuarioRepository;
import br.com.senaigo.fatesg.service.UsuarioService;
import br.com.senaigo.fatesg.util.Email;
import br.com.senaigo.fatesg.util.GerarSenha;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	//Usuario registro = new Usuario();

	Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	public UsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public Usuario post(Usuario entity) {
		return null;
	}

//	@Override
//	public Usuario get(Long id) {
//
//		return null;
//
//	}

	@Override
	@Transactional
	public void put(String email) {
		try {
			Random ran = new Random();
			Usuario userAux = new Usuario();
			String senha = "Conforme solicitado segue a nova senha: ";
			logger.debug("\tMétodo PUT executado.");
			logger.debug("\tMétodo PUT invocado");
			logger.debug(String.format("\tValor recebido: %s", email));
			
			for (Usuario usuario : usuarioRepository.findAll()) {
				if (usuario.getLogin().equals(email)) {
					senha += GerarSenha.getRandomPass(8);
					userAux = usuario;
				}
			}
			Email.enviarEmail(userAux.getLogin(), senha);
			userAux.setSenha(UtilHash.gerarStringHash(senha, Algoritimo.MD5));
			usuarioRepository.save(userAux);

			logger.info(String.format("\tValor alterado: %s", userAux.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar registro. \nMensagem:%s", e.getMessage()));
		}

	}
	
	@Override
	public Boolean patch(Usuario entity) {
		logger.debug("\tMétodo LOGIN executado.");
		logger.debug("\tMétodo LOGIN invocado");
		logger.debug(String.format("\tValor recebido: %s", entity.toString()));
		for (Usuario usuario : usuarioRepository.findAll()) {
			if (usuario.getLogin().equals(entity.getLogin())) {
				if(usuario.getSenha().equals(UtilHash.gerarStringHash(entity.getSenha(), Algoritimo.MD5))) {
					System.out.println("senha OK");
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	@Transactional
	public void delete(Usuario entity) {
		
	}

	
	@Override
	@Transactional
	public List<Usuario> post(List<Usuario> entities) {
		return entities;
		
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
		
	}

	@Override
	@Transactional
	public void patch(List<Usuario> entities) {
		

	}

	@Override
	public List<Usuario> get() {
		return null;
	}

	@Override
	public void put(Usuario entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean login(Usuario entity) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public List<Usuario> get(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Usuario> patch(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
