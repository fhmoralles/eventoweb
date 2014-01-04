package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Atividade;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface AtividadeModel extends InterfaceEventoWebModel<Atividade> {
	
	List<Atividade> palestrasEvento(Evento e);

	List<Atividade> cursosEvento(Evento e);

	List<Atividade> minicursosEvento(Evento e);

	List<Atividade> workshopsEvento(Evento e);
	
}
