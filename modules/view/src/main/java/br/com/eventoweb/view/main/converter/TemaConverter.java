package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.evento.Tema;

@FacesConverter(value = "temaConverter", forClass = Tema.class)
public class TemaConverter extends AbstractEntityConverter<Tema> {

	public TemaConverter() {
		super(Tema.class);
	}

}
