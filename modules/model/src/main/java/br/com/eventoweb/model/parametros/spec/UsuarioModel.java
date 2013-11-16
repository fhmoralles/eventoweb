package br.com.eventoweb.model.parametros.spec;

import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;
import br.com.libutils.exception.PasswordConfirmationException;

public interface UsuarioModel extends InterfaceEventoWebModel<Usuario> {

	Usuario login(String email, String senha)
			throws PasswordConfirmationException;

	void enviarEmail(String email, String assunto, String mensagem);

	Usuario update(Usuario u, String url) throws Exception;

}
