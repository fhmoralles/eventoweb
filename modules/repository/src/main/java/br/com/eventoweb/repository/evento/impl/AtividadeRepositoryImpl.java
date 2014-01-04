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
import br.com.eventoweb.domain.evento.Atividade;
import br.com.eventoweb.domain.types.TipoAtividade;
import br.com.eventoweb.repository.evento.spec.AtividadeRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "atividadeRepository")
public class AtividadeRepositoryImpl extends
		AbstractEventoWebRepository<Atividade> implements
		AtividadeRepository {

	public AtividadeRepositoryImpl() {
		super(Atividade.class);
	}

	@Override
	public List<Atividade> palestrasEvento(Evento e) {
		return this.atividadesEvento(e, TipoAtividade.PALESTRA);
	}

	@Override
	public List<Atividade> cursosEvento(Evento e) {
		return this.atividadesEvento(e, TipoAtividade.CURSO);
	}

	@Override
	public List<Atividade> minicursosEvento(Evento e) {
		return this.atividadesEvento(e, TipoAtividade.MINICURSO);
	}

	@Override
	public List<Atividade> workshopsEvento(Evento e) {
		return this.atividadesEvento(e, TipoAtividade.WORKSHOP);
	}

	public List<Atividade> atividadesEvento(Evento e, TipoAtividade tipoAtividade) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Atividade> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Atividade> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("participante").get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e); 

		final Path<TipoAtividade> pathTipoAtividade = root.get("tipoAtividade");
		final Predicate predicateTipoAtividade = criteriaBuilder.equal(pathTipoAtividade, tipoAtividade);

		criteriaQuery.where(predicateEvento, predicateTipoAtividade);
		
		final TypedQuery<Atividade> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}
	
}
