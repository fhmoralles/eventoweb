package br.com.eventoweb.repository.evento.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.libutils.jpa.Repository;

@Local
public interface PatrocinioTipoRepository extends Repository<PatrocinioTipo> {

	List<PatrocinioTipo> patrocinioTipos(Evento e);

}
