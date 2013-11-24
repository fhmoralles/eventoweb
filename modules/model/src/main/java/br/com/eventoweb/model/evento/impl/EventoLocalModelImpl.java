package br.com.eventoweb.model.evento.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.EventoLocal;
import br.com.eventoweb.model.evento.spec.EventoLocalModel;
import br.com.eventoweb.repository.evento.spec.EventoLocalRepository;

@Stateless(name = "eventoLocalModel")
public class EventoLocalModelImpl implements EventoLocalModel {

	@EJB
	private EventoLocalRepository eventoLocalRepository;

	@Override
	public void create(EventoLocal c) throws Exception {
		eventoLocalRepository.create(c);
	}

	@Override
	public EventoLocal retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventoLocal update(EventoLocal c) throws Exception {
		return eventoLocalRepository.update(c);
	}

	@Override
	public void delete(EventoLocal c) throws Exception {
		
		/* Necessario para trazer a entidade para contexto gerenciado */
		c = eventoLocalRepository.update(c);
		/* Excluir */
		eventoLocalRepository.delete(c);
	}

	@Override
	public void refresh(EventoLocal c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EventoLocal> locaisEvento(Evento e) {
		List<EventoLocal> locais = eventoLocalRepository.locaisEvento(e);
		
		for(EventoLocal eventoLocal: locais) {
			eventoLocal.getLugar().getLugaresEspaco();
		}
		
		return locais;
	}
	

}
