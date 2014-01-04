package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.SubmissaoArquivo;
import br.com.eventoweb.model.evento.spec.SubmissaoArquivoModel;
import br.com.eventoweb.repository.evento.spec.SubmissaoArquivoRepository;

@Stateless(name = "submissaoArquivoModel")
public class SubmissaoArquivoModelIml implements SubmissaoArquivoModel {

	@EJB
	private SubmissaoArquivoRepository submissaoArquivoRepository;

	@Override
	public void create(SubmissaoArquivo s) throws Exception {
		submissaoArquivoRepository.create(s);
	}

	@Override
	public SubmissaoArquivo retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubmissaoArquivo update(SubmissaoArquivo s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(SubmissaoArquivo s) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		s = submissaoArquivoRepository.update(s);

		/* Excluir */
		submissaoArquivoRepository.delete(s);
	}

	@Override
	public void refresh(SubmissaoArquivo s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SubmissaoArquivo> arquivosSubmissao(Submissao submissao) {
		return submissaoArquivoRepository.arquivosSubmissao(submissao);
	}

}
