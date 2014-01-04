package br.com.eventoweb.repository.cadastro.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.libutils.jpa.Repository;

@Local
public interface CadastroRepository extends Repository<Cadastro> {

	List<Cadastro> buscarCadastro(String query);
	
}
