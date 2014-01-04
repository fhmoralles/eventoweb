package br.com.eventoweb.model.types.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface TipoParticipanteModel extends InterfaceEventoWebModel<TipoParticipante> {

	List<TipoParticipante> tiposParticipante();

	List<TipoParticipante> tiposParticipante(Evento e, Cadastro c);
	
}
