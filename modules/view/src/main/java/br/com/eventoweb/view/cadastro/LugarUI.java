package br.com.eventoweb.view.cadastro;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.cadastro.Lugar;
import br.com.eventoweb.model.cadastro.spec.LugarModel;
import br.com.eventoweb.view.cadastro.filter.LugarFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class LugarUI extends AbstractCRUD<Lugar, LugarFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 654070305932386117L;

	@Inject
	private LugarModel lugarModel;
	
	public LugarUI() {
		super(new LugarFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Lugar bean) throws Exception {
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isValidBean(Lugar bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Lugar newInstance() {
		return new Lugar();
	}

	@Override
	protected void saveImpl(Lugar bean) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<Lugar> searchImpl(LugarFilter filter) {
		return lugarModel.buscarLugar("%" + filter.getRazaoSocial() + "%");
	}

}
