package br.com.eventoweb.view.evento;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.Atividade;
import br.com.eventoweb.domain.evento.Inscricao;
import br.com.eventoweb.model.evento.spec.AtividadeModel;
import br.com.eventoweb.model.evento.spec.InscricaoModel;
import br.com.eventoweb.view.evento.filter.InscricaoFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class InscricaoUI extends AbstractCRUD<Inscricao, InscricaoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5371442897850511521L;

	//private static final Logger LOG = Logger.getLogger(PalestraUI.class);

	private List<Atividade> atividades;
	private List<Atividade> selectedAtividades;
	
	private static final String INSCRICAO_PESQUISAR = "/forms/eventos/inscricaoPesquisar.xhtml";
	private static final String INSCRICAO_CADASTRAR = "/forms/eventos/inscricaoCadastrar.xhtml";

	@Inject
	private InscricaoModel inscricaoModel;

	@Inject
	private AtividadeModel atividadeModel;

	public InscricaoUI() {
		super(new InscricaoFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Inscricao bean) throws Exception {
		inscricaoModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null) {
			return this.getBean().getDescricao();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		
		/* Buscar as atividades cadastradas */
		this.setAtividades(new ArrayList<Atividade>());
		this.getAtividades().addAll(atividadeModel.palestrasEvento(this.getFilter().getEvento()));
		this.getAtividades().addAll(atividadeModel.cursosEvento(this.getFilter().getEvento()));
		this.getAtividades().addAll(atividadeModel.minicursosEvento(this.getFilter().getEvento()));
		this.getAtividades().addAll(atividadeModel.workshopsEvento(this.getFilter().getEvento()));
		
		return INSCRICAO_CADASTRAR;
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
		return INSCRICAO_PESQUISAR;
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
	protected boolean isValidBean(Inscricao bean) {
		return true;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Inscricao newInstance() {
		Inscricao eventoInscricao = new Inscricao();
		eventoInscricao.setEvento(this.getFilter().getEvento());
		
		return eventoInscricao;
	}

	@Override
	protected void saveImpl(Inscricao bean) throws Exception {
		bean.setAtividades(this.getSelectedAtividades());
		inscricaoModel.update(bean);
	}

	@Override
	protected List<Inscricao> searchImpl(InscricaoFilter filter) {
		return  inscricaoModel.inscricoesEvento(filter.getEvento());
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<Atividade> getSelectedAtividades() {
		return selectedAtividades;
	}

	public void setSelectedAtividades(
			List<Atividade> selectedAtividades) {
		this.selectedAtividades = selectedAtividades;
	}
	
}
