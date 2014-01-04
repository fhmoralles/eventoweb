package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface PatrocinioTipoModel extends InterfaceEventoWebModel<PatrocinioTipo> {
	
	List<PatrocinioTipo> patrocinioTipos(Evento e);

}
