package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.EventoLocal;
import br.com.libutils.jpa.Repository;

@Local
public interface EventoLocalRepository extends Repository<EventoLocal> {

	List<EventoLocal> locaisEvento(Evento e);
	
}
