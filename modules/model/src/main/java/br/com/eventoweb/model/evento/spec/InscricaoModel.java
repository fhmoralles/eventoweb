package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Inscricao;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface InscricaoModel extends InterfaceEventoWebModel<Inscricao> {
	
	List<Inscricao> inscricoesEvento(Evento e);

}
