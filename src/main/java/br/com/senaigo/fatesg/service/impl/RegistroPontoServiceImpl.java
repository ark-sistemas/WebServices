package br.com.senaigo.fatesg.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.fatesg.entity.RegistroPonto;
import br.com.senaigo.fatesg.repositories.RegistroPontoRepository;
import br.com.senaigo.fatesg.service.RegistroPontoService;

@Service
public class RegistroPontoServiceImpl implements RegistroPontoService{
	RegistroPonto registro = new RegistroPonto();

	@Autowired
	public RegistroPontoRepository registroRepository;
	
	@Override
	@Transactional
	public RegistroPonto post(RegistroPonto entity) {
		RegistroPonto rg = registroRepository.save(entity);
		return rg;
	}

	@Override
	public RegistroPonto get(Long id) {
		 registro =  registroRepository.getOne(id);
		 return registro;
	}

	@Override
	@Transactional
	public void put(RegistroPonto entity) {
		registroRepository.save(entity);
		
	}

	@Override
	@Transactional
	public void delete(RegistroPonto entity) {
		registroRepository.delete(entity);
	}

	@Override
	@Transactional
	public void patch(RegistroPonto entity) {
		registroRepository.save(entity);
		
	}

	@Override
	@Transactional
	public List<RegistroPonto> post(List<RegistroPonto> entities) {
		return registroRepository.saveAll(entities);
	}

	@Override
	@Transactional
	public void put(List<RegistroPonto> entities) {
		registroRepository.saveAll(entities);
		
	}

	@Override
	@Transactional
	public void delete(List<RegistroPonto> entities) {
		registroRepository.deleteAll(entities);
		
	}

	@Override
	@Transactional
	public void patch(List<RegistroPonto> entities) {
		registroRepository.saveAll(entities);
		
	}

	@Override
	public List<RegistroPonto> get() {
		return registroRepository.findAll();
	}

}
