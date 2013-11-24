package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.EventoLocal;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface EventoLocalModel extends InterfaceEventoWebModel<EventoLocal> {
	
	List<EventoLocal> locaisEvento(Evento e);
	
}
