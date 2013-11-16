package br.com.eventoweb.repository.parametros.spec;

import javax.ejb.Local;

import br.com.eventoweb.domain.parametros.LogEvento;
import br.com.libutils.jpa.Repository;

@Local
public interface LogEventoRepository extends Repository<LogEvento> {

}
