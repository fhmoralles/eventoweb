package br.com.eventoweb.model.cadastro.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.model.cadastro.spec.ParticipanteModel;
import br.com.eventoweb.repository.cadastro.spec.ParticipanteRepository;

@Stateless(name = "participanteModel")
public class ParticipanteModelimpl implements ParticipanteModel {
	
	@EJB
	private ParticipanteRepository participanteRepository;
	
	@Override
	public void create(Participante c) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Participante c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Participante update(Participante p, Usuario u) throws Exception {
		return this.update(p);
	}

	@Override
	public List<Participante> buscarParticipante(String query) {
		return participanteRepository.buscarParticipante(query);
	}

}
