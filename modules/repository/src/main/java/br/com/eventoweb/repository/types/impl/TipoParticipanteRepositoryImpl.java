package br.com.eventoweb.repository.types.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.repository.types.spec.TipoParticipanteRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "tipoParticipanteRepository")
public class TipoParticipanteRepositoryImpl extends
		AbstractEventoWebRepository<TipoParticipante> implements
		TipoParticipanteRepository {

	public TipoParticipanteRepositoryImpl() {
		super(TipoParticipante.class);
	}

	@Override
	public List<TipoParticipante> tiposParticipante() {

		List<TipoParticipante> tiposParticipante = new ArrayList<TipoParticipante>();
		
		for(TipoParticipante tipoParticipante : TipoParticipante.values() ) {
			tiposParticipante.add(tipoParticipante);
		}
		
		return tiposParticipante;

	}

	@Override
	public List<TipoParticipante> tiposParticipante(Evento e) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
