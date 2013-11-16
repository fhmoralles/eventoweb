package br.com.eventoweb.repository.parametros.impl;

import javax.ejb.Stateless;

import br.com.eventoweb.domain.parametros.LogEvento;
import br.com.eventoweb.repository.parametros.spec.LogEventoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "logEventoRepository")
public class LogEventoRepositoryImpl extends
		AbstractEventoWebRepository<LogEvento> implements LogEventoRepository {

	protected LogEventoRepositoryImpl() {
		super(LogEvento.class);
	}

}
