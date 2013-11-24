package br.com.eventoweb.model.cadastro.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Lugar;
import br.com.eventoweb.model.cadastro.spec.LugarModel;
import br.com.eventoweb.repository.cadastro.spec.LugarRepository;

@Stateless(name = "lugarModel")
public class LugarModelImpl implements LugarModel {

	@EJB
	private LugarRepository lugarRepository;

	@Override
	public void create(Lugar c) {
		lugarRepository.create(c);
	}

	@Override
	public Lugar retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lugar update(Lugar c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Lugar c) {
		
		/* Necessario para trazer a entidade para contexto gerenciado */
		c = lugarRepository.update(c);
		
		/* Excluir */
		lugarRepository.delete(c);
	}

	@Override
	public void refresh(Lugar c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Lugar> buscarLugar(String query) {
		return lugarRepository.buscarLugar(query);
	}

}
