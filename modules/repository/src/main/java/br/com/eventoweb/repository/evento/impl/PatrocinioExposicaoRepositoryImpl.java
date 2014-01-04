package br.com.eventoweb.repository.evento.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.evento.PatrocinioExposicao;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.repository.evento.spec.PatrocinioExposicaoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "patrocinioExposicaoRepository")
public class PatrocinioExposicaoRepositoryImpl extends
		AbstractEventoWebRepository<PatrocinioExposicao> implements
		PatrocinioExposicaoRepository {

	public PatrocinioExposicaoRepositoryImpl() {
		super(PatrocinioExposicao.class);
	}

	@Override
	public List<PatrocinioExposicao> exposicoes(PatrocinioTipo p) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<PatrocinioExposicao> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<PatrocinioExposicao> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<PatrocinioTipo> pathPatrocinioTipo = root.get("patrocinioTipo");
		final Predicate predicateEvento = criteriaBuilder.equal(pathPatrocinioTipo, p); 

		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<PatrocinioExposicao> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();

	}

}
