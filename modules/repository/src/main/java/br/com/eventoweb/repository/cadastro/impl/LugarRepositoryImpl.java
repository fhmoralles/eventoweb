package br.com.eventoweb.repository.cadastro.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.cadastro.Lugar;
import br.com.eventoweb.repository.cadastro.spec.LugarRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "lugarRepository")
public class LugarRepositoryImpl extends
		AbstractEventoWebRepository<Lugar> implements
		LugarRepository {

	public LugarRepositoryImpl() {
		super(Lugar.class);
	}

	@Override
	public List<Lugar> buscarLugar(String query) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Lugar> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Lugar> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados
		 * Filtro por codigo */
		final Path<String> pathNome = root.get("razaoSocial");
		final Predicate predicateNome = criteriaBuilder.like(pathNome, query);
		criteriaQuery.where(predicateNome);
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("razaoSocial")));
		
		return retrieveByCriteria(criteriaQuery);
	}

}
