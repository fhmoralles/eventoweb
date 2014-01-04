package br.com.eventoweb.view.evento.filter;

import org.apache.commons.lang.StringUtils;

import br.com.webutils.ui.filter.Filter;

public class EventoFilter implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6178943602875397379L;
	
	private String nome;
	
	@Override
	public void reset() {
		this.setNome(StringUtils.EMPTY);
	}

	@Override
	public boolean isValid() {
		
		if(this.getNome() != null) {
			return !(this.getNome().equals(StringUtils.EMPTY));
		} else {
			return true;
		}
	}

	@Override
	public String getValidationMessage() {
		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
