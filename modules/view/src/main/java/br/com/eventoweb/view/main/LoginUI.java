package br.com.eventoweb.view.main;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;

import br.com.eventoweb.view.evento.MeusEventosUI;
import br.com.eventoweb.view.main.constants.MessagesConstants;
import br.com.eventoweb.view.main.security.EventoUser;
import br.com.webutils.MessageUtil;
import br.com.webutils.ui.AbstractFacesBean;

@Named
@RequestScoped
public class LoginUI extends AbstractFacesBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2596898919057177681L;

	@Inject
	private Identity identity;

	@Inject
	private MeusEventosUI meusEventosUI;
	
	public String login() {

		/* invoca login */
		identity.login();

		if (identity.isLoggedIn()) {

			MessageUtil.addGlobalInfoMessage(
					MessagesConstants.MSG_INFO_LOGIN_SUCESSO,
					((EventoUser) identity.getUser()).getUsuario()
							.getParticipante().getRazaoSocial());
			
			meusEventosUI.getFilter().setDescricao("%meuseventos%");
			return meusEventosUI.prepareSearch();
		}

		return null;
	}

	public String prepareLogin() {
		return "/login.xhtml";
	}

}
