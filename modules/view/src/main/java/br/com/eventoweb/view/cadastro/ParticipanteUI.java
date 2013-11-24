package br.com.eventoweb.view.cadastro;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.model.cadastro.spec.ParticipanteModel;
import br.com.eventoweb.view.cadastro.filter.ParticipanteFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class ParticipanteUI extends AbstractCRUD<Participante, ParticipanteFilter> {


	//private static final Logger LOG = Logger.getLogger(ParticipanteUI.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -2629498626894763525L;
	
	private static final String PARTICIPANTE_PESQUISAR = "/forms/eventos/participantePesquisar.xhtml";
	private static final String PARTICIPANTE_CADASTRAR = "/forms/eventos/participanteCadastrar.xhtml";

	/* Modelo */
	@Inject
	private ParticipanteModel participanteModel;

	public ParticipanteUI() {
		super(new ParticipanteFilter());
	}	
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Participante bean) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getMsgDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionCreate() {
		return PARTICIPANTE_CADASTRAR;
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
		return PARTICIPANTE_PESQUISAR;
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
	protected boolean isValidBean(Participante bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Participante newInstance() {
		return new Participante();
	}

	@Override
	protected void saveImpl(Participante bean) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<Participante> searchImpl(ParticipanteFilter filter) {
		return participanteModel.buscarParticipante("%" + filter.getNome() + "%");
	}


}
