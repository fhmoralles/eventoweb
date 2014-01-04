package br.com.eventoweb.repository.evento.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.SubmissaoArquivo;
import br.com.eventoweb.repository.evento.spec.SubmissaoArquivoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "submissaoArquivoRepository")
public class SubmissaoArquivoRepositoryImpl extends
		AbstractEventoWebRepository<SubmissaoArquivo> implements SubmissaoArquivoRepository {

	public SubmissaoArquivoRepositoryImpl() {
		super(SubmissaoArquivo.class);
	}

	@Override
	public List<SubmissaoArquivo> arquivosSubmissao(Submissao submissao) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<SubmissaoArquivo> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<SubmissaoArquivo> root = createRoot(criteriaQuery);

		/* Repository -> Apenas pesquias as informações no banco de dados */

		/* Filtra por evento */
		final Path<Submissao> pathSubmissao = root.get("submissao");
		final Predicate predicateSubmissao = criteriaBuilder.equal(pathSubmissao, submissao);

		criteriaQuery.where(predicateSubmissao);

		return this.retrieveByCriteria(criteriaQuery);

	}

}
