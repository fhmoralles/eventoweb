package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.cadastro.Participante;

@FacesConverter(value = "participanteConverter", forClass = Participante.class)
public class ParticipanteConverter extends AbstractEntityConverter<Participante> {

	public ParticipanteConverter() {
		super(Participante.class);
	}

}
