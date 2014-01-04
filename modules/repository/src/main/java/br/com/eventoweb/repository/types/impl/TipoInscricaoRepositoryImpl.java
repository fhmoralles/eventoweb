package br.com.eventoweb.repository.types.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.eventoweb.domain.types.TipoInscricao;
import br.com.eventoweb.repository.types.spec.TipoInscricaoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "tipoInscricaoRepository")
public class TipoInscricaoRepositoryImpl extends
		AbstractEventoWebRepository<TipoInscricao> implements
		TipoInscricaoRepository {

	public TipoInscricaoRepositoryImpl() {
		super(TipoInscricao.class);
	}

	@Override
	public List<TipoInscricao> tiposInscricao() {

		List<TipoInscricao> tiposInscricao = new ArrayList<TipoInscricao>();
		
		for(TipoInscricao tipoInscricao : TipoInscricao.values() ) {
			tiposInscricao.add(tipoInscricao);
		}
		
		return tiposInscricao;

	}

	
}
