package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.evento.Tema;
import br.com.libutils.jpa.Repository;

@Local
public interface ComiteMembroRepository extends Repository<ComiteMembro> {

	List<ComiteMembro> membrosComite(Comite c);

	Boolean membroEPresidente(Comite comite, Cadastro cadastro);

	Boolean membroEPresidente(Tema tema, Cadastro cadastro);
	
}
