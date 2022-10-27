package org.springframework.samples.notimeforheroes.card.enemy;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EnemyInGame {

    @Id
    private int endurance;

    
}
