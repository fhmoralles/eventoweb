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

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.repository.evento.spec.EventoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "eventoRepository")
public class EventoRepositoryImpl extends
		AbstractEventoWebRepository<Evento> implements
		EventoRepository {

	public EventoRepositoryImpl() {
		super(Evento.class);
	}

	@Override
	public List<Evento> eventosParticipante(Participante p) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Evento> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Evento> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados
		 * Filtro por codigo */
		final Path<Participante> pathParticipante = root.get("participante");
		final Predicate predicateParticipante = criteriaBuilder.equal(pathParticipante, p);
		criteriaQuery.where(predicateParticipante);
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dataEvento")));
		
		final TypedQuery<Evento> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}

}
