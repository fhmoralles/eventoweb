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

import br.com.eventoweb.domain.evento.PatrocinioAtividade;
import br.com.eventoweb.domain.evento.PatrocinioTipo;
import br.com.eventoweb.repository.evento.spec.PatrocinioAtividadeRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "patrocinioAtividadeRepository")
public class PatrocinioAtividadeRepositoryImpl extends
		AbstractEventoWebRepository<PatrocinioAtividade> implements
		PatrocinioAtividadeRepository {

	public PatrocinioAtividadeRepositoryImpl() {
		super(PatrocinioAtividade.class);
	}

	@Override
	public List<PatrocinioAtividade> atividades(PatrocinioTipo p) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<PatrocinioAtividade> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<PatrocinioAtividade> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<PatrocinioTipo> pathPatrocinioTipo = root.get("patrocinioTipo");
		final Predicate predicateEvento = criteriaBuilder.equal(pathPatrocinioTipo, p); 

		criteriaQuery.where(predicateEvento);
		
		final TypedQuery<PatrocinioAtividade> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();

	}

}
