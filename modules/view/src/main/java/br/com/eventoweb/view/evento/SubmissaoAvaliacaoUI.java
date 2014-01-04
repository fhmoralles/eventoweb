package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.evento.SubmissaoAvaliacao;
import br.com.eventoweb.domain.types.StatusSubmissao;
import br.com.eventoweb.model.evento.spec.ComiteMembroModel;
import br.com.eventoweb.model.evento.spec.ComiteModel;
import br.com.eventoweb.model.evento.spec.SubmissaoAvaliacaoModel;
import br.com.eventoweb.model.types.spec.StatusSubmissaoModel;
import br.com.eventoweb.view.evento.filter.SubmissaoAvaliacaoFilter;
import br.com.eventoweb.view.main.security.EventoUser;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class SubmissaoAvaliacaoUI extends
		AbstractCRUD<SubmissaoAvaliacao, SubmissaoAvaliacaoFilter> {

	// private static final Logger LOG = Logger.getLogger(SubmissaoUI.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4598255299143148710L;

	private static final String SUBMISSAO_PESQUISAR = "/forms/eventos/submissaoPesquisar.xhtml";

	private static final String SUBMISSAO_AVALIACAO_PESQUISAR = "/forms/eventos/submissaoAvaliacaoPesquisar.xhtml";
	private static final String SUBMISSAO_AVALIACAO_CADASTRAR = "/forms/eventos/submissaoAvaliacaoCadastrar.xhtml";

	@Inject
	private SubmissaoAvaliacaoModel submissaoAvaliacaoModel;
	@Inject
	private ComiteModel comiteModel;
	@Inject
	private ComiteMembroModel comiteMembroModel;
	@Inject
	private StatusSubmissaoModel statusSubmissaoModel;

	/* Informações de Login */
	@Inject
	private Identity identity;

	public SubmissaoAvaliacaoUI() {
		super(new SubmissaoAvaliacaoFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteImpl(SubmissaoAvaliacao bean) throws Exception {
		submissaoAvaliacaoModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if (this.getBean() != null
				&& this.getBean().getComiteMembro().getParticipante()
						.getCadastro() != null) {
			return this.getBean().getComiteMembro().getParticipante()
					.getCadastro().getRazaoSocial();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return SUBMISSAO_AVALIACAO_CADASTRAR;
	}

	@Override
	protected String getActionDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionEdit() {
		return SUBMISSAO_AVALIACAO_CADASTRAR;
	}

	@Override
	protected String getActionSearch() {

		EventoUser eventoUser = (EventoUser) identity.getUser();
		Cadastro cadastro = eventoUser.getUsuario().getCadastro();

		
		Boolean userCadastroEPresidente; 
		if(this.getBean() == null) {
			userCadastroEPresidente = true;
		} else {
			userCadastroEPresidente = comiteMembroModel.membroEPresidente(
					this.getBean().getSubmissao().getTema(), cadastro);
		}
			

		if (userCadastroEPresidente) {
			return SUBMISSAO_AVALIACAO_PESQUISAR;
		} else {
			return SUBMISSAO_PESQUISAR;
		}
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
	protected boolean isValidBean(SubmissaoAvaliacao bean) {
		return (this.getBean().getComiteMembro() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected SubmissaoAvaliacao newInstance() {
		SubmissaoAvaliacao submissaoAvaliacao = new SubmissaoAvaliacao(this
				.getFilter().getSubmissao());
		return submissaoAvaliacao;
	}

	@Override
	protected void saveImpl(SubmissaoAvaliacao bean) throws Exception {
		submissaoAvaliacaoModel.update(bean);
	}

	@Override
	protected List<SubmissaoAvaliacao> searchImpl(
			SubmissaoAvaliacaoFilter filter) {
		return submissaoAvaliacaoModel.avaliacoesSubmissao(filter
				.getSubmissao());
	}

	public List<ComiteMembro> getComiteMembros() {

		List<Comite> comites = comiteModel.comitesTema(this.getBean()
				.getSubmissao().getTema());
		Comite comite = comites.get(0);

		return comiteMembroModel.membrosComite(comite);

	}

	public List<StatusSubmissao> getStatusSubmissao() {
		return statusSubmissaoModel.statusSubmissao();
	}

}
