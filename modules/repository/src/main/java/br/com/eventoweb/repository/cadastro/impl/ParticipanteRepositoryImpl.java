package br.com.eventoweb.repository.cadastro.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.repository.cadastro.spec.ParticipanteRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "participanteRepository")
public class ParticipanteRepositoryImpl extends
		AbstractEventoWebRepository<Participante> implements
		ParticipanteRepository {

	public ParticipanteRepositoryImpl() {
		super(Participante.class);
	}

	@Override
	public List<Participante> buscarParticipante(String query) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Participante> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Participante> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados
		 * Filtro por codigo */
		final Path<String> pathNome = root.get("razaoSocial");
		final Predicate predicateNome = criteriaBuilder.like(pathNome, query);
		criteriaQuery.where(predicateNome);
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("razaoSocial")));
		
		return retrieveByCriteria(criteriaQuery);
	}

}
