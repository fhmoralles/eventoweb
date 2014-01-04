package br.com.eventoweb.view.evento.filter;

import br.com.eventoweb.domain.evento.Comite;
import br.com.webutils.ui.filter.Filter;

public class ComiteMembroFilter implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7154655802970985621L;
	
	private Comite comite;
	
	@Override
	public void reset() {
		this.setComite(null);
	}

	@Override
	public boolean isValid() {
		return (this.getComite() != null);
	}

	@Override
	public String getValidationMessage() {
		return null;
	}

	public Comite getComite() {
		return comite;
	}

	public void setComite(Comite comite) {
		this.comite = comite;
	}

}
