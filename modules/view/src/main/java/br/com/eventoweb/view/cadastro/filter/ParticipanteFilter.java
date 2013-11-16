package br.com.eventoweb.view.cadastro.filter;

import org.apache.commons.lang.StringUtils;

import br.com.webutils.ui.filter.Filter;

public class ParticipanteFilter implements Filter {
	
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
		return !(this.getNome().equals(StringUtils.EMPTY));
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
