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
import br.com.eventoweb.domain.evento.Organizador;
import br.com.eventoweb.repository.evento.spec.OrganizadorRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "organizadorRepository")
public class OrganizadorRepositoryImpl extends
		AbstractEventoWebRepository<Organizador> implements
		OrganizadorRepository {

	public OrganizadorRepositoryImpl() {
		super(Organizador.class);
	}

	@Override
	public List<Organizador> organizadoresEvento(Evento e) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Organizador> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Organizador> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados
		 * Filtro por codigo */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e);
		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<Organizador> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}

}
