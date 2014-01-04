package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface TemaModel extends InterfaceEventoWebModel<Tema> {
	
	List<Tema> temas(Evento e);
	
}
