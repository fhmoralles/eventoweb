package br.com.eventoweb.repository.types.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.libutils.jpa.Repository;

@Local
public interface TipoParticipanteRepository extends Repository<TipoParticipante> {
	
	List<TipoParticipante> tiposParticipante();

	List<TipoParticipante> tiposParticipante(Evento e);
	
}
