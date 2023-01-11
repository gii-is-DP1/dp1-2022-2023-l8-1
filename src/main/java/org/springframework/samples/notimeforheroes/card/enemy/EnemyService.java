package org.springframework.samples.notimeforheroes.card.enemy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnemyService {
	//Repositorios de enemigos como variables
	@Autowired
	private EnemyRepository enemyRepository;
	@Autowired
	private EnemyInGameRepository enemyInGameRepository;
	
	//Lista de todos los enemigos
	public List<Enemy> findAll(){
		return (List<Enemy>) enemyRepository.findAll();
	}
	public List<Enemy> findAllByBoss(Boolean isBoss){
		return (List<Enemy>) enemyRepository.findAllByBoss(isBoss);
	}
	//Lista de los enemigos en un juego determinado
	public List<EnemyInGame>  findAllByGame(Game game){
		return enemyInGameRepository.findAllByGame(game);
	}

	//Añade enemigos a un juego
	@Transactional()
	public List<EnemyInGame> addEnemies(Game game,int numCards) {
		List<Enemy> enemies = findAllByBoss(false);	
		Collections.shuffle(enemies);
		List<EnemyInGame> enemiesIG = enemies.stream()
				.map(enemy -> EnemyInGame.createEnemyInGame(enemy,game)).limit(numCards-1).collect(Collectors.toList());//menos uno para que rellene el jefe
		for(int i =0;i<3;i++) {//para empezar con 3 enemigos en el field
			EnemyInGame enemy = enemiesIG.get(i);
			enemy.setGame(null);
			enemy.setGameField(game);
		}
		for(EnemyInGame enemy:enemiesIG) {
			saveEnemyInGame(enemy);
		}
		List<Enemy> bosses = findAllByBoss(true);
		Collections.shuffle(bosses);
		EnemyInGame boss = EnemyInGame.createEnemyInGame(bosses.get(0), game);
		saveEnemyInGame(boss);
		enemiesIG.add(boss);
		return enemiesIG;
	}
	//añadir el número de enemigos que pida
	public void enemyToField(Game game,int numEnemies) {
		List<EnemyInGame> pile = game.getMonsterPile();
		try {
			if(numEnemies==0) return;
			
			for(int i =0;i<numEnemies;i++) {
				EnemyInGame enemy = pile.get(i);
				enemy.setGameField(game);
				enemy.setGame(null);
				
				saveEnemyInGame(enemy);
			}
		}catch (Exception e) {//por si no quedan cartas
			return;
		}
	}
	//función save simple
	public void saveEnemyInGame(EnemyInGame enemy) {
		enemyInGameRepository.save(enemy);
	}
}
