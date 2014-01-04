package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.model.evento.spec.ComiteModel;
import br.com.eventoweb.repository.evento.spec.ComiteRepository;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;

@Stateless(name = "comiteModel")
public class ComiteModelIml implements ComiteModel {

	@EJB
	private ComiteRepository comiteRepository;
	
	@Override
	public void create(Comite c) throws Exception {
		comiteRepository.create(c);
	}

	@Override
	public Comite retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comite update(Comite c) throws Exception {
		return comiteRepository.update(c);
	}

	@Override
	public void delete(Comite c) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		c = comiteRepository.update(c);
	
		/* Excluir */
		comiteRepository.delete(c);
	}

	@Override
	public void refresh(Comite c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comite> comitesEvento(Evento e) {
		return comiteRepository.comitesEvento(e);
	}

	@Override
	public List<Comite> comitesTema(Tema t) {
		return comiteRepository.comitesTema(t);
	}

}
