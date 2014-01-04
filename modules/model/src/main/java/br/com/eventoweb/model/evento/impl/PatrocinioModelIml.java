package br.com.eventoweb.model.evento.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Financeiro;
import br.com.eventoweb.domain.evento.Patrocinio;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.types.TipoFinanceiro;
import br.com.eventoweb.model.evento.spec.PatrocinioModel;
import br.com.eventoweb.repository.evento.spec.FinanceiroRepository;
import br.com.eventoweb.repository.evento.spec.PatrocinioRepository;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;

@Stateless(name = "patrocinioModel")
public class PatrocinioModelIml implements PatrocinioModel {

	@EJB
	private PatrocinioRepository patrocinioRepository;
	
	@EJB
	private ParticipanteRepository participanteRepository;
	
	@EJB
	private FinanceiroRepository financeiroRepository;

	@Override
	public void create(Patrocinio c) throws Exception {
		patrocinioRepository.create(c);
	}

	@Override
	public Patrocinio retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patrocinio update(Patrocinio c) throws Exception {
		
		/* Resgistra o Participante como PATROCINADOR */
		Participante participante = c.getParticipante();
		participante = participanteRepository.update(participante);
		c.setParticipante(participante);
		
		/* Registrar o valor no Financeiro */
		Financeiro financeiro = new Financeiro();
		financeiro.setDescricao("Entrada de Patrocínio");
		financeiro.setEvento(c.getParticipante().getEvento());
		financeiro.setParticipante(participante);
		financeiro.setTipoFinanceiro(TipoFinanceiro.PREVISTO);
		financeiro.setValorFinanceiro(c.getValorPatrocinio());
		financeiro.setDataPrevisto(Calendar.getInstance().getTime());
		financeiroRepository.update(financeiro);
		
		return patrocinioRepository.update(c);
	}

	@Override
	public void delete(Patrocinio c) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		c = patrocinioRepository.update(c);
	
		/* Excluir */
		patrocinioRepository.delete(c);
		financeiroRepository.delete(c.getParticipante().getFinanceiro());
		participanteRepository.delete(c.getParticipante());
	}

	@Override
	public void refresh(Patrocinio c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Patrocinio> patrocinadoresEvento(Evento e) {
		return patrocinioRepository.patrocinadoresEvento(e);
	}

}
