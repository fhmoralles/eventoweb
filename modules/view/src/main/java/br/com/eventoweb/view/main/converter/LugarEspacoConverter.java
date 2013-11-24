package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.cadastro.LugarEspaco;

@FacesConverter(value = "lugarEspacoConverter", forClass = LugarEspaco.class)
public class LugarEspacoConverter extends AbstractEntityConverter<LugarEspaco> {

	public LugarEspacoConverter() {
		super(LugarEspaco.class);
	}

}
