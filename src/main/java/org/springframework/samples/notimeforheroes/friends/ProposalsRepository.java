package org.springframework.samples.notimeforheroes.friends;

import org.springframework.data.repository.CrudRepository;



public interface ProposalsRepository extends  CrudRepository<Proposals, String>{
	
}