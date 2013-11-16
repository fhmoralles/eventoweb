package br.com.eventoweb.model.evento.impl;


import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.model.evento.spec.EventoModel;
import br.com.eventoweb.repository.evento.spec.EventoRepository;

@Stateless(name = "eventoModel")
public class EventoModelImpl implements EventoModel {

	@EJB
	private EventoRepository eventoRepository;
	
	@Override
	public void create(Evento c) {

		Calendar dataCadastro = Calendar.getInstance();
		dataCadastro.setTimeInMillis(System.currentTimeMillis());
		c.setDataCadastro(dataCadastro.getTime());

		eventoRepository.create(c);
		
	}

	@Override
	public Evento retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evento update(Evento c) throws Exception {
		return eventoRepository.update(c);
	}

	@Override
	public void delete(Evento c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Evento c) {
		eventoRepository.refresh(c);
	}

	@Override
	public List<Evento> eventosParticipante(Participante p) {
		return eventoRepository.eventosParticipante(p);
	}

}
