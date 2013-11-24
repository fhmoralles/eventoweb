package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.cadastro.Lugar;

@FacesConverter(value = "lugarConverter", forClass = Lugar.class)
public class LugarConverter extends AbstractEntityConverter<Lugar> {

	public LugarConverter() {
		super(Lugar.class);
	}

}
