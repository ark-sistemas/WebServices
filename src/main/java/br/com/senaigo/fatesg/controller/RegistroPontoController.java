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
import br.com.senaigo.fatesg.interfaces.GenericOperationsController;
import br.com.senaigo.fatesg.service.RegistroPontoService;

@RestController
@RequestMapping("/registro")
public class RegistroPontoController implements GenericOperationsController<RegistroPonto>{
	
	
	Logger log = LoggerFactory.getLogger(RegistroPontoController.class);

	
	@Autowired
	public RegistroPontoService registroService;
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			 				MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<RegistroPonto> post(@RequestBody RegistroPonto entity) {
		

		try {
			registroService.post(entity);
			log.info("Registro inserido");
			
			Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
			Resource<RegistroPonto> result = new Resource<RegistroPonto>(entity,link);
			
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
	public void put(@RequestBody RegistroPonto entity) {
			
			try {
				registroService.put(entity);
				log.info(String.format("Registro atualizado: %s",entity.toString()));
				//Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
			} catch (Exception e) {
				log.error(String.format("Erro ao executar o método PUT.\nMensagem: %s",e.getMessage()));
			}
	}


	@Override
	@DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody RegistroPonto entity) {
		try {
			registroService.delete(entity);
			log.info(String.format("Registro(s) deletado(s): %s",entity.toString()));
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método DELETE.\nMensagem: %s",e.getMessage()));
		}
		
	}


	@Override
	@GetMapping(value = "/{get}/",produces = {MediaType.APPLICATION_JSON_VALUE,
											  MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<RegistroPonto> get() {
		List<RegistroPonto> allRegistros = new ArrayList<RegistroPonto>();
		allRegistros.addAll(registroService.get());
		List<RegistroPonto> all = new ArrayList<RegistroPonto>();
		try {
			for(RegistroPonto registro : allRegistros) {
				String registroId = String.valueOf(registro.getIdRegistro());
				Link selfLink = linkTo(RegistroPonto.class).slash(registroId).withSelfRel();
				registro.add(selfLink);
				all.add(registro);
			}
			
			Link link = linkTo(RegistroPonto.class).withSelfRel();
			Resources<RegistroPonto> result = new Resources<RegistroPonto>(all,link);
			log.info(String.format("Registro(s) recuperados(s): %s",all.toString()));
			return result;
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}
	
	@Override
	@GetMapping(value = "/get/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,
											MediaType.APPLICATION_XML_VALUE,
											MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<RegistroPonto> get(@PathVariable Long id) {
		
		try {
			RegistroPonto registro = registroService.get(id);
			  
			Link link = linkTo(RegistroPonto.class).slash(registro).withSelfRel();
			Resource<RegistroPonto> result = new Resource<RegistroPonto>(registro, link);
			log.info(String.format("Registro recuperado: %s",result.toString()));
			return result;
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}


	@Override
	@PatchMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							 MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Boolean patch(@RequestBody RegistroPonto entity) {
		
		try {
			registroService.patch(entity);
			log.info(String.format("Registro atualizado: %s",entity.toString()));
			Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
			return true;
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método PATCH.\nMensagem: %s",e.getMessage()));
			return false;
		}
		
	}


	@Override
	public void put(String email) {
		// TODO Auto-generated method stub
		
	}
}
