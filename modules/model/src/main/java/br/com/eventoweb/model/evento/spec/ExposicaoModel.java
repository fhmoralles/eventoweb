package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Exposicao;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface ExposicaoModel extends InterfaceEventoWebModel<Exposicao> {
	
	List<Exposicao> exposicoes(Evento e);
	
}
