package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Financeiro;
import br.com.libutils.jpa.Repository;

@Local
public interface FinanceiroRepository extends Repository<Financeiro> {

	List<Financeiro> registrosPrevistosFinanceiroEvento(Evento evento);

	List<Financeiro> registrosRealizadosFinanceiroEvento(Evento evento);
	
}
