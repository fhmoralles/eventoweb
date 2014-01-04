package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.PatrocinioAtividade;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface PatrocinioAtividadeModel extends InterfaceEventoWebModel<PatrocinioAtividade> {
	
	List<PatrocinioAtividade> atividades(PatrocinioTipo p);

}
