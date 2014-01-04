package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Participante;
import br.com.libutils.jpa.Repository;

@Local
public interface ParticipanteRepository extends Repository<Participante> {

	List<Participante> organizadoresEvento(Evento e);
	
	List<Participante> realizadoresEvento(Evento e);	
	
	List<Participante> apoiadoresEvento(Evento e);

	List<Participante> locaisEvento(Evento e);
	
}
