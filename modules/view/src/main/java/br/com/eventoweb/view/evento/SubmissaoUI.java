package br.com.eventoweb.view.evento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.SubmissaoArquivo;
import br.com.eventoweb.domain.evento.SubmissaoAvaliacao;
import br.com.eventoweb.domain.types.StatusSubmissao;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.evento.spec.ComiteModel;
import br.com.eventoweb.model.evento.spec.SubmissaoArquivoModel;
import br.com.eventoweb.model.evento.spec.SubmissaoAvaliacaoModel;
import br.com.eventoweb.model.evento.spec.SubmissaoModel;
import br.com.eventoweb.model.types.spec.StatusSubmissaoModel;
import br.com.eventoweb.view.evento.filter.EventoOpcoesFilter;
import br.com.eventoweb.view.main.security.EventoUser;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class SubmissaoUI extends AbstractCRUD<Submissao, EventoOpcoesFilter> {

	// private static final Logger LOG = Logger.getLogger(SubmissaoUI.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4598255299143148710L;

	private static final String SUBMISSAO_PESQUISAR = "/forms/eventos/submissaoPesquisar.xhtml";
	private static final String SUBMISSAO_CADASTRAR = "/forms/eventos/submissaoCadastrar.xhtml";

	@Inject
	private SubmissaoModel submissaoModel;
	@Inject
	private SubmissaoArquivoModel submissaoArquivoModel;
	@Inject
	private SubmissaoAvaliacaoModel submissaoAvaliacaoModel;
	@Inject
	private ComiteModel comiteModel;
	@Inject
	private StatusSubmissaoModel statusSubmissaoModel;
	
	/* Informações de Login */
	@Inject
	private Identity identity;
	
	private SubmissaoArquivo submissaoArquivoDownload;

	public SubmissaoUI() {
		super(new EventoOpcoesFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteImpl(Submissao bean) throws Exception {
		submissaoModel.delete(this.getBean());
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
		return SUBMISSAO_CADASTRAR;
	}

	@Override
	protected String getActionDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionEdit() {
		return SUBMISSAO_CADASTRAR;
	}

	@Override
	protected String getActionSearch() {
		return SUBMISSAO_PESQUISAR;
	}

	@Override
	public boolean isDeletable() {
		return false;
	}

	@Override
	public boolean isEditable() {
		return false;
	}

	@Override
	public boolean isInsertable() {
		return false;
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
	protected boolean isValidBean(Submissao bean) {
		return (this.getBean().getParticipante().getCadastro() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Submissao newInstance() {
		Submissao submissao = new Submissao();
		submissao.setParticipante(new Participante());
		submissao.getParticipante().setEvento(this.getFilter().getEvento());
		submissao.getParticipante().setTipoParticipante(TipoParticipante.COMITE);

		return submissao;
	}

	@Override
	protected void saveImpl(Submissao bean) throws Exception {
		submissaoModel.update(bean);
	}

	@Override
	protected List<Submissao> searchImpl(EventoOpcoesFilter filter) {
		
		EventoUser eventoUser = (EventoUser) identity.getUser();
		Cadastro cadastro = eventoUser.getUsuario().getCadastro();
		
		return submissaoModel.submissoesEvento(filter.getEvento(), cadastro);
	}

	public Boolean isPresidente(Submissao submissao) {
		
		System.out.println("submissao: " + submissao);
		System.out.println("submissao.getTema(): " + submissao.getTema());
		
		List<Comite> comites = comiteModel.comitesTema(submissao.getTema());
		
		if(comites != null && comites.size() > 0) {
			
			Comite comite = comites.get(0);
			EventoUser eventoUser = (EventoUser) identity.getUser();
			Cadastro cadastro = eventoUser.getUsuario().getCadastro();
			
			for(ComiteMembro comiteMembro: comite.getMembros()) {
				
				if(comiteMembro.getTipoComiteMembro() == TipoComiteMembro.PRESIDENTE && comiteMembro.getParticipante().getCadastro().equals(cadastro)) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public List<SubmissaoArquivo> arquivosSubmissao(Submissao submissao) {
		return submissaoArquivoModel.arquivosSubmissao(submissao);
	}
	
	public List<SubmissaoAvaliacao> avaliacoesSubmissao(Submissao submissao) {
		return submissaoAvaliacaoModel.avaliacoesSubmissao(submissao);
	}
	
	public StreamedContent getArquivoDownload() {
		
		File file = new File(this.getSubmissaoArquivoDownload().getArquivo());
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return new DefaultStreamedContent(fileInputStream, "*.*", this.getSubmissaoArquivoDownload().getArquivoRelativo());
	}

	public SubmissaoArquivo getSubmissaoArquivoDownload() {
		return submissaoArquivoDownload;
	}

	public void setSubmissaoArquivoDownload(
			SubmissaoArquivo submissaoArquivoDownload) {
		this.submissaoArquivoDownload = submissaoArquivoDownload;
	}

	public List<StatusSubmissao> getStatusSubmissao() {
		return statusSubmissaoModel.statusSubmissao();
	}
	
}
