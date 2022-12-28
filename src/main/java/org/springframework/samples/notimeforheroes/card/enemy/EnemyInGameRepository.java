package org.springframework.samples.notimeforheroes.card.enemy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyInGameRepository extends CrudRepository<EnemyInGame,Integer>{
	List<EnemyInGame> findAll();
	@Query("SELECT eig FROM EnemyInGame eig WHERE eig.game=?1")
	List<EnemyInGame> findAllByGame(Game game);
	
}
