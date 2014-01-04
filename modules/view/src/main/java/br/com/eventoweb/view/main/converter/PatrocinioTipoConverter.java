package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.evento.PatrocinioTipo;

@FacesConverter(value = "patrocinioTipoConverter", forClass = PatrocinioTipo.class)
public class PatrocinioTipoConverter extends AbstractEntityConverter<PatrocinioTipo> {

	public PatrocinioTipoConverter() {
		super(PatrocinioTipo.class);
	}

}
