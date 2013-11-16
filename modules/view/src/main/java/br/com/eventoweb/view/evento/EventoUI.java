package br.com.eventoweb.view.evento;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Local;
import br.com.eventoweb.domain.evento.Organizador;
import br.com.eventoweb.model.cadastro.spec.ParticipanteModel;
import br.com.eventoweb.model.evento.spec.EventoModel;
import br.com.eventoweb.model.evento.spec.OrganizadorModel;
import br.com.eventoweb.view.evento.filter.EventoFilter;
import br.com.eventoweb.view.main.constants.MessagesConstants;
import br.com.eventoweb.view.main.security.EventoUser;
import br.com.webutils.MessageUtil;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class EventoUI extends AbstractCRUD<Evento, EventoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5533016745298915585L;

	private static final Logger LOG = Logger.getLogger(EventoUI.class);
	
	private static final String EVENTO_CADASTRAR = "/forms/eventos/eventoCadastrar.xhtml";
	private static final String EVENTO_EDITAR = "/forms/eventos/eventoEditar.xhtml";
	//private static final String EVENTO_MEUSEVENTOS = "/main.xhtml";
	//private static final String PESQUISA_NATUREZA_INCLUSAO = "/forms/cadastros/naturezaInclusaoPesquisar.xhtml";
	
	/* Model */
	@Inject
	private EventoModel eventoModel;
	@Inject
	private OrganizadorModel organizadorModel;
	@Inject
	private ParticipanteModel participanteModel;
	
	/* Informações de Login */
	@Inject
	private Identity identity;
	
	/* Injeta o UI dos meus eventos */
	@Inject
	private MeusEventosUI meusEventosUI;
	
	/* Dominios */
	private Participante selectedParticipante;
	private Organizador organizdorSelected;
	
	private List<Organizador> organizadores;
	private List<Local> locais;
	
	public EventoUI() {
		super(new EventoFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Evento bean) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getMsgDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionCreate() {
		return EVENTO_CADASTRAR;
	}

	@Override
	protected String getActionDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionEdit() {
		
		EventoUser eventoUser = (EventoUser) identity.getUser();
		Participante participante = eventoUser.getUsuario().getParticipante();
		
		/* Se for o criado/oganizador do evento -> página de Edição */
		if(this.getBean().getParticipante().equals(participante)) {
			this.setOrganizadores(organizadorModel.organizadoresEvento(getBean()));
			return EVENTO_EDITAR;
		}

		return null;
	}

	@Override
	protected String getActionSearch() {
		return meusEventosUI.prepareSearch();
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
	protected boolean isValidBean(Evento bean) {
		
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.setTimeInMillis(System.currentTimeMillis());
		
		LOG.info(bean.getDataEvento());
		
		if(bean.getDataEvento().compareTo(dataAtual.getTime()) >= 0) {
			return true;
		}
		
		LOG.info(MessagesConstants.MSG_ERROR_DATA_EVENTO_INVALIDO);
		MessageUtil
				.addComponentErrorMessage(
						"contentEventoForm_inputDataEventoText",
						MessagesConstants.MSG_ERROR_DATA_EVENTO_INVALIDO);

		return false;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Evento newInstance() {
		return new Evento();
	}

	@Override
	protected void saveImpl(Evento bean) throws Exception {
		
		this.getBean().setParticipante(((EventoUser)identity.getUser()).getUsuario().getParticipante());
		eventoModel.create(this.getBean());
	}

	protected void saveOrganizador() {
		
		/* Pegar o valor selecionado */
		if(this.getSelectedParticipante() != null) {
			
			/* Adicionar na listagem de Organizadores */
			Organizador organizador = new Organizador();
			organizador.setEvento(this.getBean());
			organizador.setParticipante(this.getSelectedParticipante());
			
			/* Persiste */
			try {
				organizadorModel.create(organizador);
				this.getOrganizadores().add(organizador);
				this.setSelectedParticipante(null);
			} catch (Exception e) {
	 			LOG.error(MessagesConstants.MSG_ERROR_ORGANIZADOR_NAO_CADASTRADO, e);
	 			MessageUtil
					.addComponentErrorMessage(
							"contentEventoForm_tabView_autoComplete_input",
							MessagesConstants.MSG_ERROR_ORGANIZADOR_NAO_CADASTRADO);
			}
			
 		} else {
 			LOG.error(MessagesConstants.MSG_ERROR_PARTICIPANTE_SELECIONADO_INVALIDO);
 			MessageUtil
 					.addComponentErrorMessage(
 							"contentEventoForm_tabView_autoComplete",
 							MessagesConstants.MSG_ERROR_PARTICIPANTE_SELECIONADO_INVALIDO); 			
 		}
	}
	
	protected void deleteOrganizador() {
	
		if(this.getOrganizdorSelected() != null) {
			
			try {
				
				organizadorModel.delete(this.getOrganizdorSelected());				
				this.getOrganizadores().remove(this.getOrganizdorSelected());

	 			LOG.info(MessagesConstants.MSG_INFO_ORGANIZADOR_SELECIONADO_EXCLUIDO_SUCESSO);
	 			MessageUtil
	 					.addComponentInfoMessage(
	 							"contentEventoForm_tabView_autoComplete",
	 							MessagesConstants.MSG_INFO_ORGANIZADOR_SELECIONADO_EXCLUIDO_SUCESSO); 			
				
			} catch(Exception e) {
	 			LOG.error(MessagesConstants.MSG_ERROR_ORGANIZADOR_SELECIONADO_NAO_EXCLUIDO);
	 			MessageUtil
	 					.addComponentErrorMessage(
	 							"contentEventoForm_tabView_autoComplete",
	 							MessagesConstants.MSG_ERROR_ORGANIZADOR_SELECIONADO_NAO_EXCLUIDO); 			
				
			}
			
		} else {
 			LOG.error(MessagesConstants.MSG_ERROR_ORGANIZADOR_SELECIONADO_INVALIDO);
 			MessageUtil
 					.addComponentErrorMessage(
 							"contentEventoForm_tabView_autoComplete",
 							MessagesConstants.MSG_ERROR_ORGANIZADOR_SELECIONADO_INVALIDO); 			
		}
	}
	
	@Override
	protected List<Evento> searchImpl(EventoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onTabChange(TabChangeEvent event) {
		
		Tab activeTab = event.getTab();
		LOG.info(activeTab.getId());
		/*
		if(activeTab.getId().equals("p1") && this.getOrganizadores() != null) {
			
			this.setOrganizadores(eventoModel.eventoOrganizadores(this.getBean()));
			this.getBean().setOrganizadores(this.getOrganizadores());
			
		} else if(activeTab.getId().equals("p2")) {
			
		} else if(activeTab.getId().equals("p3")) {
			
		} else if(activeTab.getId().equals("p4")) {
			
		} else if(activeTab.getId().equals("p5")) {
			
		} else if(activeTab.getId().equals("p6")) {
			
		} else if(activeTab.getId().equals("p7")) {

		} else if(activeTab.getId().equals("p8")) {

		} else if(activeTab.getId().equals("p9")) {
		
		}*/
		
	}

	public List<Participante> autoComplete(String query) {
		List<Participante> participantes = participanteModel.buscarParticipante("%" + query + "%");
		
		/* Retirar os que já foram adicionados */
		for(Organizador organizador: this.getOrganizadores()) {
			participantes.remove(organizador.getParticipante());
		}
		
		return participantes;
	}
	
	public List<Organizador> getOrganizadores() {
		return organizadores;
	}

	public void setOrganizadores(List<Organizador> organizadores) {
		this.organizadores = organizadores;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	public Participante getSelectedParticipante() {
		return selectedParticipante;
	}

	public void setSelectedParticipante(Participante selectedParticipante) {
		this.selectedParticipante = selectedParticipante;
	}

	public Organizador getOrganizdorSelected() {
		return organizdorSelected;
	}

	public void setOrganizdorSelected(Organizador organizdorSelected) {
		this.organizdorSelected = organizdorSelected;
	}
	
}
