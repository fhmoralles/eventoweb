package br.com.eventoweb.model.types.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.types.spec.TipoParticipanteModel;
import br.com.eventoweb.repository.types.spec.TipoParticipanteRepository;

@Stateless(name = "tipoParticipanteModel")
public class TipoParticipanteModelImpl implements TipoParticipanteModel {

	@EJB
	private TipoParticipanteRepository tipoParticipanteRepository;

	@Override
	public void create(TipoParticipante c) {
		// TODO Auto-generated method stub
	}

	@Override
	public TipoParticipante retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoParticipante update(TipoParticipante c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TipoParticipante c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(TipoParticipante c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoParticipante> tiposParticipante() {
		return tipoParticipanteRepository.tiposParticipante();
	}

	@Override
	public List<TipoParticipante> tiposParticipante(Evento e, Cadastro c) {
		return tipoParticipanteRepository.tiposParticipante(e);
	}


}
