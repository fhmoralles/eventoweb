package br.com.eventoweb.model.types.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface TipoComiteMembroModel extends InterfaceEventoWebModel<TipoComiteMembro> {

	List<TipoComiteMembro> tiposComiteMembro();

	List<TipoComiteMembro> tiposComiteMembro(Evento e, Cadastro c);
	
}
