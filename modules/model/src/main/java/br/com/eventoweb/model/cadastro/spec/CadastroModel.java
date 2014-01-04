package br.com.eventoweb.model.cadastro.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface CadastroModel extends InterfaceEventoWebModel<Cadastro> {
	
	Cadastro update(Cadastro p, Usuario u) throws Exception;
	
	List<Cadastro> buscarCadastro(String query);
	
}
