package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.types.ArquivoSubmissao;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface SubmissaoModel extends InterfaceEventoWebModel<Submissao> {
	
	List<Submissao> submissoesEvento(Evento e, Cadastro c);

	Submissao update(Submissao submissao, List<ArquivoSubmissao> arquivos) throws Exception;
	
}
