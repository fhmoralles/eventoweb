package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface ComiteModel extends InterfaceEventoWebModel<Comite> {
	
	List<Comite> comitesEvento(Evento e);

	List<Comite> comitesTema(Tema t);
	
}
