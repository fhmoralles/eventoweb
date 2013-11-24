package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.EventoOrganizador;
import br.com.eventoweb.model.evento.spec.EventoOrganizadorModel;
import br.com.eventoweb.view.evento.filter.OrganizadorFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class EventoOrganizadorUI extends AbstractCRUD<EventoOrganizador, OrganizadorFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6991662102398733893L;

	//private static final Logger LOG = Logger.getLogger(OrganizadorUI.class);
	
	private static final String EVENTO_ORGANIZADOR_PESQUISAR = "/forms/eventos/eventoOrganizadorPesquisar.xhtml";
	private static final String EVENTO_ORGANIZADOR_CADASTRAR = "/forms/eventos/eventoOrganizadorCadastrar.xhtml";

	@Inject
	private EventoOrganizadorModel eventoOrganizadorModel;

	public EventoOrganizadorUI() {
		super(new OrganizadorFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(EventoOrganizador bean) throws Exception {
		eventoOrganizadorModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null && this.getBean().getParticipante() != null) {
			return this.getBean().getParticipante().getRazaoSocial();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		
		this.getBean().setParticipante(null);
		return EVENTO_ORGANIZADOR_CADASTRAR;
	}

	@Override
	protected String getActionDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionSearch() {
		return EVENTO_ORGANIZADOR_PESQUISAR;
	}

	@Override
	public boolean isDeletable() {
		return true;
	}

	@Override
	public boolean isEditable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInsertable() {
		return true;
	}

	@Override
	public boolean isSearchable() {
		return false;
	}

	@Override
	protected boolean isSearchOnPrepare() {
		return true;
	}

	@Override
	protected boolean isValidBean(EventoOrganizador bean) {
		return (this.getBean().getParticipante() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected EventoOrganizador newInstance() {
		return new EventoOrganizador();
	}

	@Override
	protected void saveImpl(EventoOrganizador bean) throws Exception {
		bean.setEvento(this.getFilter().getEvento());
		eventoOrganizadorModel.update(bean);
	}

	@Override
	protected List<EventoOrganizador> searchImpl(OrganizadorFilter filter) {
		return eventoOrganizadorModel.organizadoresEvento(filter.getEvento());
	}

}
