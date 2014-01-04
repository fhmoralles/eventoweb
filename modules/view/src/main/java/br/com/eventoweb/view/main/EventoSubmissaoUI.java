package br.com.eventoweb.view.main;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.security.Identity;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.domain.exceptions.ComiteSemMembrosException;
import br.com.eventoweb.domain.exceptions.ComiteSemPresidenteException;
import br.com.eventoweb.domain.exceptions.TemaSemComiteException;
import br.com.eventoweb.domain.types.ArquivoSubmissao;
import br.com.eventoweb.domain.types.StatusSubmissao;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.evento.spec.EventoModel;
import br.com.eventoweb.model.evento.spec.SubmissaoModel;
import br.com.eventoweb.model.evento.spec.TemaModel;
import br.com.eventoweb.view.evento.filter.EventoOpcoesFilter;
import br.com.eventoweb.view.main.security.EventoUser;
import br.com.eventoweb.view.type.Anexo;
import br.com.webutils.MessageUtil;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
public class EventoSubmissaoUI extends
		AbstractCRUD<Submissao, EventoOpcoesFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7885237934534818379L;

	private static final String EVENTO_SUBMISSAO = "/eventoSubmissao.xhtml";
	
	@Inject
	private EventoModel eventoModel;
	@Inject
	private TemaModel temaModel;
	@Inject
	private Identity identity;
	@Inject
	private SubmissaoModel submissaoModel;

	private Evento evento;
	private Tema tema;
	private List<Anexo> arquivos;
	private Anexo arquivo;

	public EventoSubmissaoUI() {
		super(new EventoOpcoesFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		this.setTema(null);
		this.setArquivo(null);
		this.arquivos.clear();
	}

	@Override
	protected void deleteImpl(Submissao bean) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getMsgDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionCreate() {
		// TODO Auto-generated method stub
		return null;
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
		return EVENTO_SUBMISSAO + "?evento=" + this.getEvento().getId();
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
	protected boolean isValidBean(Submissao bean) {
		/* TODO: Fazer a validação */
		return true;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Submissao newInstance() {
		return new Submissao();
	}

	@Override
	protected void saveImpl(Submissao bean) throws Exception {
		
		if (identity.isLoggedIn()) {
			
			if(bean == null) {
				bean = newInstance();
			}
			bean.setTema(this.getTema());
			bean.setStatus(StatusSubmissao.AGUARDANDO);

			Participante p = new Participante();
			p.setCadastro(((EventoUser) identity.getUser()).getUsuario()
					.getCadastro());
			p.setEvento(this.getEvento());
			p.setTipoParticipante(TipoParticipante.ORADOR);
			bean.setParticipante(p);

			List<ArquivoSubmissao> arquivosAux = new ArrayList<ArquivoSubmissao>();
			for (Anexo anexo : arquivos) {
				ArquivoSubmissao arquivoSubmissao = new ArquivoSubmissao(anexo
						.getFile().getFileName(), anexo.getFile().getContents());
				arquivosAux.add(arquivoSubmissao);
			}
			
			try {
				submissaoModel.update(bean, arquivosAux);

				/* Reseta the bean */
				this.reset();
				
			} catch (TemaSemComiteException e) {
				MessageUtil.addGlobalErrorMessage(e.getMessage());
				throw e;
			} catch (ComiteSemMembrosException e) {
				MessageUtil.addGlobalErrorMessage(e.getMessage());
				throw e;
			} catch (ComiteSemPresidenteException e) {
				MessageUtil.addGlobalErrorMessage(e.getMessage());
				throw e;
			}
			
			
		} else {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("loginSubmissaoDialog.show()");
		}

	}

	@Override
	protected List<Submissao> searchImpl(EventoOpcoesFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public void buscarEvento() {

		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		String strIdEvento = httpServletRequest.getParameter("evento");

		if (strIdEvento != null && eventoModel != null) {
			try {
				this.evento = eventoModel.retrieve(Long.parseLong(strIdEvento));
				this.prepareCreate();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getNome() {
		return evento.getNome();
	}

	public Evento getEvento() {
		return evento;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<Anexo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Anexo> arquivos) {
		this.arquivos = arquivos;
	}

	public Anexo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Anexo arquivo) {
		this.arquivo = arquivo;
	}

	public List<Tema> getTemas() {
		return temaModel.temas(this.getEvento());
	}

	public void uploadArquivo(FileUploadEvent event) {
		if (arquivos == null) {
			arquivos = new ArrayList<Anexo>();
		}
		arquivos.add(new Anexo(event.getFile()));
		System.out.println("Total de Arquivos: " + arquivos.size());
	}

	public void removerArquivo() {
		if (arquivos != null) {
			arquivos.remove(arquivo);
		}
		arquivo = null;
	}

}
