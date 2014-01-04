package br.com.eventoweb.repository.evento.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.repository.evento.spec.ComiteMembroRepository;
import br.com.eventoweb.repository.evento.spec.ComiteRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "comiteMembroRepository")
public class ComiteMembroRepositoryImpl extends
		AbstractEventoWebRepository<ComiteMembro> implements
		ComiteMembroRepository {

	@Resource
	private SessionContext sessionContext;
	
	@EJB
	ComiteRepository comiteRepository;
	
	public ComiteMembroRepositoryImpl() {
		super(ComiteMembro.class);
	}

	@Override
	public List<ComiteMembro> membrosComite(Comite c) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<ComiteMembro> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<ComiteMembro> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Comite> pathComite = root.get("comite");
		final Predicate predicateComite = criteriaBuilder.equal(pathComite, c); 

		criteriaQuery.where(predicateComite);
		
		return this.retrieveByCriteria(criteriaQuery);
		
	}

	@Override
	public Boolean membroEPresidente(Comite comite, Cadastro cadastro) {

		ComiteMembroRepository comiteMembroRepository = sessionContext.getBusinessObject(ComiteMembroRepository.class);
		List<ComiteMembro> comiteMembros = comiteMembroRepository.membrosComite(comite);
		
		for(ComiteMembro comiteMembro: comiteMembros) {
			
			if(comiteMembro.getParticipante().getCadastro().equals(cadastro)) {
				return (comiteMembro.getTipoComiteMembro() == TipoComiteMembro.PRESIDENTE);
			}
		}
		
		return false;
	}

	@Override
	public Boolean membroEPresidente(Tema tema, Cadastro cadastro) {
		
		List<Comite> comites = comiteRepository.comitesTema(tema);

		ComiteMembroRepository comiteMembroRepositoryAux = sessionContext.getBusinessObject(ComiteMembroRepository.class);
		
		if(comites != null && comites.size() > 0) {
			return comiteMembroRepositoryAux.membroEPresidente(comites.get(0), cadastro);
		}

		return false;
	}
	
}
