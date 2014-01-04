package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.model.evento.spec.ComiteMembroModel;
import br.com.eventoweb.model.types.spec.TipoComiteMembroModel;
import br.com.eventoweb.view.evento.filter.ComiteMembroFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class ComiteMembroUI extends
		AbstractCRUD<ComiteMembro, ComiteMembroFilter> {

	// private static final Logger LOG = Logger.getLogger(ComiteUI.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4598255299143148710L;

	private static final String COMITE_MEMBRO_PESQUISAR = "/forms/eventos/comiteMembroPesquisar.xhtml";
	private static final String COMITE_MEMBRO_CADASTRAR = "/forms/eventos/comiteMembroCadastrar.xhtml";

	@Inject
	private ComiteMembroModel comiteMembroModel;
	@Inject
	private TipoComiteMembroModel tipoComiteMembroModel;

	public ComiteMembroUI() {
		super(new ComiteMembroFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteImpl(ComiteMembro bean) throws Exception {
		comiteMembroModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if (this.getBean() != null && this.getBean().getParticipante() != null
				&& this.getBean().getParticipante().getCadastro() != null) {
			return this.getBean().getParticipante().getCadastro()
					.getRazaoSocial();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		this.getBean().getParticipante().setCadastro(null);
		return COMITE_MEMBRO_CADASTRAR;
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
		return COMITE_MEMBRO_PESQUISAR;
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
	protected boolean isValidBean(ComiteMembro bean) {
		return (this.getBean().getParticipante().getCadastro() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected ComiteMembro newInstance() {
		ComiteMembro comiteMembro = new ComiteMembro(this.getFilter()
				.getComite());
		return comiteMembro;
	}

	@Override
	protected void saveImpl(ComiteMembro bean) throws Exception {
		comiteMembroModel.update(bean);
	}

	@Override
	protected List<ComiteMembro> searchImpl(ComiteMembroFilter filter) {
		return comiteMembroModel.membrosComite(filter.getComite());
	}
	
	public List<TipoComiteMembro> getTiposComiteMembro() {
		return tipoComiteMembroModel.tiposComiteMembro();
	}
}
