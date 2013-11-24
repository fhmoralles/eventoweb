package br.com.eventoweb.model.cadastro.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Lugar;
import br.com.eventoweb.domain.cadastro.LugarEspaco;
import br.com.eventoweb.model.cadastro.spec.LugarEspacoModel;
import br.com.eventoweb.repository.cadastro.spec.LugarEspacoRepository;

@Stateless(name = "lugarEspacoModel")
public class LugarEspacoModelImpl implements LugarEspacoModel {

	@EJB
	private LugarEspacoRepository lugarEspacoRepository;

	@Override
	public void create(LugarEspaco c) {
		lugarEspacoRepository.create(c);
	}

	@Override
	public LugarEspaco retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LugarEspaco update(LugarEspaco c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(LugarEspaco c) {
		
		/* Necessario para trazer a entidade para contexto gerenciado */
		c = lugarEspacoRepository.update(c);
		
		/* Excluir */
		lugarEspacoRepository.delete(c);
	}

	@Override
	public void refresh(LugarEspaco c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LugarEspaco> espacosLugar(Lugar lugar) {
		return lugarEspacoRepository.espacosLugar(lugar);
	}

}
