package br.com.eventoweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.eventoweb.domain.cadastro.Cadastro;

@FacesConverter(value = "cadastroConverter", forClass = Cadastro.class)
public class CadastroConverter extends AbstractEntityConverter<Cadastro> {

	public CadastroConverter() {
		super(Cadastro.class);
	}

}
