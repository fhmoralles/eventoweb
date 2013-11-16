package br.com.eventoweb.model.cadastro.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.model.spec.InterfaceEventoWebModel;

@Local
public interface ParticipanteModel extends InterfaceEventoWebModel<Participante> {
	
	Participante update(Participante p, Usuario u) throws Exception;
	
	List<Participante> buscarParticipante(String query);
	
}
