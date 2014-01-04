package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.evento.ComiteMembro;

@FacesConverter(value = "comiteMembroConverter", forClass = ComiteMembro.class)
public class ComiteMembroConverter extends AbstractEntityConverter<ComiteMembro> {

	public ComiteMembroConverter() {
		super(ComiteMembro.class);
	}

}
