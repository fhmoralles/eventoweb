package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.PatrocinioExposicao;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface PatrocinioExposicaoModel extends InterfaceEventoWebModel<PatrocinioExposicao> {
	
	List<PatrocinioExposicao> exposicoes(PatrocinioTipo p);

}
