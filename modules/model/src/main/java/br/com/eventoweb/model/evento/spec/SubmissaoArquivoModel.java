package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.SubmissaoArquivo;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface SubmissaoArquivoModel extends InterfaceEventoWebModel<SubmissaoArquivo> {
	
	List<SubmissaoArquivo> arquivosSubmissao(Submissao submissao);
	
}
