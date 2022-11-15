package org.springframework.samples.notimeforheroes.user;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;



public interface FriendsRepository extends  CrudRepository<Friends, String>{

    Collection<Friends> findAllByUserName (String userName);
	
}