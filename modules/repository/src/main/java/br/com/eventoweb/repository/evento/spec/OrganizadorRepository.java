package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Organizador;
import br.com.libutils.jpa.Repository;

@Local
public interface OrganizadorRepository extends Repository<Organizador> {

	List<Organizador> organizadoresEvento(Evento e);
	
}
