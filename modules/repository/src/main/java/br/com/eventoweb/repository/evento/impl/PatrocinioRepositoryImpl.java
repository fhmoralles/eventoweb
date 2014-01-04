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
import br.com.eventoweb.domain.evento.Patrocinio;
import br.com.eventoweb.repository.evento.spec.PatrocinioRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "patrocinioRepository")
public class PatrocinioRepositoryImpl extends
		AbstractEventoWebRepository<Patrocinio> implements
		PatrocinioRepository {

	public PatrocinioRepositoryImpl() {
		super(Patrocinio.class);
	}

	public List<Patrocinio> patrocinadoresEvento(Evento e) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Patrocinio> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Patrocinio> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("participante").get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e); 

		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<Patrocinio> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}
	
}
