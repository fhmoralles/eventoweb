package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface LocalModel extends InterfaceEventoWebModel<br.com.eventoweb.domain.evento.Local> {
	
	List<br.com.eventoweb.domain.evento.Local> locaisEvento(Evento e);

}
