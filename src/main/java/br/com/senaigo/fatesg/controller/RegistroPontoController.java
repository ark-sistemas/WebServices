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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.fatesg.entity.RegistroPonto;
import br.com.senaigo.fatesg.interfaces.GenericOperationsController;
import br.com.senaigo.fatesg.service.RegistroPontoService;

@RestController("/registro")
public class RegistroPontoController implements GenericOperationsController<RegistroPonto>{
	
	@Autowired
	public RegistroPontoService registroService;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			 				MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<RegistroPonto> post(@RequestBody RegistroPonto entity) {
		if(entity.getIdFuncionario() != null ) {
			System.out.println("id do Funcionario: "+ entity.getIdFuncionario());
			registroService.post(entity);
			
			Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
			Resource<RegistroPonto> result = new Resource<RegistroPonto>(entity,link);
			
			return result;
			
		}
		System.out.println("Entidade Null");
		return null;
	}


	@Override
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@RequestBody RegistroPonto entity) {
		if(entity.getIdFuncionario() != null) {
			
			registroService.put(entity);
			Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
		}
		System.out.println("Entidade Null");
	}


	@Override
	@DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody RegistroPonto entity) {
		registroService.delete(entity);
		
	}


	@Override
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<RegistroPonto> get() {
		List<RegistroPonto> allRegistros = registroService.get();
		List<RegistroPonto> all = new ArrayList<RegistroPonto>();
		for(RegistroPonto registro : allRegistros) {
			String registroId = String.valueOf(registro.getIdRegistro());
			Link selfLink = linkTo(RegistroPonto.class).slash(registroId).withSelfRel();
			registro.add(selfLink);
			all.add(registro);
		}
		
		Link link = linkTo(RegistroPonto.class).withSelfRel();
		Resources<RegistroPonto> result = new Resources<RegistroPonto>(all,link);
		return result;
	}


	@Override
	@GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	//@RequestMapping(value = "/get/{id}")
	//@ResponseBody
	//@RequestParam(value="id")
	public Resource<RegistroPonto> getId(Integer id) {
		
		RegistroPonto registro = registroService.get(id);
		  
	    Link link = linkTo(RegistroPonto.class).slash(registro).withSelfRel();
	    Resource<RegistroPonto> result = new Resource<RegistroPonto>(registro, link);
	    return result;
	}


	@Override
	@PatchMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							 MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(@RequestBody RegistroPonto entity) {
		
		registroService.patch(entity);
		Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
		
	}
}
