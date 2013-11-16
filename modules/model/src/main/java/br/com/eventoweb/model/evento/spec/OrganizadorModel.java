package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Organizador;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface OrganizadorModel extends InterfaceEventoWebModel<Organizador> {
	
	List<Organizador> organizadoresEvento(Evento e);
	
}
