package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.SubmissaoAvaliacao;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface SubmissaoAvaliacaoModel extends InterfaceEventoWebModel<SubmissaoAvaliacao> {
	
	List<SubmissaoAvaliacao> avaliacoesSubmissao(Submissao submissao);
	
}
