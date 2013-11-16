package br.com.eventoweb.model.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.types.TipoDocumento;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface TipoDocumentoModel extends InterfaceEventoWebModel<TipoDocumento> {

	List<TipoDocumento> getTiposDocumento();
	
}
