package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Tema;
import br.com.libutils.jpa.Repository;

@Local
public interface TemaRepository extends Repository<Tema> {
	
	List<Tema> temas(Evento e);
	
}
