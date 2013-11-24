package br.com.eventoweb.model.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Lugar;
import br.com.eventoweb.domain.cadastro.LugarEspaco;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface LugarEspacoModel extends InterfaceEventoWebModel<LugarEspaco> {
	
	List<LugarEspaco> espacosLugar(Lugar lugar);
	
}
