package org.springframework.samples.notimeforheroes.card.enemy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends CrudRepository<Enemy,Integer>{
	
	
}
