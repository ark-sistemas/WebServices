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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.fatesg.entity.Justificativa;
import br.com.senaigo.fatesg.entity.RegistroPonto;
import br.com.senaigo.fatesg.interfaces.GenericOperationsController;
import br.com.senaigo.fatesg.service.JustificativaService;

@RestController
@RequestMapping("/justificativa")
public class JustificativaController implements GenericOperationsController<Justificativa>{
	
	
	Logger log = LoggerFactory.getLogger(JustificativaController.class);

	
	@Autowired
	public JustificativaService registroService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			 				MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Justificativa> post(@RequestBody Justificativa entity) {
			try {
				registroService.post(entity);
				log.info("Registro inserido");
				Link link = linkTo(Justificativa.class).slash(entity.getId()).withSelfRel();
				Resource<Justificativa> result = new Resource<Justificativa>(entity,link);
				
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
	public void put(@RequestBody Justificativa entity) {
			
			try {
				registroService.put(entity);
				log.info("Registro atualizado");
				Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
			} catch (Exception e) {
				log.error(String.format("Erro ao executar o método PUT.\nMensagem: %s",e.getMessage()));
			}
	}

	@Override
	@DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Justificativa entity) {
		try {
			registroService.delete(entity);
			log.info(String.format("Registro(s) deletado(s): %s",entity.toString()));
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método DELETE.\nMensagem: %s",e.getMessage()));
		}
		
	}


	@Override
	@GetMapping(value = "/{get}",produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<Justificativa> get() {
		try {
			List<Justificativa> allRegistros = registroService.get();
			log.info(String.format("Registro(s) recuperados(s): %s",allRegistros.toString()));
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
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}

	@Override
	@GetMapping(value = "/{registro}/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<Justificativa> get(@PathVariable Long id) {
		
		try {
			Justificativa registro = registroService.get(id);
			log.info(String.format("Registro recuperado: %s",registro.toString()));  
			Link link = linkTo(Justificativa.class).slash(registro).withSelfRel();
			Resource<Justificativa> result = new Resource<Justificativa>(registro, link);
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
	public void patch(@RequestBody Justificativa entity) {
		
		try {
			registroService.patch(entity);
			log.info(String.format("Registro atualizado: %s",entity.toString()));
			Link link = linkTo(Justificativa.class).slash(entity.getId()).withSelfRel();
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método PATCH.\nMensagem: %s",e.getMessage()));
		}
		
	}
}
