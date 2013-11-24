package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.EventoOrganizador;
import br.com.libutils.jpa.Repository;

@Local
public interface EventoOrganizadorRepository extends Repository<EventoOrganizador> {

	List<EventoOrganizador> organizadoresEvento(Evento e);
	
}
