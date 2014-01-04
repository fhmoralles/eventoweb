package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Atividade;
import br.com.libutils.jpa.Repository;

@Local
public interface AtividadeRepository extends Repository<Atividade> {

	List<Atividade> palestrasEvento(Evento e);

	List<Atividade> cursosEvento(Evento e);

	List<Atividade> minicursosEvento(Evento e);

	List<Atividade> workshopsEvento(Evento e);
	
}
