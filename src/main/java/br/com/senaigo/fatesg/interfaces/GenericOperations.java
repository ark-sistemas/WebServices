package br.com.senaigo.fatesg.interfaces;

import java.util.List;

public interface GenericOperations<E> {
	
	E post(E entity);
//	E get(Long id);
	void put(String email);
	void put(E entity);
	void delete(E entity);
	//void patch(E entity);
	Boolean patch(E entity);
	boolean login(E entity);
	
	List<E> post(List<E> entities);
	void put(List<E> entities);
	void delete(List<E> entities);
	void patch(List<E> entities);
	List<E> patch(String email);
	List<E> get();
}
