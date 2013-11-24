package br.com.eventoweb.model.evento.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.EventoOrganizador;
import br.com.eventoweb.model.evento.spec.EventoOrganizadorModel;
import br.com.eventoweb.repository.evento.spec.EventoOrganizadorRepository;

@Stateless(name = "eventoOrganizadorModel")
public class EventoOrganizadorModelImpl implements EventoOrganizadorModel {

	@EJB
	private EventoOrganizadorRepository eventoOrganizadorRepository;

	@Override
	public void create(EventoOrganizador c) {
		eventoOrganizadorRepository.create(c);
	}

	@Override
	public EventoOrganizador retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventoOrganizador update(EventoOrganizador c) throws Exception {
		return eventoOrganizadorRepository.update(c);
	}

	@Override
	public void delete(EventoOrganizador c) {
		
		/* Necessario para trazer a entidade para contexto gerenciado */
		c = eventoOrganizadorRepository.update(c);
		
		/* Excluir */
		eventoOrganizadorRepository.delete(c);
	}

	@Override
	public void refresh(EventoOrganizador c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EventoOrganizador> organizadoresEvento(Evento e) {
		return eventoOrganizadorRepository.organizadoresEvento(e);
	}
	

}
