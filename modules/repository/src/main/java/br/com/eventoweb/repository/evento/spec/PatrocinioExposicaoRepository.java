package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.PatrocinioExposicao;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.libutils.jpa.Repository;

@Local
public interface PatrocinioExposicaoRepository extends Repository<PatrocinioExposicao> {

	List<PatrocinioExposicao> exposicoes(PatrocinioTipo p);

}
