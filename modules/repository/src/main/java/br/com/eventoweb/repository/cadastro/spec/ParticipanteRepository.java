package br.com.eventoweb.repository.cadastro.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.libutils.jpa.Repository;

@Local
public interface ParticipanteRepository extends Repository<Participante> {

	List<Participante> buscarParticipante(String query);
	
}
