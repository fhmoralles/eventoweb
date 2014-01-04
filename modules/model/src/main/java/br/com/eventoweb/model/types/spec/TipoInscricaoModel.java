package br.com.eventoweb.model.types.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.types.TipoInscricao;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface TipoInscricaoModel extends InterfaceEventoWebModel<TipoInscricao> {

	List<TipoInscricao> tiposInscricao();
	
}
