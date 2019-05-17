package br.com.senaigo.fatesg.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.fatesg.entity.Justificativa;
import br.com.senaigo.fatesg.repositories.JustificativaRepository;
import br.com.senaigo.fatesg.service.JustificativaService;

@Service
public class JustificativaServiceImpl implements JustificativaService{
	Justificativa registro = new Justificativa();

	@Autowired
	public JustificativaRepository repository;
	
	@Override
	@Transactional
	public Justificativa post(Justificativa entity) {
		Justificativa rg = repository.save(entity);
		return rg;
	}

	@Override
	public Justificativa get(Long id) {
		 registro =  repository.getOne(id);
		 return registro;
	}

	@Override
	@Transactional
	public void put(Justificativa entity) {
		repository.save(entity);
		
	}

	@Override
	@Transactional
	public void delete(Justificativa entity) {
		repository.delete(entity);
	}

	@Override
	@Transactional
	public void patch(Justificativa entity) {
		repository.save(entity);
		
	}

	@Override
	@Transactional
	public List<Justificativa> post(List<Justificativa> entities) {
		return repository.saveAll(entities);
	}

	@Override
	@Transactional
	public void put(List<Justificativa> entities) {
		repository.saveAll(entities);
		
	}

	@Override
	@Transactional
	public void delete(List<Justificativa> entities) {
		repository.deleteAll(entities);
		
	}

	@Override
	@Transactional
	public void patch(List<Justificativa> entities) {
		repository.saveAll(entities);
		
	}

	@Override
	public List<Justificativa> get() {
		return repository.findAll();
	}

}
