package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.Apoio;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.model.evento.spec.ApoioModel;
import br.com.eventoweb.model.evento.spec.PatrocinioTipoModel;
import br.com.eventoweb.view.evento.filter.ApoiadorFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class ApoioUI extends AbstractCRUD<Apoio, ApoiadorFilter> {

	// private static final Logger LOG = Logger.getLogger(ApoiadorUI.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4598255299143148710L;

	private static final String APOIADOR_PESQUISAR = "/forms/eventos/apoiadorPesquisar.xhtml";
	private static final String APOIADOR_CADASTRAR = "/forms/eventos/apoiadorCadastrar.xhtml";

	@Inject
	private ApoioModel apoioModel;
	@Inject
	private PatrocinioTipoModel patrocinioTipoModel;

	public ApoioUI() {
		super(new ApoiadorFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteImpl(Apoio bean) throws Exception {
		apoioModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null && this.getBean().getParticipante() != null) {
			return this.getBean().getParticipante().getCadastro().getRazaoSocial();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		this.getBean().getParticipante().setCadastro(null);
		return APOIADOR_CADASTRAR;
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
		return APOIADOR_PESQUISAR;
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
	protected boolean isValidBean(Apoio bean) {
		return (this.getBean().getParticipante().getCadastro() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Apoio newInstance() {
		Apoio apoio = new Apoio(this.getFilter().getEvento());

		return apoio;
	}

	@Override
	protected void saveImpl(Apoio bean) throws Exception {
		apoioModel.update(bean);
	}

	@Override
	protected List<Apoio> searchImpl(ApoiadorFilter filter) {
		return apoioModel.apoiadoresEvento(filter.getEvento());
	}

	protected List<PatrocinioTipo> getPatrocinioTipos() {
		return patrocinioTipoModel.patrocinioTipos(this.getFilter().getEvento());
	}
	
}
