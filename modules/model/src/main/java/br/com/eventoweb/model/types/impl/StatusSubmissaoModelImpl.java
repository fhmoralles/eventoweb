package br.com.eventoweb.model.types.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.types.StatusSubmissao;
import br.com.eventoweb.model.types.spec.StatusSubmissaoModel;
import br.com.eventoweb.repository.types.spec.StatusSubmissaoRepository;

@Stateless(name = "statusSubmissaoModel")
public class StatusSubmissaoModelImpl implements StatusSubmissaoModel {

	@EJB
	private StatusSubmissaoRepository statusSubmissaoRepository;

	@Override
	public void create(StatusSubmissao c) {
		// TODO Auto-generated method stub
	}

	@Override
	public StatusSubmissao retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusSubmissao update(StatusSubmissao c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(StatusSubmissao c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(StatusSubmissao c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StatusSubmissao> statusSubmissao() {
		return statusSubmissaoRepository.statusSubmissao();
	}

}
