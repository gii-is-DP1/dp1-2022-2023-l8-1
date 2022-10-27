package org.springframework.samples.notimeforheroes.user;

import org.springframework.data.repository.CrudRepository;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
}
