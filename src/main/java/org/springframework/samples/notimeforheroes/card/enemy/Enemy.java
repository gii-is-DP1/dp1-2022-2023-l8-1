package org.springframework.samples.notimeforheroes.card.enemy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "enemies")
public class Enemy {

    @Id
    int endurance;
    int glory;

    EnemyType type;


    
}
