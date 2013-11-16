package br.com.eventoweb.view.evento.filter;

import org.apache.commons.lang.StringUtils;

import br.com.webutils.ui.filter.Filter;

public class EventoFilter implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6178943602875397379L;
	
	private String descricao;
	
	@Override
	public void reset() {
		this.setDescricao(StringUtils.EMPTY);
	}

	@Override
	public boolean isValid() {
		
		if(this.getDescricao() != null) {
			return !(this.getDescricao().equals(StringUtils.EMPTY));
		} else {
			return true;
		}
	}

	@Override
	public String getValidationMessage() {
		return null;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
