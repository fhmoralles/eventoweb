package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.SubmissaoAvaliacao;
import br.com.libutils.jpa.Repository;

@Local
public interface SubmissaoAvaliacaoRepository extends Repository<SubmissaoAvaliacao> {

	List<SubmissaoAvaliacao> avaliacoesSubmissao(Submissao submissao);
	
}
