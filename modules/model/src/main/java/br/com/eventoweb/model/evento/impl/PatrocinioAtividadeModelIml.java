package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.PatrocinioAtividade;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.model.evento.spec.PatrocinioAtividadeModel;
import br.com.eventoweb.repository.evento.spec.PatrocinioAtividadeRepository;

@Stateless(name = "patrocinioAtividadeModel")
public class PatrocinioAtividadeModelIml implements PatrocinioAtividadeModel {

	@EJB
	private PatrocinioAtividadeRepository patrocinioAtividadeRepository;
	
	@Override
	public void create(PatrocinioAtividade p) throws Exception {
		patrocinioAtividadeRepository.create(p);
	}

	@Override
	public PatrocinioAtividade retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatrocinioAtividade update(PatrocinioAtividade p) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PatrocinioAtividade p) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void refresh(PatrocinioAtividade p) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PatrocinioAtividade> atividades(PatrocinioTipo p) {
		return patrocinioAtividadeRepository.atividades(p);
	}

}
