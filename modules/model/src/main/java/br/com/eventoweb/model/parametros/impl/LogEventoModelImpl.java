package br.com.eventoweb.model.parametros.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.parametros.LogEvento;
import br.com.eventoweb.model.parametros.spec.LogEventoModel;
import br.com.eventoweb.repository.parametros.spec.LogEventoRepository;

@Stateless(name = "logEventoModel")
public class LogEventoModelImpl implements LogEventoModel {

	@EJB
	private LogEventoRepository logEventoRepository;
	
	@Override
	public void create(LogEvento c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LogEvento retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogEvento update(LogEvento c) throws Exception {
		return logEventoRepository.update(c);
	}

	@Override
	public void delete(LogEvento c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(LogEvento c) {
		// TODO Auto-generated method stub
		
	}

}
