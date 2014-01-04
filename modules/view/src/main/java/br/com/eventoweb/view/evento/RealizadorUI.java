package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.evento.spec.ParticipanteModel;
import br.com.eventoweb.view.evento.filter.RealizadorFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class RealizadorUI extends AbstractCRUD<Participante, RealizadorFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6991662102398733893L;

	//private static final Logger LOG = Logger.getLogger(RealizadorUI.class);
	
	private static final String REALIZADOR_PESQUISAR = "/forms/eventos/realizadorPesquisar.xhtml";
	private static final String REALIZADOR_CADASTRAR = "/forms/eventos/realizadorCadastrar.xhtml";

	@Inject
	private ParticipanteModel participanteModel;

	public RealizadorUI() {
		super(new RealizadorFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Participante bean) throws Exception {
		participanteModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null && this.getBean().getCadastro() != null) {
			return this.getBean().getCadastro().getRazaoSocial();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		this.getBean().setCadastro(null);
		return REALIZADOR_CADASTRAR;
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
		return REALIZADOR_PESQUISAR;
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
	protected boolean isValidBean(Participante bean) {
		return (this.getBean().getCadastro() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Participante newInstance() {
		Participante participante = new Participante();
		participante.setTipoParticipante(TipoParticipante.REALIZADOR);
		
		return participante;
	}

	@Override
	protected void saveImpl(Participante bean) throws Exception {
		bean.setEvento(this.getFilter().getEvento());
		participanteModel.update(bean);
	}

	@Override
	protected List<Participante> searchImpl(RealizadorFilter filter) {
		return participanteModel.realizadoresEvento(filter.getEvento());
	}

}
