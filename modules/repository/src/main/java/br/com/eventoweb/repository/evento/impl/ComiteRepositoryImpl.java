package br.com.eventoweb.repository.evento.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.repository.evento.spec.ComiteRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "comiteRepository")
public class ComiteRepositoryImpl extends
		AbstractEventoWebRepository<Comite> implements
		ComiteRepository {

	public ComiteRepositoryImpl() {
		super(Comite.class);
	}

	@Override
	public List<Comite> comitesEvento(Evento e) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Comite> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Comite> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("tema").get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e); 

		criteriaQuery.where(predicateEvento);
		
		return this.retrieveByCriteria(criteriaQuery);
		
	}

	@Override
	public List<Comite> comitesTema(Tema t) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Comite> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Comite> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Tema> pathTema = root.get("tema");
		final Predicate predicateTema = criteriaBuilder.equal(pathTema, t); 

		criteriaQuery.where(predicateTema);

		return this.retrieveByCriteria(criteriaQuery);
	}
	
}
