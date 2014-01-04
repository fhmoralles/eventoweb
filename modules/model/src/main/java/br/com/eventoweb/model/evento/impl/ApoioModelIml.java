package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Apoio;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.model.evento.spec.ApoioModel;
import br.com.eventoweb.repository.evento.spec.ApoioRepository;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;

@Stateless(name = "apoioModel")
public class ApoioModelIml implements ApoioModel {

	@EJB
	private ApoioRepository apoioRepository;
	
	@EJB
	private ParticipanteRepository participanteRepository;
	
	@Override
	public void create(Apoio c) throws Exception {
		apoioRepository.create(c);
	}

	@Override
	public Apoio retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apoio update(Apoio c) throws Exception {
		
		/* Resgistra o Participante como PATROCINADOR */
		Participante participante = c.getParticipante();
		participante = participanteRepository.update(participante);
		c.setParticipante(participante);
		
		return apoioRepository.update(c);
	}

	@Override
	public void delete(Apoio c) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		c = apoioRepository.update(c);
	
		/* Excluir */
		apoioRepository.delete(c);
		participanteRepository.delete(c.getParticipante());
	}

	@Override
	public void refresh(Apoio c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Apoio> apoiadoresEvento(Evento e) {
		return apoioRepository.apoiadoresEvento(e);
	}

}
