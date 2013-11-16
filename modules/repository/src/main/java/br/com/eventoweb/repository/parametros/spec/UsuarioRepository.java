package br.com.eventoweb.repository.parametros.spec;

import javax.ejb.Local;

import br.com.eventoweb.domain.parametros.Usuario;
import br.com.libutils.jpa.Repository;

@Local
public interface UsuarioRepository extends Repository<Usuario> {
	
	Usuario localizarPorEmail(String email);
	
}
