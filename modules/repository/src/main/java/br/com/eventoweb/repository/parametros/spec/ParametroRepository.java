package br.com.eventoweb.repository.parametros.spec;

import javax.ejb.Local;

import br.com.eventoweb.domain.parametros.Parametro;
import br.com.libutils.jpa.Repository;

@Local
public interface ParametroRepository extends Repository<Parametro> {
	
}
