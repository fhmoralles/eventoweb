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
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.repository.evento.spec.PatrocinioTipoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "patrocinioTipoRepository")
public class PatrocinioTipoRepositoryImpl extends
		AbstractEventoWebRepository<PatrocinioTipo> implements
		PatrocinioTipoRepository {

	public PatrocinioTipoRepositoryImpl() {
		super(PatrocinioTipo.class);
	}

	@Override
	public List<PatrocinioTipo> patrocinioTipos(Evento e) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<PatrocinioTipo> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<PatrocinioTipo> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e); 

		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<PatrocinioTipo> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();

	}

}
