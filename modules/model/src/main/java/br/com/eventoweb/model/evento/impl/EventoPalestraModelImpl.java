package br.com.eventoweb.model.evento.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.EventoPalestra;
import br.com.eventoweb.model.evento.spec.EventoPalestraModel;
import br.com.eventoweb.repository.evento.spec.EventoPalestraRepository;

@Stateless(name = "eventoPalestraModel")
public class EventoPalestraModelImpl implements EventoPalestraModel {

	@EJB
	private EventoPalestraRepository eventoPalestraRepository;

	@Override
	public void create(EventoPalestra c) {
		eventoPalestraRepository.create(c);
	}

	@Override
	public EventoPalestra retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventoPalestra update(EventoPalestra c) throws Exception {
		return eventoPalestraRepository.update(c);
	}

	@Override
	public void delete(EventoPalestra c) {
		
		/* Necessario para trazer a entidade para contexto gerenciado */
		c = eventoPalestraRepository.update(c);
		/* Excluir */
		eventoPalestraRepository.delete(c);
	}

	@Override
	public void refresh(EventoPalestra c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EventoPalestra> palestrasEvento(Evento e) {
		return eventoPalestraRepository.palestrasEvento(e);
	}
	

}
