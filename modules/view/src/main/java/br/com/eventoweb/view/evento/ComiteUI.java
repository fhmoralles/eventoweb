package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.model.evento.spec.ComiteModel;
import br.com.eventoweb.model.evento.spec.TemaModel;
import br.com.eventoweb.view.evento.filter.EventoOpcoesFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class ComiteUI extends AbstractCRUD<Comite, EventoOpcoesFilter> {

	// private static final Logger LOG = Logger.getLogger(ComiteUI.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4598255299143148710L;

	private static final String COMITE_PESQUISAR = "/forms/eventos/comitePesquisar.xhtml";
	private static final String COMITE_CADASTRAR = "/forms/eventos/comiteCadastrar.xhtml";

	@Inject
	private ComiteModel comiteModel;
	@Inject
	private TemaModel temaModel;

	public ComiteUI() {
		super(new EventoOpcoesFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteImpl(Comite bean) throws Exception {
		comiteModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if (this.getBean() != null) {
			return this.getBean().getDescricao();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return COMITE_CADASTRAR;
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
		return COMITE_PESQUISAR;
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
	protected boolean isValidBean(Comite bean) {
		return (this.getBean().getTema() != null);
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Comite newInstance() {
		return new Comite();
	}

	@Override
	protected void saveImpl(Comite bean) throws Exception {
		comiteModel.update(bean);
	}

	@Override
	protected List<Comite> searchImpl(EventoOpcoesFilter filter) {
		return comiteModel.comitesEvento(filter.getEvento());
	}

	protected List<Tema> getTemas() {
		return temaModel.temas(this.getFilter().getEvento());
	}

	public String nomePresidente(Comite c) {

		for (ComiteMembro comiteMembro : c.getMembros()) {

			if (comiteMembro.getTipoComiteMembro() == TipoComiteMembro.PRESIDENTE) {
				return comiteMembro.getParticipante().getCadastro()
						.getRazaoSocial();
			}
		}
		
		return null;

	}
}
