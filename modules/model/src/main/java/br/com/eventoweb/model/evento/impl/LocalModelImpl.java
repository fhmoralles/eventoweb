package br.com.eventoweb.model.evento.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Financeiro;
import br.com.eventoweb.domain.evento.Local;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.types.TipoFinanceiro;
import br.com.eventoweb.model.evento.spec.LocalModel;
import br.com.eventoweb.repository.evento.spec.FinanceiroRepository;
import br.com.eventoweb.repository.evento.spec.LocalRepository;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;

@Stateless(name = "localModel")
public class LocalModelImpl implements LocalModel {

	@EJB
	private LocalRepository localRepository;
	
	@EJB
	private ParticipanteRepository participanteRepository;

	@EJB
	private FinanceiroRepository financeiroRepository;
	
	@Override
	public void create(Local c) throws Exception {
		localRepository.create(c);
	}

	@Override
	public Local retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Local update(Local c) throws Exception {
		
		/* Registra o tipo de Participante */
		Participante participante = c.getParticipante();
		participante = participanteRepository.update(participante);
		c.setParticipante(participante);
		
		/* Registra o Financeiro */
		Financeiro financeiro = new Financeiro();
		financeiro.setDescricao("Saida de Valor do Local");
		financeiro.setEvento(c.getParticipante().getEvento());
		financeiro.setParticipante(participante);
		financeiro.setTipoFinanceiro(TipoFinanceiro.PREVISTO);
		financeiro.setValorFinanceiro(c.getValorLocacao().negate());
		financeiro.setDataPrevisto(Calendar.getInstance().getTime());
		financeiroRepository.update(financeiro);
		
		return localRepository.update(c);
	}

	@Override
	public void delete(Local c) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		c = localRepository.update(c);
	
		/* Excluir */
		localRepository.delete(c);
		financeiroRepository.delete(c.getParticipante().getFinanceiro());
		participanteRepository.delete(c.getParticipante());
	}

	@Override
	public void refresh(Local c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Local> locaisEvento(Evento e) {
		return localRepository.locaisEvento(e);
	}

}
