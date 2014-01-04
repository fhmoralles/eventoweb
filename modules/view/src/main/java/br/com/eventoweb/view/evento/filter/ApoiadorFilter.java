package br.com.eventoweb.view.evento.filter;

import br.com.eventoweb.domain.evento.Evento;
import br.com.webutils.ui.filter.Filter;

public class ApoiadorFilter implements Filter {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3730108862297940093L;
	
	private Evento evento;
	
	@Override
	public void reset() {
		this.setEvento(null);
	}

	@Override
	public boolean isValid() {
		return (this.getEvento() != null);
	}

	@Override
	public String getValidationMessage() {
		return null;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
