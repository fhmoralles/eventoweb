package br.com.eventoweb.view.evento;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;
import org.primefaces.context.RequestContext;

import br.com.eventoweb.domain.evento.Atividade;
import br.com.eventoweb.domain.evento.Exposicao;
import br.com.eventoweb.domain.evento.PatrocinioAtividade;
import br.com.eventoweb.domain.evento.PatrocinioExposicao;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.model.evento.spec.AtividadeModel;
import br.com.eventoweb.model.evento.spec.ExposicaoModel;
import br.com.eventoweb.model.evento.spec.PatrocinioAtividadeModel;
import br.com.eventoweb.model.evento.spec.PatrocinioExposicaoModel;
import br.com.eventoweb.model.evento.spec.PatrocinioTipoModel;
import br.com.eventoweb.view.evento.filter.PatrocinioTipoFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class PatrocinioTipoUI extends AbstractCRUD<PatrocinioTipo, PatrocinioTipoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1952047470663821475L;

	private static final String PATROCINIO_TIPO_PESQUISAR = "/forms/eventos/patrocinioTipoPesquisar.xhtml";
	private static final String PATROCINIO_TIPO_CADASTRAR = "/forms/eventos/patrocinioTipoCadastrar.xhtml";

	@Inject
	private PatrocinioTipoModel patrocinioTipoModel;
	@Inject
	private PatrocinioExposicaoModel patrocinioExposicaoModel;
	@Inject
	private PatrocinioAtividadeModel patrocinioAtividadeModel;
	@Inject
	private ExposicaoModel exposicaoModel;
	@Inject
	private AtividadeModel atividadeModel;

	/* Dominio */
	private List<Exposicao> exposicoes;
	private List<Atividade> atividades;
	private List<PatrocinioExposicao> patrocinioExposicoes;
	private List<PatrocinioAtividade> patrocinioAtividades;
	
	private PatrocinioExposicao selectedPatrocinioExposicao;
	private PatrocinioAtividade selectedPatrocinioAtividade;
	private Exposicao selectedExposicao;
	private Atividade selectedAtividade;
	private Integer qtdeExposicao;
	private Integer qtdeAtividade;
	
	public PatrocinioTipoUI() {
		super(new PatrocinioTipoFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(PatrocinioTipo bean) throws Exception {
		patrocinioTipoModel.delete(this.getBean());
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
		return PATROCINIO_TIPO_CADASTRAR;
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
		return PATROCINIO_TIPO_PESQUISAR;
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
	protected boolean isValidBean(PatrocinioTipo bean) {
		return true;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected PatrocinioTipo newInstance() {
		
		this.setSelectedExposicao(null);
		this.setPatrocinioExposicoes(null);
		this.setSelectedPatrocinioExposicao(null);
		this.setExposicoes(exposicaoModel.exposicoes(this.getFilter().getEvento()));
		this.setQtdeExposicao(1);

		
		this.setSelectedAtividade(null);
		this.setPatrocinioAtividades(null);
		this.setSelectedPatrocinioAtividade(null);
		this.setAtividades(new ArrayList<Atividade>());
		this.getAtividades().addAll(atividadeModel.cursosEvento(this.getFilter().getEvento()));
		this.getAtividades().addAll(atividadeModel.palestrasEvento(this.getFilter().getEvento()));
		this.getAtividades().addAll(atividadeModel.minicursosEvento(this.getFilter().getEvento()));
		this.getAtividades().addAll(atividadeModel.workshopsEvento(this.getFilter().getEvento()));
		this.setQtdeAtividade(1);
		
		PatrocinioTipo patrocinioTipo = new PatrocinioTipo(this.getFilter().getEvento());
		return patrocinioTipo;
	}

	@Override
	protected void saveImpl(PatrocinioTipo bean) throws Exception {
		
		bean.setExposicoes(this.getPatrocinioExposicoes());
		bean.setAtividades(this.getPatrocinioAtividades());
		
		patrocinioTipoModel.update(bean);
	}

	@Override
	protected List<PatrocinioTipo> searchImpl(PatrocinioTipoFilter filter) {
		return patrocinioTipoModel.patrocinioTipos(filter.getEvento());
	}

	/* Metodos para Salvar/Excluir PatrocinioExposicao */
	
	public void saveExposicao() {
		
		PatrocinioExposicao patrocinioExposicao = new PatrocinioExposicao();
		patrocinioExposicao.setExposicao(this.getSelectedExposicao());
		patrocinioExposicao.setPatrocinioTipo(this.getBean());
		patrocinioExposicao.setQuantidade(this.getQtdeExposicao());
		
		if(this.getPatrocinioExposicoes() == null) {
			this.setPatrocinioExposicoes(new ArrayList<PatrocinioExposicao>());
		}
		
		this.getPatrocinioExposicoes().add(patrocinioExposicao);
		this.getExposicoes().remove(this.getSelectedExposicao());
		this.setSelectedExposicao(null);
		this.setQtdeExposicao(1);
	}
	
	public void deleteExposicao(PatrocinioExposicao patrocinioExposicao) {
		
		this.getExposicoes().add(patrocinioExposicao.getExposicao());
		for(int i=0; i < this.getPatrocinioExposicoes().size()-1; i++) {
			
			if(this.getPatrocinioExposicoes().get(i).getExposicao().getDescricao().equals(patrocinioExposicao.getExposicao().getDescricao())) {
				this.getPatrocinioExposicoes().remove(i);
				break;	
			}
		}
		
		RequestContext.getCurrentInstance().update("detailDataTableExposicoes");
	}
	
	/* ****************** */

	/* Metodos para Salvar/Excluir PatrocinioExposicao */
	
	public void saveAtividade() {
		
		PatrocinioAtividade patrocinioAtividade = new PatrocinioAtividade();
		patrocinioAtividade.setAtividade(this.getSelectedAtividade());
		patrocinioAtividade.setPatrocinioTipo(this.getBean());
		patrocinioAtividade.setQuantidade(this.getQtdeAtividade());
		
		if(this.getPatrocinioAtividades() == null) {
			this.setPatrocinioAtividades(new ArrayList<PatrocinioAtividade>());
		}
		
		this.getPatrocinioAtividades().add(patrocinioAtividade);
		this.getAtividades().remove(this.getSelectedAtividade());
		this.setSelectedAtividade(null);
		this.setQtdeAtividade(1);
	}
	
	public void deleteAtividade(PatrocinioAtividade patrocinioAtividade) {
		
		this.getAtividades().add(patrocinioAtividade.getAtividade());
		for(int i=0; i < this.getPatrocinioAtividades().size()-1; i++) {
			
			if(this.getPatrocinioAtividades().get(i).getAtividade().getDescricao().equals(patrocinioAtividade.getAtividade().getDescricao())) {
				this.getPatrocinioAtividades().remove(i);
				break;	
			}
		}
		
		RequestContext.getCurrentInstance().update("detailDataTableAtividades");
	}
	
	/* ****************** */
	
	public List<Exposicao> getExposicoes() {
		return exposicoes;
	}

	public void setExposicoes(List<Exposicao> exposicoes) {
		this.exposicoes = exposicoes;
	}

	public Exposicao getSelectedExposicao() {
		return selectedExposicao;
	}

	public void setSelectedExposicao(Exposicao selectedExposicao) {
		this.selectedExposicao = selectedExposicao;
	}

	public Integer getQtdeExposicao() {
		return qtdeExposicao;
	}

	public void setQtdeExposicao(Integer qtdeExposicao) {
		this.qtdeExposicao = qtdeExposicao;
	}

	public List<PatrocinioExposicao> getPatrocinioExposicoes() {
		return patrocinioExposicoes;
	}

	public void setPatrocinioExposicoes(
			List<PatrocinioExposicao> patrocinioExposicoes) {
		this.patrocinioExposicoes = patrocinioExposicoes;
	}

	public PatrocinioExposicao getSelectedPatrocinioExposicao() {
		return selectedPatrocinioExposicao;
	}

	public void setSelectedPatrocinioExposicao(
			PatrocinioExposicao selectedPatrocinioExposicao) {
		this.selectedPatrocinioExposicao = selectedPatrocinioExposicao;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<PatrocinioAtividade> getPatrocinioAtividades() {
		return patrocinioAtividades;
	}

	public void setPatrocinioAtividades(
			List<PatrocinioAtividade> patrocinioAtividades) {
		this.patrocinioAtividades = patrocinioAtividades;
	}

	public PatrocinioAtividade getSelectedPatrocinioAtividade() {
		return selectedPatrocinioAtividade;
	}

	public void setSelectedPatrocinioAtividade(
			PatrocinioAtividade selectedPatrocinioAtividade) {
		this.selectedPatrocinioAtividade = selectedPatrocinioAtividade;
	}

	public Atividade getSelectedAtividade() {
		return selectedAtividade;
	}

	public void setSelectedAtividade(Atividade selectedAtividade) {
		this.selectedAtividade = selectedAtividade;
	}

	public Integer getQtdeAtividade() {
		return qtdeAtividade;
	}

	public void setQtdeAtividade(Integer qtdeAtividade) {
		this.qtdeAtividade = qtdeAtividade;
	}

	public List<PatrocinioExposicao> exposicoesDetail(PatrocinioTipo patrocinioTipo) {
		return patrocinioExposicaoModel.exposicoes(patrocinioTipo);
	}
	
	public List<PatrocinioAtividade> atividadesDetail(PatrocinioTipo patrocinioTipo) {
		return patrocinioAtividadeModel.atividades(patrocinioTipo);
	}
}
