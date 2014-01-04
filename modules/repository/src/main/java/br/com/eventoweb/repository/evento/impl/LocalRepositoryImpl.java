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
import br.com.eventoweb.domain.evento.Local;
import br.com.eventoweb.repository.evento.spec.LocalRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "localRepository")
public class LocalRepositoryImpl extends
		AbstractEventoWebRepository<Local> implements
		LocalRepository {

	public LocalRepositoryImpl() {
		super(Local.class);
	}

	public List<Local> locaisEvento(Evento e) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Local> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Local> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informa��es no banco de dados */
		final Path<Evento> pathEvento = root.get("participante").get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e); 

		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<Local> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}
	
}
