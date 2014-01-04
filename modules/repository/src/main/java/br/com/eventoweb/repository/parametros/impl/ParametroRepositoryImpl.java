package br.com.eventoweb.repository.parametros.impl;

import javax.ejb.Stateless;

import br.com.eventoweb.domain.parametros.Parametro;
import br.com.eventoweb.repository.parametros.spec.ParametroRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "parametroRepository")
public class ParametroRepositoryImpl extends
		AbstractEventoWebRepository<Parametro> implements ParametroRepository {

	protected ParametroRepositoryImpl() {
		super(Parametro.class);
	}

}
