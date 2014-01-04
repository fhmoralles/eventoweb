package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Exposicao;
import br.com.eventoweb.model.evento.spec.ExposicaoModel;
import br.com.eventoweb.repository.evento.spec.ExposicaoRepository;

@Stateless(name = "exposicaoModel")
public class ExposicaoModelImpl implements ExposicaoModel {

	@EJB
	private ExposicaoRepository exposicaoRepository;
	
	@Override
	public void create(Exposicao c) throws Exception {
		exposicaoRepository.create(c);
	}

	@Override
	public Exposicao retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exposicao update(Exposicao c) throws Exception {
		return exposicaoRepository.update(c);
	}

	@Override
	public void delete(Exposicao c) throws Exception {
		/* Excluir */
		c = exposicaoRepository.update(c);
		exposicaoRepository.delete(c);
	}

	@Override
	public void refresh(Exposicao c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Exposicao> exposicoes(Evento e) {
		return exposicaoRepository.exposicoes(e);
	}

}
