package org.springframework.samples.notimeforheroes.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    @Query("Select u FROM User u where username = :username")
  public User getByUsername(@Param("username") String username);

    public List<User> findByUsernameStartsWith(String username);
	
	@Query("SELECT u FROM User u WHERE u.username=?1")
    User findByUsername(String username);

    Page<User> findAll(Pageable pageable);

}
