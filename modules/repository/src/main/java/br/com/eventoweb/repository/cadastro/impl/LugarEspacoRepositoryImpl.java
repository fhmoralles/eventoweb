package br.com.eventoweb.repository.cadastro.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.cadastro.Lugar;
import br.com.eventoweb.domain.cadastro.LugarEspaco;
import br.com.eventoweb.repository.cadastro.spec.LugarEspacoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "lugarEspacoRepository")
public class LugarEspacoRepositoryImpl extends
		AbstractEventoWebRepository<LugarEspaco> implements
		LugarEspacoRepository {

	public LugarEspacoRepositoryImpl() {
		super(LugarEspaco.class);
	}

	@Override
	public List<LugarEspaco> espacosLugar(Lugar lugar) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<LugarEspaco> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<LugarEspaco> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Lugar> pathLugar = root.get("lugar");
		final Predicate predicateLugar = criteriaBuilder.equal(pathLugar, lugar);
		criteriaQuery.where(predicateLugar);
		
		return retrieveByCriteria(criteriaQuery);
	}

}
