package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.evento.Atividade;

@FacesConverter(value = "atividadeConverter", forClass = Atividade.class)
public class AtividadeConverter extends AbstractEntityConverter<Atividade> {

	public AtividadeConverter() {
		super(Atividade.class);
	}

}
