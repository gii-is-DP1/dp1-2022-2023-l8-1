package org.springframework.samples.notimeforheroes.card.enemy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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


    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    EnemyType type;


    
}
