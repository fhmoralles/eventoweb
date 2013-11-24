package br.com.eventoweb.view.cadastro.filter;

import org.apache.commons.lang.StringUtils;

import br.com.webutils.ui.filter.Filter;

public class LugarFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873464664831048896L;
	
	private String razaoSocial;

	@Override
	public void reset() {
		this.setRazaoSocial(StringUtils.EMPTY);
	}

	@Override
	public boolean isValid() {
		return (this.getRazaoSocial() != null && !this.getRazaoSocial().equals(StringUtils.EMPTY));
	}

	@Override
	public String getValidationMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	
}
