package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.libutils.jpa.Repository;

@Local
public interface LocalRepository extends Repository<br.com.eventoweb.domain.evento.Local> {

	List<br.com.eventoweb.domain.evento.Local> locaisEvento(Evento e);
	
}
