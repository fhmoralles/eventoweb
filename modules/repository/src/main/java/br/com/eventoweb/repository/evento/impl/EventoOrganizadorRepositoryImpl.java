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
import br.com.eventoweb.domain.evento.EventoOrganizador;
import br.com.eventoweb.repository.evento.spec.EventoOrganizadorRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "eventoOrganizadorRepository")
public class EventoOrganizadorRepositoryImpl extends
		AbstractEventoWebRepository<EventoOrganizador> implements
		EventoOrganizadorRepository {

	public EventoOrganizadorRepositoryImpl() {
		super(EventoOrganizador.class);
	}

	@Override
	public List<EventoOrganizador> organizadoresEvento(Evento e) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<EventoOrganizador> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<EventoOrganizador> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e);
		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<EventoOrganizador> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}

}
