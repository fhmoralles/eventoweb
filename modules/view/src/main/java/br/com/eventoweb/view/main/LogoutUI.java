package br.com.eventoweb.view.main;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jboss.seam.security.Identity;
import org.primefaces.context.RequestContext;

import br.com.eventoweb.domain.parametros.LogEvento;
import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.model.parametros.spec.LogEventoModel;
import br.com.eventoweb.view.main.security.EventoUser;

@Named
@RequestScoped
public class LogoutUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(LogoutUI.class);

	@Inject
	private Identity identity;

	@EJB
	private LogEventoModel logEventoModel;

	public String logout() {

		Usuario usuario = ((EventoUser) identity.getUser()).getUsuario();

		try {
			/* Inserindo Log */
			LogEvento logEvento = new LogEvento();
			logEvento.setDescricao("Login - Usuario Email = "
					+ usuario.getEmail());
			logEvento.setEvento("LOGOUT");
			logEvento.setUsuario(usuario);
			logEvento = logEventoModel.update(logEvento);

			/* Invalida a Sessão devido a erro no Identity */
			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession httpSession = httpServletRequest.getSession();
			httpSession.invalidate();
			
			/* Encerra a Identidade */
			identity.logout();

		} catch (Exception e) {
			LOG.error(e, e);
		} finally {
			RequestContext.getCurrentInstance().update("mainForm");
		}

		return "/login.xhtml";

	}
}
