package br.com.senaigo.fatesg.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.fatesg.entity.RegistroPonto;
import br.com.senaigo.fatesg.entity.Usuario;
import br.com.senaigo.fatesg.interfaces.GenericOperationsController;
import br.com.senaigo.fatesg.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController implements GenericOperationsController<Usuario>{
	
	
	Logger log = LoggerFactory.getLogger(UsuarioController.class);

	
	@Autowired
	public UsuarioService service;
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			 				MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Usuario> post(@RequestBody Usuario entity) {
		

		try {
			service.post(entity);
			log.info("Registro inserido");
			
			Link link = linkTo(Usuario.class).slash(entity.getId()).withSelfRel();
			Resource<Usuario> result = new Resource<Usuario>(entity,link);
			
			return result;
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método POST.\nMensagem: %s",e.getMessage()));
		}
			
		return null;
	}


	@Override
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@RequestBody String email) {
			
			try {
				service.put(email);
				log.info(String.format("Registro atualizado: %s",email));
				//Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
			} catch (Exception e) {
				log.error(String.format("Erro ao executar o método PUT.\nMensagem: %s",e.getMessage()));
			}
	}


	@Override
	@DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Usuario entity) {
		try {
			service.delete(entity);
			log.info(String.format("Registro(s) deletado(s): %s",entity.toString()));
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método DELETE.\nMensagem: %s",e.getMessage()));
		}
		
	}

	@Override
	@PatchMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							 MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(@RequestBody Usuario entity) {
		
		try {
			service.patch(entity);
			log.info(String.format("Registro atualizado: %s",entity.toString()));
			Link link = linkTo(Usuario.class).slash(entity.getId()).withSelfRel();
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método PATCH.\nMensagem: %s",e.getMessage()));
		}
		
	}


	@Override
	public Resources<Usuario> get() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Resource<Usuario> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void put(Usuario entity) {
		// TODO Auto-generated method stub
		
	}
}
