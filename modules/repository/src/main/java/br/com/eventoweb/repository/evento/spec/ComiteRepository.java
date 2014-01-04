package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Tema;
import br.com.libutils.jpa.Repository;

@Local
public interface ComiteRepository extends Repository<Comite> {

	List<Comite> comitesEvento(Evento e);

	List<Comite> comitesTema(Tema t);
	
}
