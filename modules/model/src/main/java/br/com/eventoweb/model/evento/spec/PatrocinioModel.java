package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Patrocinio;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface PatrocinioModel extends InterfaceEventoWebModel<Patrocinio> {
	
	List<Patrocinio> patrocinadoresEvento(Evento e);

}
