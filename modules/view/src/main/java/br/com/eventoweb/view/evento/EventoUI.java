package br.com.eventoweb.view.evento;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.model.evento.spec.EventoModel;
import br.com.eventoweb.view.evento.filter.EventoFilter;
import br.com.eventoweb.view.main.constants.MessagesConstants;
import br.com.eventoweb.view.main.security.EventoUser;
import br.com.webutils.MessageUtil;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class EventoUI extends AbstractCRUD<Evento, EventoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5533016745298915585L;

	private static final Logger LOG = Logger.getLogger(EventoUI.class);

	private static final String EVENTO_CADASTRAR = "/forms/eventos/eventoCadastrar.xhtml";

	/* Model */
	@Inject
	private EventoModel eventoModel;

	/* Informações de Login */
	@Inject
	private Identity identity;

	/* Injeta o UI dos meus eventos */
	@Inject
	private MeusEventosUI meusEventosUI;

	/* Dominios */

	public EventoUI() {
		super(new EventoFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteImpl(Evento bean) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getMsgDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionCreate() {
		return EVENTO_CADASTRAR;
	}

	@Override
	protected String getActionDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionEdit() {

		EventoUser eventoUser = (EventoUser) identity.getUser();
		Participante participante = eventoUser.getUsuario().getParticipante();

		/* Se for o criado/oganizador do evento -> página de Edição */
		if (this.getBean().getParticipante().equals(participante)) {

			/* Retorna a tela de edição */
			return EVENTO_CADASTRAR;
		}

		return null;
	}

	@Override
	protected String getActionSearch() {
		return meusEventosUI.prepareSearch();
	}

	@Override
	public boolean isDeletable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEditable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInsertable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSearchable() {
		return true;
	}

	@Override
	protected boolean isSearchOnPrepare() {
		return false;
	}

	@Override
	protected boolean isValidBean(Evento bean) {

		Calendar dataAtual = Calendar.getInstance();
		dataAtual.setTimeInMillis(System.currentTimeMillis());

		LOG.info(bean.getDataEvento());

		if (bean.getDataEvento().compareTo(dataAtual.getTime()) >= 0) {
			return true;
		}

		LOG.info(MessagesConstants.MSG_ERROR_DATA_EVENTO_INVALIDO);
		MessageUtil.addComponentErrorMessage(
				"contentEventoForm_inputDataEventoText",
				MessagesConstants.MSG_ERROR_DATA_EVENTO_INVALIDO);

		return false;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Evento newInstance() {
		return new Evento();
	}

	@Override
	protected void saveImpl(Evento bean) throws Exception {

		this.getBean().setParticipante(
				((EventoUser) identity.getUser()).getUsuario()
						.getParticipante());
		eventoModel.update(this.getBean());
	}

	@Override
	protected List<Evento> searchImpl(EventoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
