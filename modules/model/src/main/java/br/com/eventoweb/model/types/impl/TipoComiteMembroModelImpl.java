package br.com.eventoweb.model.types.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.model.types.spec.TipoComiteMembroModel;
import br.com.eventoweb.repository.types.spec.TipoComiteMembroRepository;

@Stateless(name = "tipoComiteMembroModel")
public class TipoComiteMembroModelImpl implements TipoComiteMembroModel {

	@EJB
	private TipoComiteMembroRepository tipoComiteMembroRepository;

	@Override
	public void create(TipoComiteMembro c) {
		// TODO Auto-generated method stub
	}

	@Override
	public TipoComiteMembro retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoComiteMembro update(TipoComiteMembro c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TipoComiteMembro c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(TipoComiteMembro c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoComiteMembro> tiposComiteMembro() {
		return tipoComiteMembroRepository.tiposComiteMembro();
	}

	@Override
	public List<TipoComiteMembro> tiposComiteMembro(Evento e, Cadastro c) {
		return tipoComiteMembroRepository.tiposComiteMembro(e);
	}


}
