package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Patrocinio;
import br.com.libutils.jpa.Repository;

@Local
public interface PatrocinioRepository extends Repository<Patrocinio> {

	List<Patrocinio> patrocinadoresEvento(Evento e);
	
}
