package br.com.eventoweb.view.evento;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.model.evento.spec.EventoModel;
import br.com.eventoweb.view.evento.filter.EventoFilter;
import br.com.eventoweb.view.main.security.EventoUser;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
public class MeusEventosUI extends AbstractCRUD<Evento, EventoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2164254370123961820L;

	private static final String EVENTO_MEUSEVENTOS = "/main.xhtml";
	
	@Inject
	private Identity identity;

	@Inject
	private EventoModel eventoModel;

	public MeusEventosUI() {
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
		return EVENTO_MEUSEVENTOS;
	}

	@Override
	public boolean isDeletable() {
		
		/* TODO: Se for o criado e/ou organizador, sim. Senão não. */
		return false;
	}

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public boolean isInsertable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSearchable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isSearchOnPrepare() {
		return true;
	}

	@Override
	protected boolean isValidBean(Evento bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Evento newInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void saveImpl(Evento bean) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected List<Evento> searchImpl(EventoFilter filter) {
		
		Participante p = ((EventoUser)identity.getUser()).getUsuario().getParticipante();
		return eventoModel.eventosParticipante(p); 
		
	}

}
