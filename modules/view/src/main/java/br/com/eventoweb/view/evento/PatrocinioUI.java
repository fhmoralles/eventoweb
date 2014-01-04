package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.evento.Patrocinio;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.evento.spec.PatrocinioModel;
import br.com.eventoweb.model.evento.spec.PatrocinioTipoModel;
import br.com.eventoweb.view.evento.filter.PatrocinioFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class PatrocinioUI extends AbstractCRUD<Patrocinio, PatrocinioFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5371442897850511521L;

	//private static final Logger LOG = Logger.getLogger(PalestraUI.class);
	
	private static final String PATROCINIO_PESQUISAR = "/forms/eventos/patrocinioPesquisar.xhtml";
	private static final String PATROCINIO_CADASTRAR = "/forms/eventos/patrocinioCadastrar.xhtml";

	@Inject
	private PatrocinioModel patrocinioModel;
	@Inject
	private PatrocinioTipoModel patrocinioTipoModel;

	public PatrocinioUI() {
		super(new PatrocinioFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Patrocinio bean) throws Exception {
		patrocinioModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null) {
			return this.getBean().getDescricao();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return PATROCINIO_CADASTRAR;
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
		return PATROCINIO_PESQUISAR;
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
	protected boolean isValidBean(Patrocinio bean) {
		return true;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Patrocinio newInstance() {
		Patrocinio patrocinio = new Patrocinio();
		patrocinio.setParticipante(new Participante());
		patrocinio.getParticipante().setEvento(this.getFilter().getEvento());
		patrocinio.getParticipante().setTipoParticipante(TipoParticipante.PATROCINADOR);
		
		return patrocinio;
	}

	@Override
	protected void saveImpl(Patrocinio bean) throws Exception {
		patrocinioModel.update(bean);
	}

	@Override
	protected List<Patrocinio> searchImpl(PatrocinioFilter filter) {
		return patrocinioModel.patrocinadoresEvento(filter.getEvento());
	}

	protected List<PatrocinioTipo> getPatrocinioTipos() {
		return patrocinioTipoModel.patrocinioTipos(this.getFilter().getEvento());
	}
	
	public void atualizarValorPatrocinio(AjaxBehaviorEvent e) {
		this.getBean().setValorPatrocinio(this.getBean().getPatrocinioTipo().getValorPadrao());
	}
}
