package br.com.eventoweb.view.evento;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.Local;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.evento.spec.LocalModel;
import br.com.eventoweb.view.evento.filter.LocalFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class LocalUI extends AbstractCRUD<Local, LocalFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6991662102398733893L;

	//private static final Logger LOG = Logger.getLogger(LocalUI.class);
	
	private static final String LOCAL_PESQUISAR = "/forms/eventos/localPesquisar.xhtml";
	private static final String LOCAL_CADASTRAR = "/forms/eventos/localCadastrar.xhtml";

	@Inject
	private LocalModel localModel;

	public LocalUI() {
		super(new LocalFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Local bean) throws Exception {
		localModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null && this.getBean().getParticipante().getCadastro() != null) {
			return this.getBean().getParticipante().getCadastro().getRazaoSocial();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return LOCAL_CADASTRAR;
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
		return LOCAL_PESQUISAR;
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
	protected boolean isValidBean(Local bean) {
		return (this.getBean().getParticipante().getCadastro() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Local newInstance() {
		Local local = new Local();
		local.setValorLocacao(BigDecimal.ZERO);
		local.setParticipante(new Participante());
		local.getParticipante().setEvento(this.getFilter().getEvento());
		local.getParticipante().setTipoParticipante(TipoParticipante.LOCAL);
		
		return local;
	}

	@Override
	protected void saveImpl(Local bean) throws Exception {
		localModel.update(bean);
	}

	@Override
	protected List<Local> searchImpl(LocalFilter filter) {
		return localModel.locaisEvento(filter.getEvento());
	}

}
