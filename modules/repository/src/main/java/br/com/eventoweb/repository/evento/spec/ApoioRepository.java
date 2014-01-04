package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Apoio;
import br.com.eventoweb.domain.evento.Evento;
import br.com.libutils.jpa.Repository;

@Local
public interface ApoioRepository extends Repository<Apoio> {

	List<Apoio> apoiadoresEvento(Evento e);
	
}
