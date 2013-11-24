package br.com.eventoweb.view.evento.filter;

import br.com.eventoweb.domain.evento.Evento;
import br.com.webutils.ui.filter.Filter;

public class PalestraFilter implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5014541637298828958L;
	
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
