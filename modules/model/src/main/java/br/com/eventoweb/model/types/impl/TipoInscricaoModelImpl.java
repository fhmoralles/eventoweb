package br.com.eventoweb.model.types.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.types.TipoInscricao;
import br.com.eventoweb.model.types.spec.TipoInscricaoModel;
import br.com.eventoweb.repository.types.spec.TipoInscricaoRepository;

@Stateless(name = "tipoInscricaoModel")
public class TipoInscricaoModelImpl implements TipoInscricaoModel {

	@EJB
	private TipoInscricaoRepository tipoInscricaoRepository;

	@Override
	public void create(TipoInscricao c) {
		// TODO Auto-generated method stub
	}

	@Override
	public TipoInscricao retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoInscricao update(TipoInscricao c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TipoInscricao c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(TipoInscricao c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoInscricao> tiposInscricao() {
		return tipoInscricaoRepository.tiposInscricao();
	}


}
