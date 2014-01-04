package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.SubmissaoArquivo;
import br.com.libutils.jpa.Repository;

@Local
public interface SubmissaoArquivoRepository extends Repository<SubmissaoArquivo> {

	List<SubmissaoArquivo> arquivosSubmissao(Submissao submissao);
	
}
