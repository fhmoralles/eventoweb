package br.com.eventoweb.model.cadastro.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.cadastro.Espaco;
import br.com.eventoweb.model.cadastro.spec.EspacoModel;
import br.com.eventoweb.repository.cadastro.spec.EspacoRepository;

@Stateless(name = "espacoModel")
public class EspacoModelImpl implements EspacoModel {

	@EJB
	private EspacoRepository espacoRepository;

	@Override
	public void create(Espaco c) {
		espacoRepository.create(c);
	}

	@Override
	public Espaco retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Espaco update(Espaco c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Espaco c) {
		
		/* Necessario para trazer a entidade para contexto gerenciado */
		c = espacoRepository.update(c);
		
		/* Excluir */
		espacoRepository.delete(c);
	}

	@Override
	public void refresh(Espaco c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Espaco> espacosParticipante(Cadastro lugar) {
		return espacoRepository.espacosParticipante(lugar);
	}

}
