package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.cadastro.Espaco;

@FacesConverter(value = "espacoConverter", forClass = Espaco.class)
public class EspacoConverter extends AbstractEntityConverter<Espaco> {

	public EspacoConverter() {
		super(Espaco.class);
	}

}
