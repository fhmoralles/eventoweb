package br.com.eventoweb.repository.types.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.repository.types.spec.TipoComiteMembroRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "tipoComiteMembroRepository")
public class TipoComiteMembroRepositoryImpl extends
		AbstractEventoWebRepository<TipoComiteMembro> implements
		TipoComiteMembroRepository {

	public TipoComiteMembroRepositoryImpl() {
		super(TipoComiteMembro.class);
	}

	@Override
	public List<TipoComiteMembro> tiposComiteMembro() {

		List<TipoComiteMembro> tiposComiteMembro = new ArrayList<TipoComiteMembro>();
		
		for(TipoComiteMembro tipoComiteMembro : TipoComiteMembro.values() ) {
			tiposComiteMembro.add(tipoComiteMembro);
		}
		
		return tiposComiteMembro;

	}

	@Override
	public List<TipoComiteMembro> tiposComiteMembro(Evento e) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
