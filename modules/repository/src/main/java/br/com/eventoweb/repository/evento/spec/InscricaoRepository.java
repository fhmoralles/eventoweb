package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Inscricao;
import br.com.libutils.jpa.Repository;

@Local
public interface InscricaoRepository extends Repository<Inscricao> {

	List<Inscricao> inscricoesEvento(Evento e);
	
}
