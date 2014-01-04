package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.PatrocinioAtividade;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.libutils.jpa.Repository;

@Local
public interface PatrocinioAtividadeRepository extends Repository<PatrocinioAtividade> {

	List<PatrocinioAtividade> atividades(PatrocinioTipo p);

}
