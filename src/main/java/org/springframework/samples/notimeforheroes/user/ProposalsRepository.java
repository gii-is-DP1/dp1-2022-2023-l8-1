package org.springframework.samples.notimeforheroes.user;

import org.springframework.data.repository.CrudRepository;



public interface ProposalsRepository extends  CrudRepository<Proposals, String>{
	
}