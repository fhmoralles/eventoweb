package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Exposicao;
import br.com.libutils.jpa.Repository;

@Local
public interface ExposicaoRepository extends Repository<Exposicao> {
	
	List<Exposicao> exposicoes(Evento e);
	
}
