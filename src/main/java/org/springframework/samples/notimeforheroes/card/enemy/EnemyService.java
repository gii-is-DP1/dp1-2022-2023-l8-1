package org.springframework.samples.notimeforheroes.card.enemy;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnemyService {
	@Autowired
	private EnemyRepository enemyRepository;
	@Autowired
	private EnemyInGameRepository enemyInGameRepository;
	
	public List<Enemy> findAll(){
		return (List<Enemy>) enemyRepository.findAll();
	}
	public List<EnemyInGame>  findAllByGame(Game game){
		return enemyInGameRepository.findAllByGame(game);
	}
	@Transactional()
	public List<EnemyInGame> addEnemies(Game game) {
		List<EnemyInGame> enemiesIG = findAll().stream()
				.map(enemy -> EnemyInGame.createEnemyInGame(enemy,game)).collect(Collectors.toList());
		for(EnemyInGame enemy:enemiesIG) {
			saveEnemyInGame(enemy);
		}
		return enemiesIG;
	}
	
	public void saveEnemyInGame(EnemyInGame enemy) {
		enemyInGameRepository.save(enemy);
	}
}