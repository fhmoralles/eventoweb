package br.com.eventoweb.repository.cadastro.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.eventoweb.domain.types.TipoDocumento;
import br.com.eventoweb.repository.cadastro.spec.TipoDocumentoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "tipoDocumentoRepository")
public class TipoDocumentoRepositoryImpl extends
		AbstractEventoWebRepository<TipoDocumento> implements
		TipoDocumentoRepository {

	public TipoDocumentoRepositoryImpl() {
		super(TipoDocumento.class);
	}

	@Override
	public List<TipoDocumento> getTiposDocumento() {

		List<TipoDocumento> tiposDocumentos = new ArrayList<TipoDocumento>();
		
		for(TipoDocumento tipoDocumento : TipoDocumento.values() ) {
			tiposDocumentos.add(tipoDocumento);
		}
		
		return tiposDocumentos;

	}

	
}
