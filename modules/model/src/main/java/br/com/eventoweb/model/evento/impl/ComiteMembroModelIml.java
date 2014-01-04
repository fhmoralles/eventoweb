package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.model.evento.spec.ComiteMembroModel;
import br.com.eventoweb.repository.evento.spec.ComiteMembroRepository;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;

@Stateless(name = "comiteMembroModel")
public class ComiteMembroModelIml implements ComiteMembroModel {

	@EJB
	private ComiteMembroRepository comiteMembroRepository;
	
	@EJB
	private ParticipanteRepository participanteRepository;
	
	public void create(ComiteMembro c) throws Exception {
		comiteMembroRepository.create(c);
	}

	@Override
	public ComiteMembro retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComiteMembro update(ComiteMembro c) throws Exception {
		Participante participante = c.getParticipante();
		participante = participanteRepository.update(participante);
		c.setParticipante(participante);

		return comiteMembroRepository.update(c);
	}

	@Override
	public void delete(ComiteMembro c) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		c = comiteMembroRepository.update(c);
	
		/* Excluir */
		comiteMembroRepository.delete(c);
	}

	@Override
	public void refresh(ComiteMembro c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ComiteMembro> membrosComite(Comite c) {
		return comiteMembroRepository.membrosComite(c);
	}

	@Override
	public Boolean membroEPresidente(Comite comite, Cadastro cadastro) {
		return comiteMembroRepository.membroEPresidente(comite, cadastro);
	}

	@Override
	public Boolean membroEPresidente(Tema tema, Cadastro cadastro) {
		return comiteMembroRepository.membroEPresidente(tema, cadastro);
	}

}
