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
import br.com.eventoweb.domain.evento.Inscricao;
import br.com.eventoweb.repository.evento.spec.InscricaoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "inscricaoRepository")
public class InscricaoRepositoryImpl extends
		AbstractEventoWebRepository<Inscricao> implements
		InscricaoRepository {

	public InscricaoRepositoryImpl() {
		super(Inscricao.class);
	}

	@Override
	public List<Inscricao> inscricoesEvento(Evento e) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Inscricao> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Inscricao> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e); 

		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<Inscricao> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}
	
}
