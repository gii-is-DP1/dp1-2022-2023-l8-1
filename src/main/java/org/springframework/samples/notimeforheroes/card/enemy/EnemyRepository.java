package org.springframework.samples.notimeforheroes.card.enemy;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends CrudRepository<Enemy,Integer>{
	
	
}
