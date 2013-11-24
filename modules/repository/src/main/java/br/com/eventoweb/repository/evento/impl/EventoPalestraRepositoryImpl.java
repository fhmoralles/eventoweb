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
import br.com.eventoweb.domain.evento.EventoPalestra;
import br.com.eventoweb.repository.evento.spec.EventoPalestraRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "eventoPalestraRepository")
public class EventoPalestraRepositoryImpl extends
		AbstractEventoWebRepository<EventoPalestra> implements
		EventoPalestraRepository {

	public EventoPalestraRepositoryImpl() {
		super(EventoPalestra.class);
	}

	@Override
	public List<EventoPalestra> palestrasEvento(Evento e) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<EventoPalestra> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<EventoPalestra> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e);
		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<EventoPalestra> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}

}
