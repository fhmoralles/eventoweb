package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.Exposicao;
import br.com.eventoweb.model.evento.spec.ExposicaoModel;
import br.com.eventoweb.view.evento.filter.ExposicaoFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class ExposicaoUI extends AbstractCRUD<Exposicao, ExposicaoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6991662102398733893L;

	//private static final Logger LOG = Logger.getLogger(ExposicaoUI.class);
	
	private static final String EXPOSICAO_PESQUISAR = "/forms/eventos/exposicaoPesquisar.xhtml";
	private static final String EXPOSICAO_CADASTRAR = "/forms/eventos/exposicaoCadastrar.xhtml";

	@Inject
	private ExposicaoModel exposicaoModel;

	public ExposicaoUI() {
		super(new ExposicaoFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Exposicao bean) throws Exception {
		exposicaoModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null && this.getBean().getDescricao() != null) {
			return this.getBean().getDescricao();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return EXPOSICAO_CADASTRAR;
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
		return EXPOSICAO_PESQUISAR;
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
	protected boolean isValidBean(Exposicao bean) {
		return (this.getBean().getDescricao() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Exposicao newInstance() {
		Exposicao exposicao = new Exposicao(this.getFilter().getEvento());
		
		return exposicao;
	}

	@Override
	protected void saveImpl(Exposicao bean) throws Exception {
		exposicaoModel.update(bean);
	}

	@Override
	protected List<Exposicao> searchImpl(ExposicaoFilter filter) {
		return exposicaoModel.exposicoes(filter.getEvento());
	}

}
