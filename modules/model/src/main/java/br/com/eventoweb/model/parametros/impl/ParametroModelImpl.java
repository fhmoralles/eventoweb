package br.com.eventoweb.model.parametros.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.eventoweb.domain.parametros.Parametro;
import br.com.eventoweb.model.parametros.spec.ParametroModel;
import br.com.eventoweb.repository.parametros.spec.ParametroRepository;

@Stateless(name = "parametroModel")
public class ParametroModelImpl implements ParametroModel {

	private static final Logger LOG = Logger.getLogger(ParametroModelImpl.class);

	@EJB
	private ParametroRepository parametroRepository;

	@Override
	public void create(Parametro c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Parametro retrieve(Long id) throws Exception {
		return parametroRepository.retrieve(id);
	}

	@Override
	public Parametro update(Parametro c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Parametro c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Parametro c) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
