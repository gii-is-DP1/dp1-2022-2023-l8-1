package org.springframework.samples.notimeforheroes.card.enemy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends CrudRepository<Enemy,Integer>{
	//Funciones básicas de repositorio gracias a la extensión CrudRepositry
	
	@Query("SELECT e FROM Enemy e WHERE e.isBoss=?1")
	List<Enemy> findAllByBoss(Boolean isBoss);
}
