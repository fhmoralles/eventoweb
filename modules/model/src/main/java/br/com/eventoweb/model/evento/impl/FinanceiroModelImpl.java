package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Financeiro;
import br.com.eventoweb.model.evento.spec.FinanceiroModel;
import br.com.eventoweb.repository.evento.spec.FinanceiroRepository;

@Stateless(name = "financeiroModel")
public class FinanceiroModelImpl implements FinanceiroModel {

	@EJB
	private FinanceiroRepository financeiroRepository;
	
	@Override
	public void create(Financeiro c) throws Exception {
		financeiroRepository.create(c);
	}

	@Override
	public Financeiro retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Financeiro update(Financeiro c) throws Exception {
		return financeiroRepository.update(c);
	}

	@Override
	public void delete(Financeiro c) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		c = financeiroRepository.update(c);
	
		/* Excluir */
		financeiroRepository.delete(c);
	}

	@Override
	public void refresh(Financeiro c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Financeiro> registrosPrevistosFinanceiroEvento(Evento evento) {
		return financeiroRepository.registrosPrevistosFinanceiroEvento(evento);
	}

	@Override
	public List<Financeiro> registrosRealizadosFinanceiroEvento(
			Evento evento) {
		return financeiroRepository.registrosRealizadosFinanceiroEvento(evento);
	}

}
