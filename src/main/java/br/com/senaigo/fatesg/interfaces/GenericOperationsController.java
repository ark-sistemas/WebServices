package br.com.senaigo.fatesg.interfaces;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

public interface GenericOperationsController<E> {
	
	Resource<E> post(E entity); // post 201
	void put(E entity); //put 204 - no contect
	void put(String email); //put 204 - no contect
	void delete(E entity); //delete 204
	Resources<E> get(); // get 200
	Resources<E> patch(String email); // get 200
//	Resource<E> get(Long id); // get 200
	//void patch(E entity); //patch 204
	Resource patch(E entity); //patch 204
}
