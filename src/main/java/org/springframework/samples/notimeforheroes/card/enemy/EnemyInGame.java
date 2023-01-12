package org.springframework.samples.notimeforheroes.card.enemy;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.game.Game;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EnemyInGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Min(0)
    private int wounds;

    @ManyToOne(targetEntity = Game.class)
    @JoinColumn(name="game_id")
    private Game game;
    
    @ManyToOne(targetEntity = Game.class)
    @JoinColumn(name="game_field_id")
    private Game gameField;

    @ManyToOne(targetEntity = Enemy.class)
    private Enemy enemy;

    @OneToMany(mappedBy="enemyInGame")
	  private List<AbilityCardInGame> cardsPlayed;

    //Asociar enemigos a un juego
    public static EnemyInGame createEnemyInGame(Enemy enemy, Game game) {
    	EnemyInGame enemyIG = new EnemyInGame();
    	enemyIG.setEnemy(enemy);
    	enemyIG.setGame(game);
		return enemyIG;
    }

    public String toString(){
      return enemy.getType().toString();
    }
    
}
