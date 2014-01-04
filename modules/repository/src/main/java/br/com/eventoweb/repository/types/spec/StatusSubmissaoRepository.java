package br.com.eventoweb.repository.types.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.types.StatusSubmissao;
import br.com.libutils.jpa.Repository;

@Local
public interface StatusSubmissaoRepository extends Repository<StatusSubmissao> {
	
	List<StatusSubmissao> statusSubmissao();
	
}
