package br.com.eventoweb.view;

import java.io.Serializable;
import java.util.Currency;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class LocaleUI implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Locale locale = FacesContext.getCurrentInstance().getApplication()
			.getDefaultLocale();

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

	public String getCurrencySymbol() {

		final Currency currency = Currency.getInstance(locale);
		//return currency.getSymbol();
		return "R$";
	}
}