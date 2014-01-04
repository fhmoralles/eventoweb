package br.com.eventoweb.repository.types.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.types.TipoInscricao;
import br.com.libutils.jpa.Repository;

@Local
public interface TipoInscricaoRepository extends Repository<TipoInscricao> {
	
	List<TipoInscricao> tiposInscricao();
	
}
