package br.com.eventoweb.repository.types.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.libutils.jpa.Repository;

@Local
public interface TipoComiteMembroRepository extends Repository<TipoComiteMembro> {
	
	List<TipoComiteMembro> tiposComiteMembro();

	List<TipoComiteMembro> tiposComiteMembro(Evento e);
	
}
