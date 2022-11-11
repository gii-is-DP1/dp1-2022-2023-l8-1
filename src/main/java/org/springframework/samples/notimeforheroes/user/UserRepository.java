package org.springframework.samples.notimeforheroes.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, Integer>{

}
