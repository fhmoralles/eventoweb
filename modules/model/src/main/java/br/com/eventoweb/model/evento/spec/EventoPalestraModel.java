package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.EventoPalestra;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface EventoPalestraModel extends InterfaceEventoWebModel<EventoPalestra> {
	
	List<EventoPalestra> palestrasEvento(Evento e);
	
}
