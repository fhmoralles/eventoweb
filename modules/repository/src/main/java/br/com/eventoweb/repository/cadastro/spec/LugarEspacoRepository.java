package br.com.eventoweb.repository.cadastro.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.eventoweb.domain.cadastro.Lugar;
import br.com.eventoweb.domain.cadastro.LugarEspaco;
import br.com.libutils.jpa.Repository;

@Local
public interface LugarEspacoRepository extends Repository<LugarEspaco> {

	List<LugarEspaco> espacosLugar(Lugar lugar);
	
}
