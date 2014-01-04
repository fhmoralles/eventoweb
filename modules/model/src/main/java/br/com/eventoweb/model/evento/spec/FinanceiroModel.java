package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Financeiro;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface FinanceiroModel extends InterfaceEventoWebModel<Financeiro> {

	List<Financeiro> registrosPrevistosFinanceiroEvento(Evento evento);
	
	List<Financeiro> registrosRealizadosFinanceiroEvento(Evento evento);
	
}
