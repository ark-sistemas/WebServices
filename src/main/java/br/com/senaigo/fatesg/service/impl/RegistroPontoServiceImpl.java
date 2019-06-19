package br.com.senaigo.fatesg.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.fatesg.entity.RegistroPonto;
import br.com.senaigo.fatesg.repositories.RegistroPontoRepository;
import br.com.senaigo.fatesg.service.RegistroPontoService;

@Service
public class RegistroPontoServiceImpl implements RegistroPontoService {
	RegistroPonto registro = new RegistroPonto();

	Logger logger = LoggerFactory.getLogger(RegistroPontoServiceImpl.class);

	@Autowired
	public RegistroPontoRepository registroRepository;

	@Override
	@Transactional
	public RegistroPonto post(RegistroPonto entity) {
		try {
			RegistroPonto aux;
			logger.debug("\tMétodo POST executado.");
			logger.debug("\tMétodo POST invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
//			aux = registroRepository.getOne(entity.getIdRegistro());
//			if(aux.getPrimeiraEntrada() == null) {
//				aux.setPrimeiraEntrada(entity.getPrimeiraEntrada());
//			} else if(aux.getSegundaEntrada() == null) {
//				aux.setSegundaEntrada(entity.getSegundaEntrada());
//			} else if(aux.getPrimeiraSaida() == null) {
//				aux.setPrimeiraSaida(entity.getPrimeiraSaida());
//			} else {
//				aux.setSegundaSaida(entity.getSegundaSaida());
//			}
			RegistroPonto rg =  registroRepository.save(entity);
			
//			 = registroRepository.save(entity);

			logger.info(String.format("\tValor persistido: %s", entity.toString()));
			return rg;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir registro. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

//	@Override
//	public RegistroPonto get(Long id) {
//		try {
//			logger.debug("\tMétodo GET executado.");
//			logger.debug("\tMétodo GET invocado");
//			logger.debug(String.format("\tValor recebido: %s", id.toString()));
//
//			registro = registroRepository.getOne(id);
//			
//			logger.info(String.format("\tValor buscado: %s", registro.toString()));
//			return registro;
//		} catch (Exception e) {
//			logger.error(String.format("Error ao buscar registro. \nMensagem:%s", e.getMessage()));
//			return null;
//		}
//	}

	@Override
	@Transactional
	public void put(RegistroPonto entity) {
		try {
			RegistroPonto aux = new RegistroPonto();
			logger.debug("\tMétodo PUT executado.");
			logger.debug("\tMétodo PUT invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			List<RegistroPonto> list = new ArrayList<RegistroPonto>();
			list = registroRepository.findAll();
			for (int i = 0; i < list.size(); i++) {
				RegistroPonto func = list.get(i);
				if(func.getEmail().equals(entity.getEmail())) {
					if(func.getData().equals(entity.getData())) {
						aux = func;
					}
				} 
			}
			//aux = registroRepository.getOne(entity.getIdRegistro());
			if(aux.getPrimeiraEntrada() == null || aux.getPrimeiraEntrada().isEmpty() || aux.getPrimeiraEntrada().equals("")) {
				aux.setPrimeiraEntrada(entity.getPrimeiraEntrada());
			} else if(aux.getPrimeiraSaida() == null || aux.getPrimeiraSaida().isEmpty() || aux.getPrimeiraSaida().equals("")) {
				aux.setPrimeiraSaida(entity.getPrimeiraSaida());
			} else if(aux.getSegundaEntrada() == null || aux.getSegundaEntrada().isEmpty() || aux.getSegundaEntrada().equals("")) {
				aux.setSegundaEntrada(entity.getSegundaEntrada());
			}  else if(aux.getSegundaSaida() == null || aux.getSegundaSaida().isEmpty() || aux.getSegundaSaida().equals("")){
				aux.setSegundaSaida(entity.getSegundaSaida());
			}
			registroRepository.save(aux);
			
			logger.info(String.format("\tValor alterado: %s", aux.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar registro. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(RegistroPonto entity) {
		try {
			logger.debug("\tMétodo DELETE executado.");
			logger.debug("\tMétodo DELETE invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			registroRepository.delete(entity);
			
			logger.info("\tValor entidade deletada:");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar. \nMensagem:%s", e.getMessage()));
			
		}
	}

	@Override
	@Transactional
	public RegistroPonto patch(RegistroPonto entity) {
		try {
			logger.debug("\tMétodo PATCH executado.");
			logger.debug("\tMétodo PATCH invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			RegistroPonto rp = registroRepository.save(entity);
			
			logger.info(String.format("\tValor alterado: %s", entity.toString()));
			return rp;
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar. \nMensagem:%s", e.getMessage()));
			return null;
		}

	}

	@Override
	@Transactional
	public List<RegistroPonto> post(List<RegistroPonto> entities) {
		try {
			List<RegistroPonto> list = new ArrayList<RegistroPonto>();
			logger.debug("\tMétodo POST LIST executado.");
			logger.debug("\tMétodo POST LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			list =  registroRepository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
			return list;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir a lista. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	@Transactional
	public void put(List<RegistroPonto> entities) {
		try {
			
			logger.debug("\tMétodo PUT LIST executado.");
			logger.debug("\tMétodo PUT LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			registroRepository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(List<RegistroPonto> entities) {
		try {
			logger.debug("\tMétodo DELETE LIST executado.");
			logger.debug("\tMétodo DELETE LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			registroRepository.deleteAll(entities);
			logger.info("\tValor lista deletada ");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar a lista. \nMensagem:%s", e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void patch(List<RegistroPonto> entities) {
		try {
			logger.debug("\tMétodo PATCH LIST executado.");
			logger.debug("\tMétodo PATCH LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			registroRepository.saveAll(entities);
			logger.info(String.format("\t lista alterada: %s",entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	public List<RegistroPonto> get() {
		try {
			List<RegistroPonto> list = new ArrayList<RegistroPonto>();
			logger.debug("\tMétodo GET LIST executado.");
			logger.debug("\tMétodo GET LIST invocado");
			
			list = (registroRepository.findAll());
			logger.info(String.format("\t lista buscada: %s",list.toString()));
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

	@Override
	public boolean login(RegistroPonto entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RegistroPonto> patch(String email) {
		try {
			List<RegistroPonto> listAux = new ArrayList<RegistroPonto>();
			List<RegistroPonto> list = new ArrayList<RegistroPonto>();
			logger.debug("\tMétodo GET LIST executado.");
			logger.debug("\tMétodo GET LIST invocado");
			listAux = registroRepository.findAll();
			for (RegistroPonto registroPonto : listAux) {
				if(registroPonto.getEmail().equals(email)) {
					list.add(registroPonto);
				}
			}
			
//			list = (registroRepository.getOne(id));
			logger.info(String.format("\t lista buscada: %s",list.toString()));
			return list;
		} catch (Exception e) {
			logger.error(String.format("Error ao get lista. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

}
