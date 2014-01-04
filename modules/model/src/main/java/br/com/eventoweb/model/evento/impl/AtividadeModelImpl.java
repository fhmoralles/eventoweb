package br.com.eventoweb.model.evento.impl;


import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Atividade;
import br.com.eventoweb.domain.evento.Financeiro;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.types.TipoFinanceiro;
import br.com.eventoweb.model.evento.spec.AtividadeModel;
import br.com.eventoweb.repository.evento.spec.AtividadeRepository;
import br.com.eventoweb.repository.evento.spec.FinanceiroRepository;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;

@Stateless(name = "atividadeModel")
public class AtividadeModelImpl implements AtividadeModel {

	@EJB
	private AtividadeRepository atividadeRepository;

	@EJB
	private ParticipanteRepository participanteRepository;
	
	@EJB
	private FinanceiroRepository financeiroRepository;
	
	@Override
	public void create(Atividade c) {
		atividadeRepository.create(c);
	}

	@Override
	public Atividade retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Atividade update(Atividade c) throws Exception {
		
		/* Registra o Tipo de Participante */
		Participante participante = c.getParticipante();
		participante = participanteRepository.update(participante);
		c.setParticipante(participante);
		
		/* Registra o Financeiro */
		Financeiro financeiro = new Financeiro();
		financeiro.setDescricao("Saida de valor de atividade");
		financeiro.setEvento(c.getParticipante().getEvento());
		financeiro.setParticipante(participante);
		financeiro.setTipoFinanceiro(TipoFinanceiro.PREVISTO);
		financeiro.setValorFinanceiro(c.getValorAtividade().negate());
		financeiro.setDataPrevisto(Calendar.getInstance().getTime());
		financeiroRepository.update(financeiro);
		
		return atividadeRepository.update(c);
	}

	@Override
	public void delete(Atividade c) {
		
		/* Necessario para trazer a entidade para contexto gerenciado */
		c = atividadeRepository.update(c);
		/* Excluir */
		atividadeRepository.delete(c);
		financeiroRepository.delete(c.getParticipante().getFinanceiro());
		participanteRepository.delete(c.getParticipante());
	}

	@Override
	public void refresh(Atividade c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Atividade> palestrasEvento(Evento e) {
		return atividadeRepository.palestrasEvento(e);
	}

	@Override
	public List<Atividade> cursosEvento(Evento e) {
		return atividadeRepository.cursosEvento(e);
	}

	@Override
	public List<Atividade> minicursosEvento(Evento e) {
		return atividadeRepository.minicursosEvento(e);
	}

	@Override
	public List<Atividade> workshopsEvento(Evento e) {
		return atividadeRepository.workshopsEvento(e);
	}
	
}
