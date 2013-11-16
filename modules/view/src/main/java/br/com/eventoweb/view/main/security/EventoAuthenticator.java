package br.com.eventoweb.view.main.security;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.api.Credential;
import org.picketlink.idm.impl.api.PasswordCredential;

import br.com.eventoweb.domain.parametros.LogEvento;
import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.model.parametros.spec.LogEventoModel;
import br.com.eventoweb.model.parametros.spec.UsuarioModel;
import br.com.eventoweb.view.main.constants.MessagesConstants;
import br.com.libutils.exception.PasswordConfirmationException;
import br.com.webutils.MessageUtil;

public class EventoAuthenticator extends BaseAuthenticator implements Authenticator {

	private static final Logger LOG = Logger.getLogger(EventoAuthenticator.class);

	@Inject
	private Credentials credentials;

	@Inject
	private LogEventoModel logEventoModel;
	
	@Inject
	private UsuarioModel usuarioModel;

	@Override
	public void authenticate() {

		EventoUser consultUser = null;
		Credential credential = credentials.getCredential();

		if (credential instanceof PasswordCredential) {

			String email = StringUtils.trim(credentials.getUsername());
			String senha = ((PasswordCredential) credential).getValue();
			Usuario usuario = null;
			
			LOG.info("Password Credential: " + email);

			try {
				
				usuario = usuarioModel.login(email, senha);

				if (usuario != null && usuario.getAtivo()) {
					consultUser = new EventoUser(usuario);
					setStatus(AuthenticationStatus.SUCCESS);
					setUser(consultUser);
				} else {
					setStatus(AuthenticationStatus.FAILURE);
					MessageUtil.addGlobalErrorMessage(MessagesConstants.MSG_ERROR_LOGIN_INVALIDO);
				}
				
			} catch (PasswordConfirmationException e) {
				LOG.error(e, e);
				MessageUtil.addGlobalErrorMessage(MessagesConstants.MSG_ERROR_LOGIN_INVALIDO);
				setStatus(AuthenticationStatus.FAILURE);
			}

			/* Inserindo Log */
			LogEvento logEvento = new LogEvento();
			logEvento.setDescricao("Login - Usuario Email = " + email+ "; Status -> " + getStatus());
			logEvento.setEvento("LOGIN");
			logEvento.setUsuario(usuario);
			try {
				logEvento = logEventoModel.update(logEvento);
			} catch (Exception e) {
				LOG.error(e, e);
			}
		
		} else {
			setStatus(AuthenticationStatus.FAILURE);
		}
		
	}
	
}
