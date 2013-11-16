package br.com.eventoweb.repository.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.types.TipoDocumento;
import br.com.libutils.jpa.Repository;

@Local
public interface TipoDocumentoRepository extends Repository<TipoDocumento> {
	
	List<TipoDocumento> getTiposDocumento();
	
}
