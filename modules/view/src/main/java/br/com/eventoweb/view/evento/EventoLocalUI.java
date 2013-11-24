package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.EventoLocal;
import br.com.eventoweb.model.evento.spec.EventoLocalModel;
import br.com.eventoweb.view.evento.filter.EventoLocalFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class EventoLocalUI extends AbstractCRUD<EventoLocal, EventoLocalFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6991662102398733893L;

	//private static final Logger LOG = Logger.getLogger(LocalUI.class);
	
	private static final String EVENTO_LOCAL_PESQUISAR = "/forms/eventos/eventoLocalPesquisar.xhtml";
	private static final String EVENTO_LOCAL_CADASTRAR = "/forms/eventos/eventoLocalCadastrar.xhtml";

	@Inject
	private EventoLocalModel eventoLocalModel;

	public EventoLocalUI() {
		super(new EventoLocalFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(EventoLocal bean) throws Exception {
		eventoLocalModel.delete(bean);
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null && this.getBean().getLugar() != null) {
			return this.getBean().getLugar().getRazaoSocial();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return EVENTO_LOCAL_CADASTRAR;
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
		return EVENTO_LOCAL_PESQUISAR;
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
	protected boolean isValidBean(EventoLocal bean) {
		return true;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected EventoLocal newInstance() {
		return new EventoLocal();
	}

	@Override
	protected void saveImpl(EventoLocal bean) throws Exception {
		bean.setEvento(this.getFilter().getEvento());
		eventoLocalModel.update(bean);
}

	@Override
	protected List<EventoLocal> searchImpl(EventoLocalFilter filter) {
		return eventoLocalModel.locaisEvento(filter.getEvento());
	}
	
}
