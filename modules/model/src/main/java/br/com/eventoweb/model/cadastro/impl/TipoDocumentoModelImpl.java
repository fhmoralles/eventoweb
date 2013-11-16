package br.com.eventoweb.model.cadastro.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.types.TipoDocumento;
import br.com.eventoweb.model.cadastro.spec.TipoDocumentoModel;
import br.com.eventoweb.repository.cadastro.spec.TipoDocumentoRepository;

@Stateless(name = "tipoDocumentoModel")
public class TipoDocumentoModelImpl implements TipoDocumentoModel {

	@EJB
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Override
	public void create(TipoDocumento c) {
		// TODO Auto-generated method stub
	}

	@Override
	public TipoDocumento retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoDocumento update(TipoDocumento c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TipoDocumento c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(TipoDocumento c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoDocumento> getTiposDocumento() {
		return tipoDocumentoRepository.getTiposDocumento();
	}


}
