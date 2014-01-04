package br.com.eventoweb.model.evento.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.evento.spec.EventoModel;
import br.com.eventoweb.repository.evento.spec.EventoRepository;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;

@Stateless(name = "eventoModel")
public class EventoModelImpl implements EventoModel {

	@EJB
	private EventoRepository eventoRepository;
	
	@EJB
	private ParticipanteRepository participanteRepository;
	
	@Override
	public void create(Evento e) {
		eventoRepository.create(e);
	}

	@Override
	public Evento retrieve(Long id) {
		return eventoRepository.retrieve(id);
	}

	@Override
	public Evento update(Evento e) throws Exception {
		return eventoRepository.update(e);
	}

	@Override
	public Evento update(Evento e, Cadastro c) throws Exception {
		
		Evento evento = eventoRepository.update(e);

		Participante participante = new Participante();
		participante.setEvento(evento);
		participante.setCadastro(c);
		participante.setTipoParticipante(TipoParticipante.CRIADOR);
		participante = participanteRepository.update(participante);
		
		return evento;
	}
	
	@Override
	public void delete(Evento e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Evento e) {
		eventoRepository.refresh(e);
	}

	@Override
	public List<Evento> eventosCadastro(Cadastro c) {
		return eventoRepository.eventosCadastro(c);
	}

	@Override
	public List<Evento> eventosPorNome(String nome) {
		return eventoRepository.eventosPorNome(nome);
	}

}
