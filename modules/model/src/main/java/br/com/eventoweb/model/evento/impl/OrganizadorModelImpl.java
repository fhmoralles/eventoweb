package br.com.eventoweb.model.evento.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Organizador;
import br.com.eventoweb.model.evento.spec.OrganizadorModel;
import br.com.eventoweb.repository.evento.spec.OrganizadorRepository;

@Stateless(name = "organizadorModel")
public class OrganizadorModelImpl implements OrganizadorModel {

	@EJB
	private OrganizadorRepository organizadorRepository;

	@Override
	public void create(Organizador c) {
		organizadorRepository.create(c);
	}

	@Override
	public Organizador retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organizador update(Organizador c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Organizador c) {
		
		/* Necessario para trazer a entidade para contexto gerenciado */
		c = organizadorRepository.update(c);
		
		/* Excluir */
		organizadorRepository.delete(c);
	}

	@Override
	public void refresh(Organizador c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Organizador> organizadoresEvento(Evento e) {
		return organizadorRepository.organizadoresEvento(e);
	}
	

}
