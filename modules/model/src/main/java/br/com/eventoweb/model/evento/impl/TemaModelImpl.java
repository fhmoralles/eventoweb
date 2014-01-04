package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.model.evento.spec.TemaModel;
import br.com.eventoweb.repository.evento.spec.TemaRepository;

@Stateless(name = "temaModel")
public class TemaModelImpl implements TemaModel {

	@EJB
	private TemaRepository temaRepository;
	
	@Override
	public void create(Tema c) throws Exception {
		temaRepository.create(c);
	}

	@Override
	public Tema retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tema update(Tema c) throws Exception {
		return temaRepository.update(c);
	}

	@Override
	public void delete(Tema c) throws Exception {
		/* Excluir */
		c = temaRepository.update(c);
		temaRepository.delete(c);
	}

	@Override
	public void refresh(Tema c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tema> temas(Evento e) {
		return temaRepository.temas(e);
	}

}
