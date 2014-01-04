package br.com.eventoweb.model.cadastro.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.model.cadastro.spec.CadastroModel;
import br.com.eventoweb.repository.cadastro.spec.CadastroRepository;

@Stateless(name = "cadastroModel")
public class CadastroModelImpl implements CadastroModel {
	
	@EJB
	private CadastroRepository cadastroRepository;
	
	@Override
	public void create(Cadastro c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cadastro retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cadastro update(Cadastro c) throws Exception {
		return cadastroRepository.update(c);
	}

	@Override
	public void delete(Cadastro c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Cadastro c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cadastro update(Cadastro p, Usuario u) throws Exception {
		return this.update(p);
	}

	@Override
	public List<Cadastro> buscarCadastro(String query) {
		return cadastroRepository.buscarCadastro(query);
	}

}
