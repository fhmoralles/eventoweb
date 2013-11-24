package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.EventoPalestra;
import br.com.libutils.jpa.Repository;

@Local
public interface EventoPalestraRepository extends Repository<EventoPalestra> {

	List<EventoPalestra> palestrasEvento(Evento e);
	
}
