package br.com.eventoweb.repository.types.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.eventoweb.domain.types.StatusSubmissao;
import br.com.eventoweb.repository.types.spec.StatusSubmissaoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "statusSubmissaoRepository")
public class StatusSubmissaoRepositoryImpl extends
		AbstractEventoWebRepository<StatusSubmissao> implements
		StatusSubmissaoRepository {

	public StatusSubmissaoRepositoryImpl() {
		super(StatusSubmissao.class);
	}

	@Override
	public List<StatusSubmissao> statusSubmissao() {

		List<StatusSubmissao> tiposParticipante = new ArrayList<StatusSubmissao>();
		
		for(StatusSubmissao statusSubmissao : StatusSubmissao.values() ) {
			tiposParticipante.add(statusSubmissao);
		}
		
		return tiposParticipante;

	}
	
}
