package br.com.eventoweb.repository.cadastro.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.repository.cadastro.spec.CadastroRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "cadastroRepository")
public class CadastroRepositoryImpl extends
		AbstractEventoWebRepository<Cadastro> implements
		CadastroRepository {

	public CadastroRepositoryImpl() {
		super(Cadastro.class);
	}

	@Override
	public List<Cadastro> buscarCadastro(String query) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Cadastro> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Cadastro> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados
		 * Filtro por codigo */
		final Path<String> pathNome = root.get("razaoSocial");
		final Predicate predicateNome = criteriaBuilder.like(pathNome, query);
		criteriaQuery.where(predicateNome);
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("razaoSocial")));
		
		return retrieveByCriteria(criteriaQuery);
	}

}
