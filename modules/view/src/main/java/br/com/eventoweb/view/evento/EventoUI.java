package br.com.eventoweb.view.evento;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.types.TipoInscricao;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.evento.spec.EventoModel;
import br.com.eventoweb.model.types.spec.TipoInscricaoModel;
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
	private static final String EVENTO_PESQUISAR = "/forms/eventos/eventoPesquisar.xhtml";

	/* Model */
	@Inject
	private EventoModel eventoModel;
	@Inject
	private TipoInscricaoModel tipoInscricaoModel;

	/* Informações de Login */
	@Inject
	private Identity identity;

	/* Tipos do Cadastro */
	private List<TipoParticipante> tiposParticipante;
	private String nomeCriador;

	private Boolean apoiador = false;
	private Boolean comite = false;
	private Boolean criador = false;

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
		Cadastro cadastro = eventoUser.getUsuario().getCadastro();

		for (Participante participante : this.getBean().getParticipantes()) {

			if (participante.getTipoParticipante() == TipoParticipante.CRIADOR) {
				this.setNomeCriador(participante.getCadastro()
						.getRazaoSocial());
			}

			if (participante.getCadastro().equals(cadastro)) {

				if (participante.getTipoParticipante() == TipoParticipante.APOIADOR) {
					this.setApoiador(true);
				} else if (participante.getTipoParticipante() == TipoParticipante.COMITE) {
					this.setComite(true);
				} else if (participante.getTipoParticipante() == TipoParticipante.CRIADOR) {
					this.setCriador(true);
				}
			}
		}

		// this.getBean().getParticipantes().

		return EVENTO_CADASTRAR;
	}

	@Override
	protected String getActionSearch() {
		return EVENTO_PESQUISAR;
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

		if (bean.getDataInicio().compareTo(dataAtual.getTime()) >= 0) {
			return true;
		}

		LOG.info(MessagesConstants.MSG_ERROR_DATA_EVENTO_INVALIDO);
		MessageUtil.addComponentErrorMessage(
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
		Evento evento = new Evento();
		evento.setDataCadastro(Calendar.getInstance().getTime());
		evento.setTipoInscricao(TipoInscricao.SELECAO);

		return evento;
	}

	@Override
	protected void saveImpl(Evento bean) throws Exception {

		EventoUser eventoUser = (EventoUser) identity.getUser();
		Cadastro cadastro = eventoUser.getUsuario().getCadastro();

		eventoModel.update(this.getBean(), cadastro);
	}

	@Override
	protected List<Evento> searchImpl(EventoFilter filter) {
		return eventoModel.eventosPorNome("%" + filter.getNome() + "%");
	}

	/* Permissoes de Visualização */
	
	public Boolean getPermiteOrganizadores() {
		return this.getCriador();
	}

	public Boolean getPermiteLocais() {
		return this.getCriador();
	}

	public Boolean getPermiteExposicoes() {
		return this.getCriador();
	}
	
	public Boolean getPermiteTemas() {
		return this.getCriador();
	}

	public Boolean getPermitePalestras() {
		return this.getCriador();
	}

	public Boolean getPermiteCursos() {
		return this.getCriador();
	}
	
	public Boolean getPermiteMiniCursos() {
		return this.getCriador();
	}

	public Boolean getPermiteWorkshops() {
		return this.getCriador();
	}

	public Boolean getPermiteTipoPatrocinadores() {
		return this.getCriador();
	}

	public Boolean getPermitePatrocinadores() {
		return this.getCriador();
	}

	public Boolean getPermiteApoiadores() {
		return this.getCriador();
	}

	public Boolean getPermiteRealizadores() {
		return this.getCriador();
	}

	public Boolean getPermiteInscricoes() {
		return this.getCriador();
	}

	public Boolean getPermiteFinanceiro() {
		return this.getCriador();
	}
	
	public Boolean getPermiteComites() {
		return this.getCriador();
	}

	public Boolean getPermiteSubmissoes() {
		return this.getCriador() || this.getComite();
	}

	/* ****** */

	public List<TipoInscricao> getTiposInscricao() {
		return tipoInscricaoModel.tiposInscricao();
	}

	public List<TipoParticipante> getTiposParticipante() {
		return tiposParticipante;
	}

	public void setTiposParticipante(List<TipoParticipante> tiposParticipante) {
		this.tiposParticipante = tiposParticipante;
	}

	public String getLinkPagina() {

		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath()
				+ "/eventoPagina.xhtml?evento=" + this.getBean().getId();
	}

	public String linkPagina(Evento e) {

		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath()
				+ "/eventoPagina.xhtml?evento=" + e.getId();
	}

	public String getNomeCriador() {
		return nomeCriador;
	}

	public void setNomeCriador(String nomeCriador) {
		this.nomeCriador = nomeCriador;
	}

	public Boolean getCriador() {
		return criador;
	}

	public void setCriador(Boolean criador) {
		this.criador = criador;
	}

	public Boolean getComite() {
		return comite;
	}

	public void setComite(Boolean comite) {
		this.comite = comite;
	}

	public Boolean getApoiador() {
		return apoiador;
	}

	public void setApoiador(Boolean apoiador) {
		this.apoiador = apoiador;
	}

}
