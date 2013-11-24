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
import br.com.eventoweb.domain.evento.EventoLocal;
import br.com.eventoweb.repository.evento.spec.EventoLocalRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "eventoLocalRepository")
public class EventoLocalRepositoryImpl extends
		AbstractEventoWebRepository<EventoLocal> implements
		EventoLocalRepository {

	public EventoLocalRepositoryImpl() {
		super(EventoLocal.class);
	}

	@Override
	public List<EventoLocal> locaisEvento(Evento e) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<EventoLocal> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<EventoLocal> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e);
		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<EventoLocal> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}

}
