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

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.repository.evento.spec.TemaRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "temaRepository")
public class TemaRepositoryImpl extends
		AbstractEventoWebRepository<Tema> implements
		TemaRepository {

	public TemaRepositoryImpl() {
		super(Tema.class);
	}

	@Override
	public List<Tema> temas(Evento e) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Tema> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Tema> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e); 

		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<Tema> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}

}
