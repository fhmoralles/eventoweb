package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Apoio;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface ApoioModel extends InterfaceEventoWebModel<Apoio> {
	
	List<Apoio> apoiadoresEvento(Evento e);

}
