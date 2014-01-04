package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.model.evento.spec.PatrocinioTipoModel;
import br.com.eventoweb.repository.evento.spec.PatrocinioTipoRepository;

@Stateless(name = "patrocinioTipoModel")
public class PatrocinioTipoModelIml implements PatrocinioTipoModel {

	@EJB
	private PatrocinioTipoRepository patrocinioTipoRepository;
	
	@Override
	public void create(PatrocinioTipo p) throws Exception {
		patrocinioTipoRepository.create(p);
	}

	@Override
	public PatrocinioTipo retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatrocinioTipo update(PatrocinioTipo p) throws Exception {
		return patrocinioTipoRepository.update(p);
	}

	@Override
	public void delete(PatrocinioTipo p) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		p = patrocinioTipoRepository.update(p);
		/* Excluir */
		patrocinioTipoRepository.delete(p);
	}

	@Override
	public void refresh(PatrocinioTipo p) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PatrocinioTipo> patrocinioTipos(Evento e) {
		return patrocinioTipoRepository.patrocinioTipos(e);
	}

}
