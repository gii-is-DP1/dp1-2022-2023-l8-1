package org.springframework.samples.notimeforheroes.card.enemy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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


    private int endurance;

    @ManyToOne(targetEntity = Game.class)
    private Game game;

    @ManyToOne(targetEntity = Enemy.class)
    private Enemy enemy;


    
}
