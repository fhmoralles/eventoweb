package br.com.eventoweb.model.parametros.spec;

import javax.ejb.Local;

import br.com.eventoweb.domain.parametros.LogEvento;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface LogEventoModel extends InterfaceEventoWebModel<LogEvento> {

}
