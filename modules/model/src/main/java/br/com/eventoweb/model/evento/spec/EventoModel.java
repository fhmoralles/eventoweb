package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface EventoModel extends InterfaceEventoWebModel<Evento> {
	
	List<Evento> eventosParticipante(Participante p);
	
}
