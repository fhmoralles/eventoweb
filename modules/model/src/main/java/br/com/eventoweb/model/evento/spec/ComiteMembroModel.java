package br.com.eventoweb.model.evento.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface ComiteMembroModel extends InterfaceEventoWebModel<ComiteMembro> {
	
	List<ComiteMembro> membrosComite(Comite c);

	Boolean membroEPresidente(Comite comite, Cadastro cadastro);
	
	Boolean membroEPresidente(Tema tema, Cadastro cadastro);

}
