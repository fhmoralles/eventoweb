package br.com.eventoweb.repository.cadastro.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.cadastro.Espaco;
import br.com.eventoweb.repository.cadastro.spec.EspacoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "espacoRepository")
public class EspacoRepositoryImpl extends
		AbstractEventoWebRepository<Espaco> implements
		EspacoRepository {

	public EspacoRepositoryImpl() {
		super(Espaco.class);
	}

	@Override
	public List<Espaco> espacosParticipante(Cadastro cadastro) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Espaco> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Espaco> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Cadastro> pathEspaco = root.get("cadastro");
		final Predicate predicateEspaco = criteriaBuilder.equal(pathEspaco, cadastro);
		criteriaQuery.where(predicateEspaco);
		
		return retrieveByCriteria(criteriaQuery);
	}

}
