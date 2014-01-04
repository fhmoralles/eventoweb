package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.evento.Exposicao;

@FacesConverter(value = "exposicaoConverter", forClass = Exposicao.class)
public class ExposicaoConverter extends AbstractEntityConverter<Exposicao> {

	public ExposicaoConverter() {
		super(Exposicao.class);
	}

}
