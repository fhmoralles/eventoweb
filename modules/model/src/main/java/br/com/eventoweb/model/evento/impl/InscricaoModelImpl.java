package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Inscricao;
import br.com.eventoweb.model.evento.spec.InscricaoModel;
import br.com.eventoweb.repository.evento.spec.InscricaoRepository;

@Stateless(name = "inscricaoModel")
public class InscricaoModelImpl implements InscricaoModel {

	@EJB
	private InscricaoRepository inscricaoRepository;
	
	@Override
	public void create(Inscricao c) throws Exception {
		inscricaoRepository.create(c);
	}

	@Override
	public Inscricao retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inscricao update(Inscricao c) throws Exception {
		return inscricaoRepository.update(c);
	}

	@Override
	public void delete(Inscricao c) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		c = inscricaoRepository.update(c);
		/* Excluir */
		inscricaoRepository.delete(c);
	}

	@Override
	public void refresh(Inscricao c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Inscricao> inscricoesEvento(Evento e) {
		return inscricaoRepository.inscricoesEvento(e);
	}

}
