package br.com.eventoweb.model.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.cadastro.Espaco;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface EspacoModel extends InterfaceEventoWebModel<Espaco> {
	
	List<Espaco> espacosParticipante(Cadastro lugar);
	
}
