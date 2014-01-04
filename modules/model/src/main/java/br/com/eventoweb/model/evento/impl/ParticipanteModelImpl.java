package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.model.evento.spec.ParticipanteModel;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;

@Stateless(name = "participanteModel")
public class ParticipanteModelImpl implements ParticipanteModel {

	@EJB
	private ParticipanteRepository participanteRepository;

	@Override
	public void create(Participante c) {
		participanteRepository.create(c);
	}

	@Override
	public Participante retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Participante update(Participante c) throws Exception {
		return participanteRepository.update(c);
	}

	@Override
	public void delete(Participante c) {

		/* Necessario para trazer a entidade para contexto gerenciado */
		c = participanteRepository.update(c);

		/* Excluir */
		participanteRepository.delete(c);
	}

	@Override
	public void refresh(Participante c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Participante> organizadoresEvento(Evento e) {
		return participanteRepository.organizadoresEvento(e);
	}

	@Override
	public List<Participante> realizadoresEvento(Evento e) {
		return participanteRepository.realizadoresEvento(e);
	}

	// @Override
	// public List<Participante> apoiadoresEvento(Evento e) {
	// return participanteRepository.apoiadoresEvento(e);
	// }

	@Override
	public List<Participante> locaisEvento(Evento e) {
		return participanteRepository.locaisEvento(e);
	}

}
