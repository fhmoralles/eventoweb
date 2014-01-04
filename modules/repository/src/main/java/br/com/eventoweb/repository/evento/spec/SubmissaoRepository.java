package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Submissao;
import br.com.libutils.jpa.Repository;

@Local
public interface SubmissaoRepository extends Repository<Submissao> {

	List<Submissao> submissoesCriadorEvento(Evento e);
	
	List<Submissao> submissoesComiteEvento(Evento e, Cadastro c);
	
}
