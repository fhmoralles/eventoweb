package br.com.eventoweb.model.spec;

import java.io.Serializable;

public interface InterfaceEventoWebModel<T extends Serializable> {
	
	void create(T c) throws Exception;

	T retrieve(Long id) throws Exception;

	T update(T c) throws Exception;

	void delete(T c) throws Exception;

	void refresh(T c) throws Exception;
	
}
