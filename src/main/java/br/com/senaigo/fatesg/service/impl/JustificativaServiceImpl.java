package br.com.senaigo.fatesg.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.fatesg.entity.Justificativa;
import br.com.senaigo.fatesg.entity.RegistroPonto;
import br.com.senaigo.fatesg.repositories.JustificativaRepository;
import br.com.senaigo.fatesg.service.JustificativaService;

@Service
public class JustificativaServiceImpl implements JustificativaService {
	Justificativa registro = new Justificativa();

	Logger logger = LoggerFactory.getLogger(JustificativaServiceImpl.class);

	@Autowired
	public JustificativaRepository repository;

	@Override
	@Transactional
	public Justificativa post(Justificativa entity) {
		try {
			logger.debug("\tMétodo POST executado.");
			logger.debug("\tMétodo POST invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));

			Justificativa rg = repository.save(entity);
			logger.info(String.format("\tValor persistido: %s", entity.toString()));
			return rg;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir registro. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	public Justificativa get(Long id) {
		try {
			logger.debug("\tMétodo GET executado.");
			logger.debug("\tMétodo GET invocado");
			logger.debug(String.format("\tValor recebido: %s", id.toString()));

			registro = repository.getOne(id);
			
			logger.info(String.format("\tValor buscado: %s", registro.toString()));
			return registro;
		} catch (Exception e) {
			logger.error(String.format("Error ao buscar registro. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	@Transactional
	public void put(Justificativa entity) {
		try {
			logger.debug("\tMétodo PUT executado.");
			logger.debug("\tMétodo PUT invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			repository.save(entity);
			
			logger.info(String.format("\tValor alterado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar registro. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(Justificativa entity) {
		try {
			logger.debug("\tMétodo DELETE executado.");
			logger.debug("\tMétodo DELETE invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			repository.delete(entity);
			
			logger.info("\tValor entidade deletada");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar. \nMensagem:%s", e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void patch(Justificativa entity) {
		try {
			logger.debug("\tMétodo PATCH executado.");
			logger.debug("\tMétodo PATCH invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			repository.save(entity);
			
			logger.info(String.format("\tValor alterado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public List<Justificativa> post(List<Justificativa> entities) {
		try {
			List<Justificativa> list = new ArrayList<Justificativa>();
			logger.debug("\tMétodo POST LIST executado.");
			logger.debug("\tMétodo POST LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			list =  repository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
			return list;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir a lista. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	@Transactional
	public void put(List<Justificativa> entities) {
		try {
			logger.debug("\tMétodo PUT LIST executado.");
			logger.debug("\tMétodo PUT LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			repository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(List<Justificativa> entities) {
		try {
			logger.debug("\tMétodo DELETE LIST executado.");
			logger.debug("\tMétodo DELETE LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			repository.deleteAll(entities);
			
			logger.info("\tValor lista deletada: ");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void patch(List<Justificativa> entities) {
		try {
			logger.debug("\tMétodo PATCH LIST executado.");
			logger.debug("\tMétodo PATCH LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			repository.saveAll(entities);
			logger.info(String.format("\t lista alterada: %s",entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	public List<Justificativa> get() {
		try {
			List<Justificativa> list = new ArrayList<Justificativa>();
			logger.debug("\tMétodo GET LIST executado.");
			logger.debug("\tMétodo GET LIST invocado");
			
			list =  repository.findAll();
			logger.info(String.format("\t lista alterada: %s",list.toString()));
			return list;
		} catch (Exception e) {
			logger.error(String.format("Error ao get lista. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	public void put(String email) {
		// TODO Auto-generated method stub
		
	}

}
