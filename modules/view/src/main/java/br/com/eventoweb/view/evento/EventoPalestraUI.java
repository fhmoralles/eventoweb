package br.com.eventoweb.view.evento;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.cadastro.LugarEspaco;
import br.com.eventoweb.domain.evento.EventoLocal;
import br.com.eventoweb.domain.evento.EventoPalestra;
import br.com.eventoweb.model.cadastro.spec.LugarEspacoModel;
import br.com.eventoweb.model.cadastro.spec.ParticipanteModel;
import br.com.eventoweb.model.evento.spec.EventoLocalModel;
import br.com.eventoweb.model.evento.spec.EventoPalestraModel;
import br.com.eventoweb.view.evento.filter.PalestraFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class EventoPalestraUI extends AbstractCRUD<EventoPalestra, PalestraFilter> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5371442897850511521L;

	//private static final Logger LOG = Logger.getLogger(PalestraUI.class);
	
	private static final String EVENTO_PALESTRA_PESQUISAR = "/forms/eventos/eventoPalestraPesquisar.xhtml";
	private static final String EVENTO_PALESTRA_CADASTRAR = "/forms/eventos/eventoPalestraCadastrar.xhtml";

	@Inject
	private EventoPalestraModel eventoPalestraModel;
	@Inject
	private LugarEspacoModel lugarEspacoModel;
	@Inject
	private EventoLocalModel eventoLocalModel;

	public EventoPalestraUI() {
		super(new PalestraFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(EventoPalestra bean) throws Exception {
		eventoPalestraModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null) {
			return this.getBean().getTitulo();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return EVENTO_PALESTRA_CADASTRAR;
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
		return EVENTO_PALESTRA_PESQUISAR;
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
	protected boolean isValidBean(EventoPalestra bean) {
		return true;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected EventoPalestra newInstance() {
		EventoPalestra palestra = new EventoPalestra();
		palestra.setEvento(this.getFilter().getEvento());
		
		return palestra;
	}

	@Override
	protected void saveImpl(EventoPalestra bean) throws Exception {
		eventoPalestraModel.update(bean);
	}

	@Override
	protected List<EventoPalestra> searchImpl(PalestraFilter filter) {
		return eventoPalestraModel.palestrasEvento(filter.getEvento());
	}

	public List<LugarEspaco> getLugaresEspaco() {

		List<EventoLocal> locais = eventoLocalModel.locaisEvento(this.getFilter().getEvento());
		List<LugarEspaco> espacos = new ArrayList<LugarEspaco>();

		for (EventoLocal local : locais) {
			espacos.addAll(lugarEspacoModel.espacosLugar(local.getLugar()));
		}

		return espacos;
	}
	
}
