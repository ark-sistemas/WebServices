package br.com.senaigo.fatesg.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.fatesg.entity.Justificativa;
import br.com.senaigo.fatesg.entity.RegistroPonto;
import br.com.senaigo.fatesg.interfaces.GenericOperationsController;
import br.com.senaigo.fatesg.service.JustificativaService;

//@RestController("/justificativa")
public class JustificativaController implements GenericOperationsController<Justificativa>{
	
	@Autowired
	public JustificativaService registroService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			 				MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Justificativa> post(@RequestBody Justificativa entity) {
		if(entity.getIdJustificativa() != null ) {
			registroService.post(entity);
			
			Link link = linkTo(Justificativa.class).slash(entity.getId()).withSelfRel();
			Resource<Justificativa> result = new Resource<Justificativa>(entity,link);
			
			return result;
			
		}
		System.out.println("Entidade Null");
		return null;
	}


	@Override
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@RequestBody Justificativa entity) {
		if(entity.getIdJustificativa() != null) {
			
			registroService.put(entity);
			Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
		}
		System.out.println("Entidade Null");
	}


	@Override
	@DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Justificativa entity) {
		registroService.delete(entity);
		
	}


	@Override
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<Justificativa> get() {
		List<Justificativa> allRegistros = registroService.get();
		List<Justificativa> all = new ArrayList<Justificativa>();
		for(Justificativa registro : allRegistros) {
			String registroId = String.valueOf(registro.getIdJustificativa());
			Link selfLink = linkTo(Justificativa.class).slash(registroId).withSelfRel();
			registro.add(selfLink);
			all.add(registro);
		}
		
		Link link = linkTo(Justificativa.class).withSelfRel();
		Resources<Justificativa> result = new Resources<Justificativa>(all,link);
		return result;
	}

	@Override
	@GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<Justificativa> get(@RequestParam Long id) {
		
		Justificativa registro = registroService.get(id);
		  
	    Link link = linkTo(Justificativa.class).slash(registro).withSelfRel();
	    Resource<Justificativa> result = new Resource<Justificativa>(registro, link);
	    return result;
	}


	@Override
	@PatchMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							 MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(@RequestBody Justificativa entity) {
		
		registroService.patch(entity);
		Link link = linkTo(Justificativa.class).slash(entity.getId()).withSelfRel();
		
	}
}
