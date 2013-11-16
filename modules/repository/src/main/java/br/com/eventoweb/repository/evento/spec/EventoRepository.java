package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Organizador;
import br.com.libutils.jpa.Repository;

@Local
public interface EventoRepository extends Repository<Evento> {

	List<Evento> eventosParticipante(Participante p);
	
}
