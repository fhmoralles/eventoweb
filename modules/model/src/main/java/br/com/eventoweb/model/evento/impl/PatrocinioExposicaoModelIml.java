package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.PatrocinioExposicao;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.model.evento.spec.PatrocinioExposicaoModel;
import br.com.eventoweb.repository.evento.spec.PatrocinioExposicaoRepository;

@Stateless(name = "patrocinioExposicaoModel")
public class PatrocinioExposicaoModelIml implements PatrocinioExposicaoModel {

	@EJB
	private PatrocinioExposicaoRepository patrocinioExposicaoRepository;
	
	@Override
	public void create(PatrocinioExposicao p) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public PatrocinioExposicao retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatrocinioExposicao update(PatrocinioExposicao p) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PatrocinioExposicao p) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void refresh(PatrocinioExposicao p) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PatrocinioExposicao> exposicoes(PatrocinioTipo p) {
		return patrocinioExposicaoRepository.exposicoes(p);
	}

}
