package br.com.eventoweb.view.evento.filter;

import br.com.eventoweb.domain.evento.Submissao;
import br.com.webutils.ui.filter.Filter;

public class SubmissaoAvaliacaoFilter implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7154655802970985621L;
	
	private Submissao submissao;
	
	@Override
	public void reset() {
		this.setSubmissao(null);
	}

	@Override
	public boolean isValid() {
		return (this.getSubmissao() != null);
	}

	@Override
	public String getValidationMessage() {
		return null;
	}

	public Submissao getSubmissao() {
		return submissao;
	}

	public void setSubmissao(Submissao submissao) {
		this.submissao = submissao;
	}

}
