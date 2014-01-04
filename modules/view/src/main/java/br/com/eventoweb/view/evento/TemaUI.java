package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.model.evento.spec.TemaModel;
import br.com.eventoweb.view.evento.filter.TemaFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class TemaUI extends AbstractCRUD<Tema, TemaFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6991662102398733893L;

	//private static final Logger LOG = Logger.getLogger(TemaUI.class);
	
	private static final String TEMA_PESQUISAR = "/forms/eventos/temaPesquisar.xhtml";
	private static final String TEMA_CADASTRAR = "/forms/eventos/temaCadastrar.xhtml";

	@Inject
	private TemaModel temaModel;

	public TemaUI() {
		super(new TemaFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Tema bean) throws Exception {
		temaModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null && this.getBean().getDescricao() != null) {
			return this.getBean().getDescricao();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return TEMA_CADASTRAR;
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
		return TEMA_PESQUISAR;
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
	protected boolean isValidBean(Tema bean) {
		return (this.getBean().getDescricao() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Tema newInstance() {
		Tema tema = new Tema(this.getFilter().getEvento());
		
		return tema;
	}

	@Override
	protected void saveImpl(Tema bean) throws Exception {
		temaModel.update(bean);
	}

	@Override
	protected List<Tema> searchImpl(TemaFilter filter) {
		return temaModel.temas(filter.getEvento());
	}

}
