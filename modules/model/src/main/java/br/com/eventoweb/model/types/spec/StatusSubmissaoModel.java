package br.com.eventoweb.model.types.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.types.StatusSubmissao;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface StatusSubmissaoModel extends InterfaceEventoWebModel<StatusSubmissao> {

	List<StatusSubmissao> statusSubmissao();
	
}
